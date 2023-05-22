//
//  CharacterResponse.swift
//  Rick & Morty MasterDetail App
//
//  Created by Miguel on 5/18/23.
//

import Foundation

struct Info: Codable {
    let count: Int?
    let pages: Int?
    let next: String?
    let prev: String?
}

struct CharacterResponse: Codable {
    let info: Info?
    let results: [CharacterDTO]?
}
