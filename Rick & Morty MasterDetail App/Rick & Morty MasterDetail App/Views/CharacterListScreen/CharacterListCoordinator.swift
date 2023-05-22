//
//  CharacterListCoordinator.swift
//  Rick & Morty MasterDetail App
//
//  Created by Miguel on 5/18/23.
//

import UIKit

final class CharacterListCoordinator {
    
    let navigationController: UINavigationController
    
    init(_ navigationController: UINavigationController) {
        
        self.navigationController = navigationController
    }
}

extension CharacterListCoordinator: Coordinator {
    
    func start() {
        
        let useCases = CharacterUseCases()
        let viewModel = CharacterListViewModel(useCases)
        viewModel.coordinator = self
        let characterListContainerViewController = CharacterListContainerViewController(viewModel)
        
        navigationController.pushViewController(characterListContainerViewController, animated: false)
    }
}

extension CharacterListCoordinator: CharacterListCoordinatorDelegate {
    
    func navigateToCharacterDetail() {
        // TODO: to create CharacterDetailScreen
    }
}
