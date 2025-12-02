/*
 * Course: CSC-1120
 * Self-Balancing Binary Search Trees
 * BinarySearchTreeWithRotate
 * Name: Sean Jones
 * Last Updated:
 */
package week14;

import week9.BinarySearchTree;

/**
 * An implementation of rotations in a BST
 * @param <E> the element type stored in the tree
 */
public class BinarySearchTreeWithRotate<E extends Comparable<E>>
        extends BinarySearchTree<E> {
    protected Node<E> rotateLeft(Node<E> root) {
        Node<E> temp = root.right;
        root.right = temp.left;
        temp.left = root;
        return temp;
    }

    protected Node<E> rotateRight(Node<E> root) {
        Node<E> temp = root.left;
        root.left = temp.right;
        temp.right = root;
        return temp;
    }
}
