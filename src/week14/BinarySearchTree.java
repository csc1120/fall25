/*
 * Course: CSC-1120
 * Binary Trees
 * BinarySearchTree
 * Name: Sean Jones
 * Last Updated: 11-4-25
 */
package week14;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A BST implementation that extends the BinaryTree class
 * @param <E> the element type store in the tree
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E>
        implements SearchTree<E>, Iterable<E> {
    private class Iter implements Iterator<E> {
        private final LinkedList<Node<E>> queue = new LinkedList<>();
        private Node<E> lastReturned;

        private Iter() {
            addNodes(BinarySearchTree.this.root);
            this.lastReturned = null;
        }

        private void addNodes(Node<E> node) {
            if(node != null) {
                addNodes(node.left);
                queue.offer(node);
                addNodes(node.right);
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public E next() {
            if(queue.isEmpty()) {
                throw new IllegalStateException();
            } else {
                this.lastReturned = this.queue.poll();
                return lastReturned.data;
            }
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }
    }

    /**
     * Stores the result of adding to the tree. This is needed because
     * the recursive add method needs to return a Node to be added
     * to the tree and cannot return a boolean.
     */
    protected boolean addResult;

    /**
     * Stores the result of removing a value from the tree. This is needed
     * because the recursive delete method needs to return a Node to replace
     * the deleted node and cannot return the data.
     */
    private E deleteReturn;

    @Override
    public boolean add(E e) {
        this.root = add(e, this.root);
        return this.addResult;
    }

    /**
     * Recursive add method that uses binary search to find the location to place
     * the new node. If the element already exists in the tree, no new node is added
     * to the tree.
     * The element returns either the new node or, if no new node is
     * created, either because there is a duplicate element or the node is merely
     * on the path towards location where the new element is stored, the current
     * node is returned
     * @param e the element to add to the tree
     * @param node the current node to compare the data to
     * @return a new node containing e if the current node is null, otherwise the
     * current node
     */
    private Node<E> add(E e, Node<E> node) {
        // base case
        if(node == null) {
            this.addResult = true;
            return new Node<>(e);
        }
        if(node.data.compareTo(e) == 0) {
            // no duplicate values
            this.addResult = false;
        } else if(node.data.compareTo(e) > 0) { // node.data is bigger
            // go left
            node.left = add(e, node.left);
        } else {
            // go right
            node.right = add(e, node.right);
        }
        return node;
    }

    @Override
    public boolean contains(E e) {
        return contains(e, this.root);
    }

    private boolean contains(E e, Node<E> node) {
        // base case
        if(node == null) {
            // not there
            return false;
        }
        if(node.data.compareTo(e) == 0) {
            // found it
            return true;
        }
        if(node.data.compareTo(e) > 0) {
            // go left
            return contains(e, node.left);
        } else {
            // go right
            return contains(e, node.right);
        }
    }

    @Override
    public E find(E target) {
        return find(target, this.root);
    }

    private E find(E target, Node<E> node) {
        // base case
        if(node == null) {
            // not there
            return null;
        }
        if(node.data.compareTo(target) == 0) {
            // found it
            return node.data;
        }
        if(node.data.compareTo(target) > 0) {
            // go left
            return find(target, node.left);
        } else {
            // go right
            return find(target, node.right);
        }
    }

    @Override
    public E delete(E e) {
        this.root = delete(e, this.root);
        return deleteReturn;
    }

    private Node<E> delete(E e, Node<E> node) {
        // base case
        if(node == null) {
            deleteReturn = null;
        } else if(node.data.compareTo(e) == 0) {
            deleteReturn = node.data;
            // remove
            // case 1 - left is null
            if(node.left == null) {
                return node.right; // either null or right child
            }
            if(node.right == null) {
                return node.left; // only left child
            }
            // case 3 - two children
            // find the largest value in left subtree
            // remove largest of the left subtree
            // replace value of the node being removed with the largest left subtree value
            node.data = findLargestChild(node);

        } else if (node.data.compareTo(e) > 0) {
            // go left
            return delete(e, node.left);
        } else {
            return delete(e, node.right);
        }
        return node;
    }

    /**
     * Traverses left, the right until it finds the furthers right node.
     * Removes this node and returns the data stored in the removed node
     * @param node the possible parent of the node with the largest value
     * @return the data from the removed node
     */
    private E findLargestChild(Node<E> node) {
        E result;
        // base case
        if(node.right.right == null) { // largest value
            result = node.right.data;
            node.right = node.right.left; // removed node
        } else {
            // keep looking
            result = findLargestChild(node.right);
        }
        return result;
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public List<E> toList() {
        return List.of();
    }

    /**
     * Returns a new iterator that will return values in-order
     * @return a new iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }
}
