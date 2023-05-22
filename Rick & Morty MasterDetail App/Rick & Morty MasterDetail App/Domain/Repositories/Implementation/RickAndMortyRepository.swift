//
//  RickAndMortyRepository.swift
//  Rick & Morty MasterDetail App
//
//  Created by Miguel on 5/18/23.
//

import Foundation

enum RickAndMortyAPIs {
    case RickAndMortyMockApi
    case RickAndMortyApi
    
    var implementationClass: any APIRepresentable  {
        switch self {
        case .RickAndMortyMockApi: return MockAPIManager.shared
        case .RickAndMortyApi: return APIManager.shared
        }
    }
}

final class RickAndMortyRepository {
    
    internal var _api: RickAndMortyAPIs = .RickAndMortyApi
    
    init(_ api: RickAndMortyAPIs?) {
        
        if let api { self._api = api }
    }
}

extension RickAndMortyRepository: CharacterRepositoryRepresentable {
    
    var api:  CharacterAPIRepresentable {
        _api.implementationClass as! CharacterAPIRepresentable
    }
    
    func getCharacters(page: String) async -> Result<[Character], Failure> {
        
        return await api.fetchCharacters(page: page)
    }
    
    func getSingleCharacter(id: String) async -> Result<Character, Failure> {
        
        return await api.fetchSingleCharacter(id: id)
    }
}

