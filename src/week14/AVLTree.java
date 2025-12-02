/*
 * Course: CSC-1120
 * Self-Balancing Trees
 * AVLTree
 * Name: Sean Jones
 * Last Updated: 12-02-25
 */
package week14;

/**
 * Basic AVLTree implementation
 * @param <E> the element type stored in the tree
 */
public class AVLTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E> {
    private static class AVLNode<E> extends Node<E> {
        private static final int BALANCED = 0;
        private static final int LEFT_HEAVY = -1;
        private static final int RIGHT_HEAVY = 1;

        private int balance;

        private AVLNode(E data) {
            super(data);
            this.balance = BALANCED;
        }

        public String toString() {
            return balance + ": " + super.toString();
        }
    }

    private boolean increase;
    private boolean decrease;

    @Override
    public boolean add(E item) {
        this.increase = false;
        this.root = add((AVLNode<E>) this.root, item);
        return addResult;
    }

    private Node<E> add(AVLNode<E> localRoot, E item) {
        // if the value doesn't exist, we will hit a null child node
        if(localRoot == null) {
            // add the node
            addResult = true; // set the result of add to true (we are adding)
            increase = true; // Because we are adding, a balance is changing
            return new AVLNode<>(item); // return the new node to its parent
        }
        // otherwise keep looking
        if(item.compareTo(localRoot.data) < 0) { // added data less than current data
            add((AVLNode<E>) localRoot.left, item); // go left
            if(increase) { // when recursion finishes, did we add?
                decrementBalance(localRoot); // we added to the left, so decrement current balance
            }
            // do I need to rebalance?
            if(localRoot.balance < AVLNode.LEFT_HEAVY) { // critically left imbalanced
                increase = false; // we are fixing the increase now
                return rebalanceLeft(localRoot); // do the rotations and balance updates
            }
        } else if (item.compareTo(localRoot.data) > 0) { // added data greater than current data
            add((AVLNode<E>) localRoot.right, item); // go right
            if(increase) { // when recursion finishes, did we add?
                incrementBalance(localRoot); // we added to the right, so increment current balance
            }
            // do I need to rebalance?
            if(localRoot.balance > AVLNode.RIGHT_HEAVY) { // critically right imbalanced
                increase = false; // we are fixing the increase now
                return rebalanceRight(localRoot); // do the rotations and balance updates
            }
        } else { // duplicate. Do not add.
            increase = false;
            addResult = false;
        }
        return localRoot; // no change to the tree
    }

    private void decrementBalance(AVLNode<E> localRoot) {
        --localRoot.balance;
        // if the root is balanced as a result, there will be no
        // critical imbalance
        if(localRoot.balance == AVLNode.BALANCED) {
            increase = false;
        }
    }

    private void incrementBalance(AVLNode<E> localRoot) {
        ++localRoot.balance;
        // if the root is balanced as a result, there will be no
        // critical imbalance
        if(localRoot.balance == AVLNode.BALANCED) {
            increase = false;
        }
    }

    private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {
        // how is it imbalanced? It is either L-L or L-R, since we already know
        // the root is left imbalanced
        if(((AVLNode<E>)localRoot.left).balance > AVLNode.BALANCED) {
            // The left child is right imbalanced, so we are L-R imbalanced

            // rotate L subtree left to make the tree L-L imbalanced
            updateLeftBalances(localRoot, (AVLNode<E>) localRoot.left); // fix the balances
            localRoot.left = rotateLeft(localRoot.left); // rotate the left subtree left
        } else {
            // The left child is left imbalanced, so we are L-L imbalanced
            localRoot.balance = AVLNode.BALANCED; // this will eventually make the tree balanced
            ((AVLNode<E>) localRoot.left).balance = AVLNode.BALANCED; // so we set them now
        }
        // To fix L-L imbalance, rotate the tree right
        return (AVLNode<E>) rotateRight(localRoot); // return the new root of this tree
    }

    private AVLNode<E> rebalanceRight(AVLNode<E> localRoot) {
        // how is it imbalanced? It is either R-R or R-L, since we already know
        // the root is right imbalanced
        if(((AVLNode<E>)localRoot.left).balance < AVLNode.BALANCED) {
            // The right child is left imbalanced, so we are R-L imbalanced

            // rotate R subtree right to make the tree R-R imbalanced
            updateRightBalances(localRoot, (AVLNode<E>) localRoot.right); // fix the balances
            localRoot.right = rotateRight(localRoot.right); // rotate the right subtree right
        } else {
            // The right child is right imbalanced, so we are R-R imbalanced
            localRoot.balance = AVLNode.BALANCED; // this will eventually make the tree balanced
            ((AVLNode<E>) localRoot.right).balance = AVLNode.BALANCED; // so we set them now
        }
        // To fix R-R imbalance, rotate the tree left
        return (AVLNode<E>) rotateRight(localRoot.right); // return the new root of this tree
    }

    private void updateLeftBalances(AVLNode<E> localRoot, AVLNode<E> leftChild) {
        // Get the left child's right child, as this is only called on an L-R imbalanced tree
        AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;
        // Three possibilities:
        if (leftRightChild.balance < AVLNode.BALANCED) {
            // The left child's right subtree is left imbalanced (L-R-L)
            leftChild.balance = AVLNode.BALANCED;
            leftRightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.RIGHT_HEAVY;
        } else if (leftRightChild.balance > AVLNode.BALANCED) {
            // The left child's right subtree is right imbalanced (L-R-R)
            leftChild.balance = AVLNode.LEFT_HEAVY;
            leftRightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        } else {
            // The left child's right subtree is balanced
            leftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
    }

    private void updateRightBalances(AVLNode<E> localRoot, AVLNode<E> rightChild) {
        // Get the right child's left child, as this is only called on an R-L imbalanced tree
        AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.right;
        // Three possibilities:
        if (rightLeftChild.balance > AVLNode.BALANCED) {
            // The right child's left subtree is right imbalanced (R-L-R)
            rightChild.balance = AVLNode.RIGHT_HEAVY;
            rightLeftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        } else if (rightLeftChild.balance < AVLNode.BALANCED) {
            // The right child's left subtree is left imbalanced (R-L-L)
            rightChild.balance = AVLNode.BALANCED;
            rightLeftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.LEFT_HEAVY;
        } else {
            // The right child's left subtree is balanced
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
    }

    @Override
    public E delete(E target) {
        this.decrease = false;
        this.root = delete((AVLNode<E>) this.root, target);
        return this.deleteReturn;
    }

    private AVLNode<E> delete(AVLNode<E> localRoot, E target) {
        // if the value doesn't exist, we hit a null node
        if(localRoot == null) {
            this.decrease = false;
            this.deleteReturn = null;
        } else if(localRoot.data.compareTo(target) < 0) { // go left
            localRoot.left = delete((AVLNode<E>) localRoot.left, target);
            if(decrease) { // we removed something
                incrementBalance(localRoot); // we removed from the left, so increment balance
                if (localRoot.balance > AVLNode.RIGHT_HEAVY) { // are we critically imbalanced?
                    localRoot = rebalanceRightLeft(localRoot); // rebalance
                }
            }
        } else if(localRoot.data.compareTo(target) > 0) { // go right
            localRoot.right = delete((AVLNode<E>) localRoot.right, target);
            if(decrease) { // we removed something
                decrementBalance(localRoot); // we removed from the right, so decrement
                if(localRoot.balance < AVLNode.LEFT_HEAVY) { // are we critically imbalanced?
                    localRoot = rebalanceLeftRight(localRoot); // rebalance
                }
            }
        } else { // this is the node to remove
            this.deleteReturn = localRoot.data;
            // find the node that will replace the deleted node
            localRoot = findReplacementNode(localRoot);
        }

        return localRoot;
    }

    private AVLNode<E> rebalanceRightLeft(AVLNode<E> localRoot) {
        AVLNode<E> rightChild = (AVLNode<E>) localRoot.right;
        if (rightChild.balance > AVLNode.BALANCED) {
            updateRightBalances(localRoot, rightChild);
            this.increase = false;
            this.decrease = true;
            localRoot.right = rotateRight(rightChild);
        } else if(rightChild.balance < AVLNode.BALANCED) {
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
            this.increase = false;
            this.decrease = true;
        } else {
            rightChild.balance = AVLNode.LEFT_HEAVY;
            localRoot.balance = AVLNode.RIGHT_HEAVY;
        }
        return (AVLNode<E>) rotateLeft(localRoot);
    }

    private AVLNode<E> rebalanceLeftRight(AVLNode<E> localRoot) {
        AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;
        if (leftChild.balance > AVLNode.BALANCED) {
            updateLeftBalances(localRoot, leftChild);
            this.increase = false;
            this.decrease = true;
            localRoot.left = rotateLeft(leftChild);
        } else if(leftChild.balance < AVLNode.BALANCED) {
            leftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
            this.increase = false;
            this.decrease = true;
        } else {
            leftChild.balance = AVLNode.RIGHT_HEAVY;
            localRoot.balance = AVLNode.LEFT_HEAVY;
        }
        return (AVLNode<E>) rotateRight(localRoot);
    }

    private AVLNode<E> findReplacementNode(AVLNode<E> node) {
        if(node.left == null) {
            this.decrease = true;
            return (AVLNode<E>) node.right;
        } else if(node.right == null) {
            this.decrease = true;
            return (AVLNode<E>) node.left;
        } else {
            // has two children. need to find the largest child in the left subtree
            if(node.left.right == null) {
                // if the left subtree has no right nodes, use the root of the subtree
                node.data = node.left.data;
                node.left = node.left.left;
                // removing from the left increases the balance
                incrementBalance(node);
            } else {
                // otherwise, go down the right of the subtree until you find the largest value
                node.data = findLargestChildValue((AVLNode<E>) node.left);
                // if removing the largest child makes the subtree critically unbalanced, rebalance
                if(((AVLNode<E>) node.left).balance < AVLNode.LEFT_HEAVY) {
                    node.left = rebalanceLeft((AVLNode<E>) node.left);
                }
                // if the height was decreased (on the left side), increment the balance
                if(this.decrease) {
                    incrementBalance(node);
                }
            }
        }
        return node;
    }

    private E findLargestChildValue(AVLNode<E> parent) {
        E returnValue;
        // if the right node is the last node, it is the largest
        if(parent.right.right == null) {
            returnValue = parent.right.data;
            parent.right = parent.right.left;
            decrementBalance(parent);
        } else {
            // recursively find the largest
            returnValue = findLargestChildValue((AVLNode<E>) parent.right);
            // if removing the node causes a critical imbalance, rebalance
            if(((AVLNode<E>) parent.right).balance < AVLNode.LEFT_HEAVY) {
                parent.right = rebalanceLeft((AVLNode<E>) parent.right);
            }
            // if the tree ended up decreasing (on the right side), decrement the balance
            if(this.decrease) {
                decrementBalance(parent);
            }
        }
        return returnValue;
    }
}
