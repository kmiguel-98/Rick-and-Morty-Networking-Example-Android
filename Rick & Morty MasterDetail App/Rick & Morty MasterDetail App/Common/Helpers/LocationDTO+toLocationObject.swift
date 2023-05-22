//
//  LocationDTO+toLocationObject.swift
//  Rick & Morty MasterDetail App
//
//  Created by Miguel on 5/18/23.
//

import Foundation

extension LocationDTO {
    
    func toLocationModel() -> Location {
        return Location(name: self.name ?? "", url: self.url ?? "")
    }
}
