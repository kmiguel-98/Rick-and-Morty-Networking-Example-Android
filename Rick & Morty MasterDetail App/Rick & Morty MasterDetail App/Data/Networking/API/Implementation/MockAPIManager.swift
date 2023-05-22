//
//  MockAPIManager.swift
//  Rick & Morty MasterDetail App
//
//  Created by Miguel on 5/18/23.
//

import Foundation

final class MockAPIManager {
    
    static let shared = MockAPIManager()
    
    internal let session = URLSession.shared
    
    init() { }
}

extension MockAPIManager: CharacterAPIRepresentable {
    
    func fetchCharacters(page: String) async -> Result<[Character], Failure> {
        
        return .success([])
    }
    
    func fetchSingleCharacter(id: String) async -> Result<Character, Failure> {
        
        return .failure(Failure.decodingError)
    }
}
