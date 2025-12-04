/*
 * Course: CSC-1120
 * Red-Black Tree
 */
package week14;

import java.util.Objects;

/**
 * An implementation of a Red-Black Tree
 *
 * @param <E> the element type stored in the tree
 */
public class RedBlackTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E> {
    private static class RedBlackNode<E> extends Node<E> {
        private boolean red;

        private RedBlackNode(E item) {
            super(item);
            this.red = true;
        }

        @Override
        public String toString() {
            return (this.red ? "Red: " : "Black: ") + super.toString();
        }
    }

    private boolean blackReduced;

    @Override
    public boolean add(E item) {
        if (this.root == null) {
            this.root = new RedBlackNode<>(item);
            ((RedBlackNode<E>) this.root).red = false;
            this.addResult = true;
        } else {
            this.root = add((RedBlackNode<E>) this.root, item);
            ((RedBlackNode<E>) Objects.requireNonNull(this.root,
                    "Node must not be null")).red = false;
        }
        return this.addResult;
    }

    private RedBlackNode<E> add(RedBlackNode<E> localRoot, E item) {
        if (item.compareTo(localRoot.data) == 0) {
            this.addResult = false;
        } else if (item.compareTo(localRoot.data) < 0) {
            if (localRoot.left == null) {
                localRoot.left = new RedBlackNode<>(item);
                this.addResult = true;
            } else {
                // check for case 1 (parent and parent sibling are both red)
                if (localRoot.right != null &&
                        ((RedBlackNode<E>) localRoot.left).red &&
                        ((RedBlackNode<E>) localRoot.right).red) {
                    ((RedBlackNode<E>) localRoot.left).red = false;
                    ((RedBlackNode<E>) localRoot.right).red = false;
                    localRoot.red = true;
                }
                localRoot.left = add((RedBlackNode<E>) localRoot.left, item);
                // check for case 2 (L-L and both are red)
                if (((RedBlackNode<E>) Objects.requireNonNull(localRoot.left,
                        "Node must not be null")).red) {
                    if (localRoot.left.left != null &&
                            ((RedBlackNode<E>) localRoot.left.left).red) {
                        // rotate and change colors
                        ((RedBlackNode<E>) localRoot.left).red = false;
                        localRoot.red = true;
                        localRoot = (RedBlackNode<E>) rotateRight(localRoot);
                        // check for case 3 (L-R and both are red)
                    } else if (localRoot.left.right != null &&
                            ((RedBlackNode<E>) localRoot.left.right).red) {
                        // rotate parent left, rotate grandparent right, swap colors
                        localRoot.left = rotateLeft(localRoot.left);
                        ((RedBlackNode<E>) localRoot.left).red = false;
                        localRoot.red = true;
                        localRoot = (RedBlackNode<E>) rotateRight(localRoot);
                    }
                }
            }
        } else {// item.compareTo(localRoot.data) > 0  → go right
            if (localRoot.right == null) {
                localRoot.right = new RedBlackNode<>(item);
                this.addResult = true;
            } else {
                // case 1: parent and parent's sibling (left child) are both red
                if (localRoot.left != null &&
                        ((RedBlackNode<E>) localRoot.left).red &&
                        ((RedBlackNode<E>) localRoot.right).red) {
                    ((RedBlackNode<E>) localRoot.left).red = false;
                    ((RedBlackNode<E>) localRoot.right).red = false;
                    localRoot.red = true;
                }

                // recurse into right subtree
                localRoot.right = add((RedBlackNode<E>) localRoot.right, item);

                // after recursion, fix possible red-red violations on the right
                if (((RedBlackNode<E>) Objects.requireNonNull(localRoot.right,
                        "Node must not be null")).red) {
                    // case 2: R-R (right child and its right child are red)
                    if (localRoot.right.right != null &&
                            ((RedBlackNode<E>) localRoot.right.right).red) {
                        ((RedBlackNode<E>) localRoot.right).red = false;
                        localRoot.red = true;
                        localRoot = (RedBlackNode<E>) rotateLeft(localRoot);

                        // case 3: R-L (right child and its left child are red)
                    } else if (localRoot.right.left != null &&
                            ((RedBlackNode<E>) localRoot.right.left).red) {
                        localRoot.right = rotateRight(localRoot.right);
                        ((RedBlackNode<E>) localRoot.right).red = false;
                        localRoot.red = true;
                        localRoot = (RedBlackNode<E>) rotateLeft(localRoot);
                    }
                }
            }
        }
        return localRoot;
    }

    @Override
    public E delete(E item) {
        E oldValue = null;
        this.blackReduced = false;
        if (this.root != null) {
            if (item.compareTo(this.root.data) == 0) { // delete this node
                oldValue = this.root.data;
                this.root = findReplacement((RedBlackNode<E>) this.root);
                if (this.blackReduced && this.root != null) {
                    this.root = fixUpLeft((RedBlackNode<E>) this.root);
                }
            } else if (item.compareTo(this.root.data) < 0) {
                if (this.root.left != null) {
                    oldValue = removeFromLeft((RedBlackNode<E>) this.root, item);
                    if (this.blackReduced && this.root != null) {
                        this.root = fixUpLeft((RedBlackNode<E>) this.root);
                    }
                }
            } else {
                if (this.root.right != null) {
                    oldValue = removeFromRight((RedBlackNode<E>) this.root, item);
                    if (this.blackReduced && this.root != null) {
                        this.root = fixUpRight((RedBlackNode<E>) this.root);
                    }
                }
            }
        }
        return oldValue;
    }

    private RedBlackNode<E> findReplacement(RedBlackNode<E> node) {
        RedBlackNode<E> result = null;
        if (node.left == null) {
            if (node.red) {
                result = (RedBlackNode<E>) node.right;
            } else if (node.right == null) {
                this.blackReduced = true;
            } else if (((RedBlackNode<E>) node.right).red) {
                ((RedBlackNode<E>) node.right).red = false;
                result = (RedBlackNode<E>) node.right;
            } else {
                throw new IllegalStateException("Invalid Red-Black "
                        + "Tree Structure");
            }
        } else if (node.right == null) {
            if (node.red) {
                result = (RedBlackNode<E>) node.left;
            } else if (((RedBlackNode<E>) node.left).red) {
                ((RedBlackNode<E>) node.left).red = false;
                result = (RedBlackNode<E>) node.left;
            } else {
                throw new IllegalStateException("Invalid Red-Black "
                        + "Tree structure");
            }
        } else {
            if (node.left.right == null) {
                node.data = node.left.data;
                if (((RedBlackNode<E>) node.left).red) {
                    node.left = node.left.left;
                } else if (node.left.left == null) {
                    this.blackReduced = true;
                    node.left = null;
                } else if (((RedBlackNode<E>) node.left.left).red) {
                    ((RedBlackNode<E>) node.left.left).red = false;
                    node.left = node.left.left;
                } else {
                    throw new IllegalStateException("Invalid Red-Black "
                            + "Tree structure");
                }
                result = node;
            } else {
                node.data = findLargestChild((RedBlackNode<E>) node.left);
                if (this.blackReduced) {
                    node.left = fixUpRight((RedBlackNode<E>) node.left);
                }
                if (this.blackReduced) {
                    result = fixUpLeft(node);
                } else {
                    result = node;
                }
            }
        }
        return result;
    }

    private E findLargestChild(RedBlackNode<E> parent) {
        E returnValue;
        if (parent.right.right == null) {
            returnValue = parent.right.data;
            if (((RedBlackNode<E>) parent.right).red) {
                parent.right = parent.right.left;
            } else if (parent.right.left == null) {
                this.blackReduced = true;
                parent.right = null;
            } else if (((RedBlackNode<E>) parent.right.left).red) {
                ((RedBlackNode<E>) parent.right.left).red = false;
                parent.right = parent.right.left;
            } else {
                throw new IllegalStateException("Invalid Red-Black "
                        + "Tree structure");
            }
        } else {
            returnValue = findLargestChild((RedBlackNode<E>) parent.right);
            if (this.blackReduced) {
                parent.right = fixUpRight((RedBlackNode<E>) parent.right);
            }
        }
        return returnValue;
    }

    private E removeFromLeft(RedBlackNode<E> parent, E item) {
        E oldValue;
        if (item.compareTo(parent.left.data) < 0) {
            if (parent.left.left == null) {
                return null;
            } else {
                oldValue = removeFromLeft((RedBlackNode<E>) parent.left, item);
                if (this.blackReduced) {
                    parent.left = fixUpLeft((RedBlackNode<E>) parent.left);
                }
            }
        } else if (item.compareTo(parent.left.data) > 0) {
            if (parent.left.right == null) {
                return null;
            } else {
                oldValue = removeFromRight((RedBlackNode<E>) parent.left, item);
                if (this.blackReduced) {
                    parent.left = fixUpRight((RedBlackNode<E>) parent.left);
                }
            }
        } else {
            oldValue = parent.left.data;
            parent.left = findReplacement((RedBlackNode<E>) parent.left);
        }
        return oldValue;
    }

    private E removeFromRight(RedBlackNode<E> parent, E item) {
        E oldValue;
        if (item.compareTo(parent.right.data) < 0) {
            if (parent.right.left == null) {
                return null;
            } else {
                oldValue = removeFromLeft((RedBlackNode<E>) parent.right, item);
                if (this.blackReduced) {
                    parent.right = fixUpLeft((RedBlackNode<E>) parent.right);
                }
            }
        } else if (item.compareTo(parent.right.data) > 0) {
            if (parent.right.right == null) {
                return null;
            } else {
                oldValue = removeFromRight((RedBlackNode<E>) parent.right, item);
                if (this.blackReduced) {
                    parent.right = fixUpRight((RedBlackNode<E>) parent.right);
                }
            }
        } else {
            oldValue = parent.right.data;
            parent.right = findReplacement((RedBlackNode<E>) parent.right);
        }
        return oldValue;
    }

    private RedBlackNode<E> fixUpRight(RedBlackNode<E> localRoot) {
        RedBlackNode<E> result;
        if (localRoot.right != null
                && ((RedBlackNode<E>) localRoot.right).red) {
            ((RedBlackNode<E>) localRoot.right).red = false;
            this.blackReduced = false;
            return localRoot;
        }
        RedBlackNode<E> sibling = (RedBlackNode<E>) localRoot.left;
        if (sibling.red) {
            sibling.red = false;
            localRoot.red = true;
            RedBlackNode<E> returnValue = (RedBlackNode<E>) rotateRight(localRoot);
            returnValue.right = fixUpRight((RedBlackNode<E>) returnValue.right);
            if (this.blackReduced) {
                result = fixUpRight(returnValue);
            } else {
                result = returnValue;
            }
        } else {
            boolean leftBlackOrNull = sibling.left == null ||
                    !((RedBlackNode<E>) sibling.left).red;
            boolean rightBlackOrNull = sibling.right == null ||
                    !((RedBlackNode<E>) sibling.right).red;

            if (leftBlackOrNull && rightBlackOrNull) {
                sibling.red = true;
                result = localRoot;
            } else {
                if (sibling.right != null && ((RedBlackNode<E>) sibling.right).red) {
                    sibling.red = true;
                    ((RedBlackNode<E>) sibling.right).red = false;
                    localRoot.left = rotateLeft(sibling);
                    sibling = (RedBlackNode<E>) localRoot.left;
                }
                sibling.red = localRoot.red;
                assert sibling.left != null;
                ((RedBlackNode<E>) sibling.left).red = false;
                localRoot.red = false;
                this.blackReduced = false;
                result = (RedBlackNode<E>) rotateRight(localRoot);
            }
        }
        return result;
    }

    private RedBlackNode<E> fixUpLeft(RedBlackNode<E> localRoot) {
        RedBlackNode<E> result;
        if (localRoot.left != null
                && ((RedBlackNode<E>) localRoot.left).red) {
            ((RedBlackNode<E>) localRoot.left).red = false;
            this.blackReduced = false;
            return localRoot;
        }
        RedBlackNode<E> sibling = (RedBlackNode<E>) localRoot.right;
        if (sibling.red) {
            sibling.red = false;
            localRoot.red = true;
            Node<E> returnValue = rotateLeft(localRoot);
            returnValue.left = fixUpLeft((RedBlackNode<E>) returnValue.left);
            if (this.blackReduced) {
                result = fixUpLeft((RedBlackNode<E>) returnValue);
            } else {
                result = (RedBlackNode<E>) returnValue;
            }
        } else {
            boolean leftBlackOrNull = sibling.left == null ||
                    !((RedBlackNode<E>) sibling.left).red;
            boolean rightBlackOrNull = sibling.right == null ||
                    !((RedBlackNode<E>) sibling.right).red;

            if (leftBlackOrNull && rightBlackOrNull) {
                sibling.red = true;
                result = localRoot;
            } else {
                if (sibling.left != null && ((RedBlackNode<E>) sibling.left).red) {
                    sibling.red = true;
                    ((RedBlackNode<E>) sibling.left).red = false;
                    localRoot.right = rotateRight(sibling);
                    sibling = (RedBlackNode<E>) localRoot.right;
                }
                sibling.red = localRoot.red;
                assert sibling.right != null;
                ((RedBlackNode<E>) sibling.right).red = false;
                localRoot.red = false;
                this.blackReduced = false;
                result = (RedBlackNode<E>) rotateLeft(localRoot);
            }
        }
        return result;
    }
}

