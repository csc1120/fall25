/*
 * Course: CSC-1120
 * Binary Trees
 * Search Tree
 * Name: Sean Jones
 * Last Updated: 10-28-25
 */
package week9;

import java.util.List;

/**
 * An interface for all search trees (BST, AVL, Red-Black, etc.)
 * @param <E> the element type stored in the tree
 */
public interface SearchTree<E> {
    /**
     * Adds an element to the tree in the specified order based on the type of tree.
     * @param e the element to add to the tree
     * @return true if the element is added, false otherwise
     */
    boolean add(E e);

    /**
     * Looks for a given element and returns whether the element exists in the tree
     * @param e the element to search for
     * @return true if the element is in the tree, false otherwise
     */
    boolean contains(E e);

    /**
     * Looks for a given value and returns the element with that value
     * @param target the value to find
     * @return the element with the given value or null if the value is not in the tree
     */
    E find(E target);

    /**
     * Removes an element from the tree
     * @param e the element to remove from the tree
     * @return the removed element or null if the element does not exist in the tree
     */
    E delete(E e);

    /**
     * Empties the list of all elements
     */
    void clear();

    /**
     * Creates a List of the elements contained in the tree using an inorder traversal
     * to maintain the order of the elements
     * @return a List containing the elements of the tree, with the order preserved
     */
    List<E> toList();
}
