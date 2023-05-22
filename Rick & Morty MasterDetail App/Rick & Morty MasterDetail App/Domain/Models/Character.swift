//
//  Character.swift
//  Rick & Morty MasterDetail App
//
//  Created by Miguel on 5/18/23.
//

import Foundation

struct Character: Hashable {
    let id: Int
    let name: String
    let status: String
    let species: String
    let type: String
    let gender: String
    let origin: Location
    let location: Location
    let image: URL?
    let episode: [String]
    let url: URL?
    let created: String
    
    static func == (lhs: Character, rhs: Character) -> Bool {
        
        return lhs.id == rhs.id
    }
    
    func hash(into hasher: inout Hasher) {
        
        hasher.combine(id)
    }
}
