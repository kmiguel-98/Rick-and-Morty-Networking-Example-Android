//
//  CharacterUseCases.swift
//  Rick & Morty MasterDetail App
//
//  Created by Miguel on 5/18/23.
//

import Foundation

final class CharacterUseCases {
    
    /*
     Here u can change the data origin
     */
    var api = RickAndMortyAPIs.RickAndMortyApi
    
    lazy var repository: RickAndMortyRepository = { RickAndMortyRepository(self.api) }()
    
    func getCharacters(page: String) async -> Result<[Character], Failure> {
        
        return await repository.getCharacters(page: page)
    }
    
    func getSingleCharacter(id: String) async -> Result<Character, Failure> {
        
        return await repository.getSingleCharacter(id: id)
    }
}
