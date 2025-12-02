/*
 * Course: CSC-1120
 * Binary Trees
 * BinaryTree
 * Name: Sean Jones
 * Last Updated: 10-28-25
 */
package week14;

import java.util.function.BiConsumer;

/**
 * Basic structure of a Binary Tree
 * @param <E> the element stored in the tree
 */
public class BinaryTree<E> {
    protected static class Node<E> {
        protected E data;
        protected Node<E> left;
        protected Node<E> right;

        protected Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    protected Node<E> root;

    /**
     * A constructor that creates an empty (null) root
     */
    public BinaryTree() {
        this.root = null;
    }

    /**
     * A constructor that creates a root node with data and null children
     * @param data the data stored in the root node
     */
    public BinaryTree(E data) {
        this.root = new Node<>(data);
    }

    /**
     * A constructor that uses a given node as the root node. For internal use only.
     * @param node the root node
     */
    protected BinaryTree(Node<E> node) {
        this.root = node;
    }

    /**
     * A constructor that builds a tree with data for the root node and existing
     * left and right subtrees
     * @param data the data stored in the root node
     * @param left the left subtree
     * @param right the right subtree
     */
    public BinaryTree(E data, BinaryTree<E> left, BinaryTree<E> right) {
        this.root = new Node<>(data);
        this.root.left = left.root;
        this.root.right = right.root;
    }

    /**
     * Returns the left subtree of this tree's root node
     * @return the left subtree
     */
    public BinaryTree<E> getLeftSubtree() {
        if(this.root == null || this.root.left == null) {
            return null;
        }
        return new BinaryTree<>(root.left);
    }

    /**
     * Returns the right subtree of this tree's root node
     * @return the right subtree
     */
    public BinaryTree<E> getRightSubtree() {
        if(this.root == null || this.root.right == null) {
            return null;
        }
        return new BinaryTree<>(root.right);
    }

    public boolean isLeaf() {
        return this.root.left == null && this.root.right == null;
    }

    public E getData() {
        return root == null ? null : root.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrderTraversal((e, i) -> {
            sb.append(e.toString()).append(" ");
        });
        return sb.toString().trim();
    }

    // traversal types: pre, in, post

    /**
     * Performs a preorder traversal on this tree
     * @param consumer the action performed when visiting each node
     */
    public void preOrderTraversal(BiConsumer<E, Integer> consumer) {
        preOrderTraversal(consumer, 1, this.root);
    }

    /**
     * Performs in inorder traversal on this tree
     * @param consumer the action performed when visiting each node
     */
    private void preOrderTraversal(BiConsumer<E, Integer> consumer, int depth, Node<E> node) {
        // Visit, Left, Right
        // Visit
        if(node != null) {
            consumer.accept(node.data, depth);
            // Left
            preOrderTraversal(consumer, depth + 1, node.left);
            // Right
            preOrderTraversal(consumer, depth + 1, node.right);
        }
    }

    /**
     * Performs an inorder traversal on this tree
     * @param consumer the action performed when visiting each node
     */
    public void inOrderTraversal(BiConsumer<E, Integer> consumer) {
        inOrderTraversal(consumer, 1, this.root);
    }

    private void inOrderTraversal(BiConsumer<E, Integer> consumer, int depth, Node<E> node) {
        // Left, Visit, Right
        // Visit
        if(node != null) {
            // Left
            inOrderTraversal(consumer, depth + 1, node.left);

            consumer.accept(node.data, depth);

            // Right
            inOrderTraversal(consumer, depth + 1, node.right);
        }
    }

    /**
     * Performs in postorder traversal on this tree
     * @param consumer the action performed when visiting each node
     */
    public void postOrderTraversal(BiConsumer<E, Integer> consumer) {
        postOrderTraversal(consumer, 1, this.root);
    }

    private void postOrderTraversal(BiConsumer<E, Integer> consumer, int depth, Node<E> node) {
        // Left, Visit, Right
        // Visit
        if(node != null) {
            // Left
            postOrderTraversal(consumer, depth + 1, node.left);
            // Right
            postOrderTraversal(consumer, depth + 1, node.right);

            consumer.accept(node.data, depth);
        }
    }
}
