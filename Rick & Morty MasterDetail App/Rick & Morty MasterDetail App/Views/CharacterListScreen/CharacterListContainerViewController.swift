//
//  CharacterListContainerViewController.swift
//  Rick & Morty MasterDetail App
//
//  Created by Miguel on 5/18/23.
//

import UIKit

class CharacterListContainerViewController: UIViewController {

    @IBOutlet weak var characteListCollectionViewContainer: UIView!
    
    var viewModel: CharacterListViewModel
    
    private let characterListCollectionView: CharacterListCollectionViewController!
    
    init(_ viewModel: CharacterListViewModel) {
        
        self.viewModel = viewModel
        characterListCollectionView = CharacterListCollectionViewController(viewModel: viewModel)
        super.init(nibName: "CharacterListContainerViewController", bundle: Bundle(for: CharacterListContainerViewController.self))
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: Lifecycle
    override func viewDidLoad() {
        
        title = "Rick & Morty Characters"
        add(viewController: characterListCollectionView, to: characteListCollectionViewContainer)

        // Set navigation bar visibility
        navigationController?.isNavigationBarHidden = false
        customizeNavigationBar()
        
        super.viewDidLoad()
    }
    
    private func customizeNavigationBar() {
        
        guard let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene,
              let statusBarManager = windowScene.windows.first?.windowScene?.statusBarManager
        else { return }

        let statusBarView = UIView(frame: statusBarManager.statusBarFrame)
        statusBarView.backgroundColor = UIColor(named: "R&MPaletteSeanBlue")
        view.addSubview(statusBarView)
        
        navigationController?.navigationBar.barTintColor = UIColor(named: "R&MPaletteSeanBlue")
        navigationController?.navigationBar.backgroundColor = UIColor(named: "R&MPaletteSeanBlue")
        navigationController?.navigationBar.titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.black]
    }
}
