package opgave02;

import bst.BSTreeSet;

import java.util.Comparator;

public class IntBSTreeSet extends BSTreeSet<Integer> {
    /**
     * Create a new, empty BSTreeSet, sorted according
     * to the specified comparator.
     *
     * @param comparator
     */
    public IntBSTreeSet(Comparator<? super Integer> comparator) {
        super(comparator);
    }

    public int sum() {
        return sum(root);
    }

    public int sum(TreeNode node) {
        if (node == null) return 0;
        return sum(node.left) + node.element + sum(node.right);
    }
}
