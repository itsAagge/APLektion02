package opgave03.ex3student;

import bst.BSTreeSet;

import java.util.*;

public class BSTreeMap<K extends Comparable<K>, V> implements MapI<K, V> {
    private TreeNode root;
    private Comparator<? super K> keyComparator;
    private int size;

    public BSTreeMap() {
        root = null;
        size = 0;
    }

    /**
     * Return the value corresponding to the key k.
     * Return null, if the key k is not in the map.
     */
    @Override
    public V get(K k) {
        if (root == null) return null;
        else return locateNodeAndParent(k).current.element.value();
    }

    /**
     * Insert the (key, value) pair in the map.
     * Return the old value, if the key k was in the map before this insertion.
     * If not, return null.
     */
    @Override
    public V put(K k, V v) {
        NodePair nodePair = locateNodeAndParent(k);
        if (nodePair.current != null) {
            return nodePair.current.element.value();
        }
        else {
            if (nodePair.parent == null) root = new TreeNode(k, v);
            else {
                if (k.compareTo(nodePair.parent.element.key) > 0) {
                    nodePair.parent.right = new TreeNode(k, v);
                } else {
                    nodePair.parent.left = new TreeNode(k, v);
                }
            }
            size++;
            return null;
        }
    }

    /**
     * Remove the (key, value) pair with the key k from the map.
     * Return the value, if the key k was in the map before this removal.
     * If not, return null.
     */
    @Override
    public V remove(K k) {
        if (root == null) return null;
        NodePair nodePair = locateNodeAndParent(k);
        if (nodePair.current == null) return null;
        else {
            TreeNode nodeToRemove = nodePair.current;
            TreeNode nodeLeftOfRemoved = nodePair.current.left;
            TreeNode nodeRightOfRemoved = nodePair.current.right;

            if (nodeToRemove == root) {
                root = nodeLeftOfRemoved;
                if (nodeRightOfRemoved != null) max(nodeLeftOfRemoved).right = nodeRightOfRemoved;
            } else if (nodeToRemove == nodePair.parent.left) {
                nodePair.parent.left = nodeLeftOfRemoved;
                if (nodeRightOfRemoved != null) max(nodeLeftOfRemoved).right = nodeRightOfRemoved;
            } else {
                nodePair.parent.right = nodeRightOfRemoved;
                if (nodeLeftOfRemoved != null) min(nodeLeftOfRemoved).left = nodeLeftOfRemoved;
            }
            size--;
            return nodeToRemove.element.value();
        }
    }

    private TreeNode max(TreeNode node) {
        while (node.right != null) node = node.right;
        return node;
    }

    private TreeNode min(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    /**
     * Return a set with all the keys in the map.
     */
    @Override
    public Set<K> keys() {
        Set<TreeNode> nodes = new HashSet<>();
        toSet(root, nodes);
        Set<K> keys = new HashSet<>();
        for (TreeNode node : nodes) {
            keys.add(node.element.key());
        }
        return keys;
    }

    public void toSet(TreeNode node, Set<TreeNode> set) {
        if (node == null) return;
        toSet(node.left, set);
        set.add(node);
        toSet(node.right, set);
    }

    /**
     * Return a list with all the values in the map.
     */
    @Override
    public List<V> values() {
        Set<TreeNode> nodes = new HashSet<>();
        toSet(root, nodes);
        List<V> values = new ArrayList<>();
        for (TreeNode node : nodes) {
            values.add(node.element.value());
        }
        return values;
    }

    /**
     * Return a set with all the entries in the map.
     */
    @Override
    public Set<MapI.Entry<K, V>> entries() {
        Set<TreeNode> nodes = new HashSet<>();
        toSet(root, nodes);
        Set<MapI.Entry<K, V>> entries = new HashSet<>();
        for (TreeNode node : nodes) {
            entries.add(node.element);
        }
        return entries;
    }

    /**
     * Return the number of (key,value) pairs in the map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Return true on an empty map, false on a non-empty map.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //-----------------------------------------------------

    private static class Entry<K, V> implements MapI.Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K key() {
            return key;
        }

        public V value() {
            return value;
        }

        @Override
        public String toString() {
            return String.format("(%s, %s)", key, value);
        }
    }

    //-----------------------------------------------------

    private class TreeNode {
        Entry<K, V> element;
        TreeNode left;
        TreeNode right;

        public TreeNode(K key, V value) {
            element = new Entry<>(key, value);
            left = null;
            right = null;
        }
    }

    //-----------------------------------------------------

    // Return a (current, parent) pair consisting of
    // the node containing the element e and this node's parent.
    // Return value:
    //   current != null and parent != null: current contains e and parent is the parent to current
    //   current != null and parent == null: current is the root containing e
    //   current == null and parent != null: e is not found
    //         and parent is the node where a node with e would be inserted as child
    //   current == null and parent == null: the three is empty
    private NodePair locateNodeAndParent(K key) {
        boolean nodeFound = false;
        TreeNode parent = null;
        TreeNode current = root;
        while (!nodeFound && current != null) {
            if (key.compareTo(current.element.key) < 0) {
                parent = current;
                current = current.left;
            } else if (key.compareTo(current.element.key) > 0) {
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

        public NodePair(TreeNode current, BSTreeMap.TreeNode parent) {
            this.current = current;
            this.parent = parent;
        }
    }
}
