//
//  RepositoryRepresentable.swift
//  Rick & Morty MasterDetail App
//
//  Created by Miguel on 5/18/23.
//

import Foundation

protocol RepositoryRepresentable { }

protocol CharacterRepositoryRepresentable: AnyObject, RepositoryRepresentable {
    
    var _api: RickAndMortyAPIs { get set }
    
    func  getCharacters(page: String) async -> Result<[Character], Failure>
    
    func  getSingleCharacter(id: String) async -> Result<Character, Failure>
}
