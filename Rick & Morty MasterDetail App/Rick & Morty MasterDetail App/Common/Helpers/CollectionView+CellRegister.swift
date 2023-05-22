//
//  CollectionView+CellRegister.swift
//  Rick & Morty MasterDetail App
//
//  Created by Miguel on 5/18/23.
//

import UIKit

public protocol ReusableView: AnyObject { }

extension ReusableView {
    public static var reuseIdentifier: String {
        return String(describing: self)
    }
}

extension UICollectionReusableView: ReusableView { }

public protocol NibLoadable: AnyObject { }

extension NibLoadable {

    public static var nibName: String {
        return String(describing: self)
    }
}

extension UICollectionView {

    public func register<T: UICollectionViewCell>(_ cell: T.Type, bundle: Bundle? = nil) where T: NibLoadable {
        register(UINib(nibName: T.nibName, bundle: bundle), forCellWithReuseIdentifier: T.reuseIdentifier)
    }

    public func dequeueCell<T: UICollectionViewCell>(for indexPath: IndexPath) -> T {
        guard let cell = dequeueReusableCell(withReuseIdentifier: T.reuseIdentifier, for: indexPath) as? T else {
            preconditionFailure("Cell is not properly reusable")
        }
        return cell
    }
}
