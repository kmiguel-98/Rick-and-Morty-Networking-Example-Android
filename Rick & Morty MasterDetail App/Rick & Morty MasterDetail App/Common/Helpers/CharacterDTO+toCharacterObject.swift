//
//  CharacterDTO+toCharacterObject.swift
//  Rick & Morty MasterDetail App
//
//  Created by Miguel on 5/18/23.
//

import Foundation

extension CharacterDTO {
    
    func toCharacterModel() -> Character {
        
        return Character(
            id: self.id ?? Int.random(in: (3000...10000)),
            name: self.name ?? "",
            status: self.status ?? "",
            species: self.species ?? "",
            type: self.type ?? "",
            gender: self.gender ?? "",
            origin: self.origin?.toLocationModel() ?? Location(name: "", url: ""),
            location: self.location?.toLocationModel() ?? Location(name: "", url: ""),
            image: URL(string: self.image ?? ""),
            episode: self.episode ?? [],
            url: URL(string: self.url ?? ""),
            created: self.created ?? "")
    }
}
