//
//  CharacterListCollectionViewController.swift
//  Rick & Morty MasterDetail App
//
//  Created by Miguel on 5/18/23.
//

import UIKit
import Nuke

private let reuseIdentifier = "Cell"

final class CharacterListCollectionViewController: UICollectionViewController, UICollectionViewDataSourcePrefetching {

    // MARK: - Typealias
    private typealias Snapshot = NSDiffableDataSourceSnapshot<String, Character>
    private typealias DataSource = UICollectionViewDiffableDataSource<String, Character>
    
    // MARK: - Properties
    private let viewModel: CharacterListViewModel!
    private lazy var dataSource: DataSource = { createDatasource() }()
    private let prefetcher = ImagePrefetcher(maxConcurrentRequestCount: 6)
    private let refreshControl = UIRefreshControl()
    private let section = "OneSection"
    private var elements: [Character] = []
    
    // MARK: - Initializers
    init(viewModel: CharacterListViewModel) {
        
        self.viewModel = viewModel
        super.init(collectionViewLayout: UICollectionViewLayout())
    }
    
    required init?(coder: NSCoder) {
        
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: - Lyfecycle
    override func viewDidLoad() {
        
        collectionView.prefetchDataSource = self
        collectionView.isPrefetchingEnabled = true
        
        elements = viewModel.characters
        collectionView.collectionViewLayout = createLayout()
        applySnapshot(animatingDifferences: true)
        
        viewModel.characterListDidChange = { [unowned self] filteredCharacterList in
            elements = filteredCharacterList
            applySnapshot(animatingDifferences: true)
        }
        
        refreshControl.addTarget(self, action: #selector(refreshCollectionView), for: .valueChanged)
        collectionView.refreshControl = refreshControl
    }
    
    // MARK: - CollectionViewDelegate Methods
    override func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        
        let characterId = String(elements[indexPath.row].id)
        viewModel.didTapItem(characterId: characterId)
    }
    
    override func scrollViewDidScroll(_ scrollView: UIScrollView) {
        
        if(didScrollUntilBottom(scrollView)) {
            viewModel.collectionViewDidScrollUntilBottom()
        }
    }
    
    // MARK: Private Methods
    @objc private func refreshCollectionView() {
        
        viewModel.collectionViewDidMadeRefreshGesture()
        
        DispatchQueue.main.async {
            self.collectionView.refreshControl?.endRefreshing()
        }
    }
    
    private func createLayout() -> UICollectionViewCompositionalLayout {
        
        let size = NSCollectionLayoutSize(widthDimension: .fractionalWidth(1),
                                          heightDimension: .fractionalHeight(1))
        
        let item = NSCollectionLayoutItem(layoutSize: size)
        item.contentInsets = NSDirectionalEdgeInsets(top: 0, leading: 1, bottom: 0, trailing: 1)
        
        let group: NSCollectionLayoutGroup
        let  groupSize = NSCollectionLayoutSize(widthDimension: .fractionalWidth(1),
                                                heightDimension: .fractionalHeight(0.25))
        
        group = NSCollectionLayoutGroup.horizontal(layoutSize: groupSize, subitems: Array(repeating: item, count: 1))
        
        group.interItemSpacing = .fixed(CGFloat(10))
        group.contentInsets =  NSDirectionalEdgeInsets(top: 0, leading: 1, bottom: 0, trailing: 1)
        
        let section = NSCollectionLayoutSection(group: group)
        section.contentInsets = NSDirectionalEdgeInsets(top: 8, leading: 1, bottom: 8, trailing: 1)
        section.interGroupSpacing = CGFloat(10)
        
        return UICollectionViewCompositionalLayout(section: section)
    }
    
    private func didScrollUntilBottom(_ scrollView: UIScrollView) -> Bool {
        
        let offsetY = scrollView.contentOffset.y
        let contentHeight = scrollView.contentSize.height
        let height = scrollView.frame.size.height

        return offsetY > contentHeight - height
    }
    
    private func createDatasource() -> DataSource {
        
        UICollectionViewDiffableDataSource(collectionView: collectionView, cellProvider: { [unowned self] collectionView, indexPath, _ in
         
            let character = elements[indexPath.row]
            collectionView.register(CharacterItemCell.self)
            let cell: CharacterItemCell = collectionView.dequeueCell(for: indexPath)
            cell.configure(with: character)
            defineContentModeToImage()
            
            return cell
        })
    }

    private func applySnapshot(animatingDifferences: Bool = false) {
        
        var snapshot = Snapshot()
        snapshot.appendSections([section])
        
        snapshot.appendItems(elements, toSection: section)

        dataSource.apply(snapshot, animatingDifferences: animatingDifferences)
    }
    
    // MARK: - Defines Content Mode
    private func defineContentModeToImage() {
        
        // Define the default contentMode for each type of image loading result: success, failure and the placeholder.
        let contentModes = ImageLoadingOptions.ContentModes(success: .scaleToFill,
                                                            failure: .scaleAspectFit,
                                                            placeholder: .scaleAspectFit)

        ImageLoadingOptions.shared.contentModes = contentModes
        ImageLoadingOptions.shared.placeholder = UIImage(named: "PlaceHolderImage")
        ImageLoadingOptions.shared.transition = .fadeIn(duration: 0.1)
    }
    
    // MARK: - CollectionView DataSource Prefetching
    func collectionView(_ collectionView: UICollectionView, prefetchItemsAt indexPaths: [IndexPath]) {
        prefetcher.startPrefetching(with: viewModel.getURLImages(for: indexPaths))
    }
    
    func collectionView(_ collectionView: UICollectionView, cancelPrefetchingForItemsAt indexPaths: [IndexPath]) {
        prefetcher.stopPrefetching(with: viewModel.getURLImages(for: indexPaths))
    }
}

