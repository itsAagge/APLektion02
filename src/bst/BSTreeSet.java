package bst;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * BSTreeSet is a binary search tree, sorted according to a comparator.
 * Note: Equal elements are not allowed in the tree. Equality of two elements is
 * decided by the comparator's compare() method.
 * Note: The methods add() and remove does not re-balance the tree.
 */
public class BSTreeSet<E> { // BSTreeSet corresponds to the class BST in the book
    protected TreeNode root;
    protected Comparator<? super E> comparator;
    private int size;

    /**
     * Create a new, empty BSTreeSet, sorted according
     * to the specified comparator.
     */
    public BSTreeSet(Comparator<? super E> comparator) {
        root = null;
        this.comparator = comparator;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    //-----------------------------------------------------

    // Return a (current, parent)-pair consisting of
    // the node containing the element e and this node's parent.
    // Return value:
    //   current != null and parent != null: current contains e and parent is the parent to current
    //   current != null and parent == null: current is the root containing e
    //   current == null and parent != null: e is not found
    //         and parent is the node where a node with e would be inserted as child
    //   current == null and parent == null: the three is empty
    private NodePair locateNodeAndParent(E e) {
        boolean nodeFound = false;
        TreeNode parent = null;
        TreeNode current = root;
        while (!nodeFound && current != null) {
            if (comparator.compare(e, current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (comparator.compare(e, current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                nodeFound = true;
            }
        }
        return new NodePair(current, parent);
    }

    private class NodePair {
        final TreeNode current;
        final TreeNode parent;

        public NodePair(TreeNode current, TreeNode parent) {
            this.current = current;
            this.parent = parent;
        }
    }

    //-----------------------------------------------------

    /**
     * Return true if the element e is in the BST.
     */
    public boolean contains(E e) { // this method is named search() in the book
        // Locate the node with the element e (and its parent node).
        NodePair pair = locateNodeAndParent(e);
        return pair.current != null;
    }


    /**
     * Add the element e to the tree.
     * Return true if the element is inserted successfully.
     * Return false if the element is found in the tree before insertion.
     */
    public boolean add(E e) { // this method is named insert() in the book
        // Locate the node with the element e and its parent node.
        NodePair pair = locateNodeAndParent(e);

        if (pair.current != null) return false; // duplicate element e not inserted
        // assertion: pair.current == null from here.

        if (pair.parent == null) { // the tree is empty
            root = new TreeNode(e);
            size++;
            return true;
        }
        // assertion: pair.current == null and pair.parent != null from here

        TreeNode parent = pair.parent;
        // Create a new node and attach it to the parent node.
        if (comparator.compare(e, parent.element) < 0) {
            parent.left = new TreeNode(e);
        } else {
            parent.right = new TreeNode(e);
        }
        size++;
        return true;
    }

    /**
     * Add all elements in the collection to the tree.
     */
    public void addAll(Collection<? extends E> collection) {
        for (E e : collection) {
            this.add(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb);
        return sb.toString();
    }

    private void toString(TreeNode node, StringBuilder sb) {
        if (node != null) {
            toString(node.left, sb);
            sb.append(node.element + " ");
            toString(node.right, sb);
        }
    }

    public String toStringPreorder() {
        return toStringPreorder(root);
    }

    private String toStringPreorder(TreeNode node) {
        if (node == null) return "";
        return node.element + " " + toStringPreorder(node.left) + " " + toStringPreorder(node.right);
    }

    public String toStringInorder() {
        return toStringInorder(root);
    }

    private String toStringInorder(TreeNode node) {
        if (node == null) return "";
        return toStringInorder(node.left) + " " + node.element + " " + toStringInorder(node.right);
    }

    public String toStringPostorder() {
        return toStringPostorder(root);
    }

    private String toStringPostorder(TreeNode node) {
        if (node == null) return "";
        return toStringPostorder(node.left) + " " + toStringPostorder(node.right) + " " + node.element;
    }

    //-----------------------------------------------------

    public ArrayList<E> toArrayInorder() {
        ArrayList<E> list = new ArrayList<>();
        toArrayInorder(root, list);
        return list;
    }

    private void toArrayInorder(TreeNode node, ArrayList<E> list) {
        if (node == null) return;
        toArrayInorder(node.left, list);
        list.add(node.element);
        toArrayInorder(node.right, list);
    }

    //-----------------------------------------------------

    public int greaterThanCount(E element) {
        return greaterThanSet(element).size();
    }

    public Set<E> greaterThanSet(E element) {
        ArrayList<E> list = toArrayInorder();
        Set<E> result = new LinkedHashSet<>();

        for (E e : list) {
            if (comparator.compare(e, element) > 0) {
                result.add(e);
            }
        }

        return result;
    }

    //-----------------------------------------------------

    public int sameHeightCount(int height) {
        return sameHeightCount(root, height);
    }

    public int sameHeightCount(TreeNode node, int height) {
        if (node == null) return 0;
        int nodeHeight = height(node);
        if (nodeHeight == height) return 1;
        else if (nodeHeight > height) {
            return sameHeightCount(node.left, height) + sameHeightCount(node.right, height);
        } else {
            return 0;
        }
    }

    //-----------------------------------------------------

    public int height() {
        return height(root);
    }

    private int height(TreeNode node) {
        if (node == null) return 0;
        int heightLeft = height(node.left);
        int heightRight = height(node.right);
        return Math.max(heightLeft, heightRight) + 1;
    }

    //-----------------------------------------------------

    public Set<E> toSet() {
        Set<E> set = new HashSet<>();
        toSet(root, set);
        return set;
    }

    public void toSet(TreeNode node, Set<E> set) {
        if (node != null) {
            toSet(node.left, set);
            set.add(node.element);
            toSet(node.right, set);
        }
    }

    //-----------------------------------------------------

    public int leafCount() {
        return leafCount(root);
    }

    private int leafCount(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return leafCount(node.left) + leafCount(node.right);
    }

    //-----------------------------------------------------

    public void printLayers() {
        ArrayList<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        printLayers(queue);
    }

    private void printLayers(ArrayList<TreeNode> queue) {
        for (int i = 0; i < height(); i++) {
            StringBuilder sb = new StringBuilder();
            ArrayList<TreeNode> children = new ArrayList<>();
            for (TreeNode treeNode : queue) {
                sb.append(treeNode.element + " ");
                if (treeNode.left != null) children.add(treeNode.left);
                if (treeNode.right != null) children.add(treeNode.right);
            }
            System.out.println(sb);
            queue.clear();
            queue.addAll(children);
        }
    }

    //-----------------------------------------------------

    public E max() {
        TreeNode node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node.element;
    }

    public void removeMin() {
        TreeNode parent = null;
        TreeNode current = root;

        while (current.left != null) {
            parent = current;
            current = current.left;
        }

        if (parent == null) {
            root = null;
        }

        if (current.right != null) {
            parent.left = current.right;
        } else {
            parent.left = null;
        }
    }

    //-----------------------------------------------------

    protected class TreeNode {
        public E element;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(E e) {
            element = e;
            left = null;
            right = null;
        }
    }

    //-----------------------------------------------------

    /**
     * Remove the element e from the tree.
     * Return true if the element is removed successfully.
     * Return false if the element is not found in the tree.
     */
    public boolean remove(E e) { // this method is named delete() in the book
        // Locate the node with the element e and its parent node.
        NodePair pair = locateNodeAndParent(e);

        if (pair.current == null) return false; // the element e is not in the tree
        // assertion: pair.current != null from here.

        TreeNode current = pair.current;
        TreeNode parent = pair.parent; // may be null
        // Case 1: current has no left child.
        if (current.left == null) {
            // Connect the parent with the right child of the current node.
            if (parent == null) { // current is the root
                root = current.right;
            } else {
                if (comparator.compare(e, parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        } else {
            // Case 2: The current node has a left child.

            // Locate the rightmost (biggest) node in the left subtree of
            // the current node and the parent of the rightmost node.
            TreeNode parentOfRightMost = current;
            TreeNode rightMost = current.left;
            if (rightMost.right == null) { // special case: no node to the right of rightMost
                current.element = rightMost.element;
                current.left = rightMost.left;
            } else {
                while (rightMost.right != null) {
                    parentOfRightMost = rightMost;
                    rightMost = rightMost.right; // keep going to the right
                }
                // Replace the element in current by the element in rightMost.
                current.element = rightMost.element;
                // Eliminate rightmost node.
                parentOfRightMost.right = rightMost.left;
            }
        }
        size--;
        return true;
    }
}
