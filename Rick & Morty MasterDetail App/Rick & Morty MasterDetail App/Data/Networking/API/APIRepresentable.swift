//
//  RickAndMortyAPIRepresentable.swift
//  Rick & Morty MasterDetail App
//
//  Created by Miguel on 5/18/23.
//

import Foundation

protocol APIRepresentable {

    var session: URLSession { get }
}

protocol CharacterAPIRepresentable: AnyObject, APIRepresentable {
    
    func fetchCharacters(page: String) async -> Result<[Character], Failure>
    
    func fetchSingleCharacter(id: String) async -> Result<Character, Failure>
}
