//
//  CharacterListViewModel.swift
//  Rick & Morty MasterDetail App
//
//  Created by Miguel on 5/18/23.
//

import Foundation

protocol CharacterListCoordinatorDelegate {
    
    func navigateToCharacterDetail()
}

final class CharacterListViewModel {
    
    var coordinator: CharacterListCoordinatorDelegate!
    
    var characterListDidChange: (([Character]) -> Void)?
    
    var errorDidChange: ((Error?) -> Void)?
    
    private(set) var characters: [Character] = [] {
        didSet {
            
            characterListDidChange?(characters)
        }
    }
    
    private var error: Error? = nil {
        didSet {
            
            errorDidChange?(error)
        }
    }
    
    private var currentPage = -1
    private let useCases: CharacterUseCases
    
    // MARK: Initializers.
    init(_ useCases: CharacterUseCases) {
        
        self.useCases = useCases
    }
    
    // MARK: Public Methods.
    func collectionViewDidScrollUntilBottom() {
        
        loadNextPage()
    }
    
    func collectionViewDidMadeRefreshGesture() {
        
        refresh()
    }
    
    func didTapItem(characterId: String) {
        
        coordinator.navigateToCharacterDetail()
    }
    
    func getURLImages(for indexPaths: [IndexPath]) -> [URL] {
        
        indexPaths.compactMap { characters[$0.row].image }
    }
    
    // MARK: Private Methods.
    private func refresh() {
        
        currentPage = 1
        let page = String(currentPage)
        
        Task {
            
            let characterList = await getCharacters(page: page)
            DispatchQueue.main.async {
                
                self.characters = characterList
            }
        }
    }
    
    private func loadNextPage() {
        
        currentPage += 1
        let page = String(currentPage)
        guard page <= Constants.RICK_AND_MORTY_API.LAST_PAGE_INDEX else { return }
        
        Task {
            let characterList = await getCharacters(page: page)
            let newCharacterList = self.characters + characterList
            DispatchQueue.main.async {
                
                self.characters = newCharacterList
            }
        }
    }
    
    private func getCharacters(page: String) async -> [Character] {
        
        let response = await self.useCases.getCharacters(page: page)
        
        switch response {
        case .success(let characterList):
            return characterList
        case .failure(let error):
            self.error = error
            return []
        }
    }
}

