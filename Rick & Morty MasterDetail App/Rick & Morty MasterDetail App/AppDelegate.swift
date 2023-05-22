//
//  AppDelegate.swift
//  Rick & Morty MasterDetail App
//
//  Created by User-T on 5/18/23.
//

import UIKit

@main
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?
    var navigationController = UINavigationController()
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
        let homeScreenCoordinator = CharacterListCoordinator(navigationController)
        window = UIWindow(frame: UIScreen.main.bounds)
        window?.rootViewController = homeScreenCoordinator.navigationController
        window?.makeKeyAndVisible()
        homeScreenCoordinator.start()
        return true
    }
}

