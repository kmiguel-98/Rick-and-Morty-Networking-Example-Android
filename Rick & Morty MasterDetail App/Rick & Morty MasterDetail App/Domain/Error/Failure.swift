//
//  Failure.swift
//  Rick & Morty MasterDetail App
//
//  Created by Miguel on 5/18/23.
//

import Foundation

enum Failure: Error {
    
    case decodingError
    case urlConstructError
    case APIError(Error)
    case repositoryError
    
    var title: String {
        "Servidor temporalmente no disponible"
    }
    
    var localizedDescription: String {
        "Estamos trabajando para resolver este problema, gracias por su paciencia."
    }
}
