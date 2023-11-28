
/**
 * Class MaxHeapValidator implements BTValidator interface.
 * This class is used to validate the operations of adding and removing elements
 * in a binary tree structured as a max heap.
 */
public class MaxHeapValidator implements BTValidator {

    /**
     * Validates the addition of an element to a max heap.
     *
     * @param oldTree The binary tree before the addition, assumed to be a valid max heap.
     * @param elt The integer element being added to the tree.
     * @param newTree The binary tree after the addition.
     * @return true if the newTree is a valid max heap after the addition of elt, false otherwise.
     */
    @Override
    public boolean validAdd(IBinTree oldTree, int elt, IBinTree newTree) {
        return isMaxHeap(newTree) && newTree.contains(elt);
    }

    /**
     * Validates the removal of an element from a max heap.
     *
     * @param oldTree The binary tree before the removal, assumed to be a valid max heap.
     * @param elt The integer element being removed from the tree.
     * @param newTree The binary tree after the removal.
     * @return true if the newTree is a valid max heap after the removal of elt, false otherwise.
     */
    @Override
    public boolean validRemove(IBinTree oldTree, int elt, IBinTree newTree) {
        return isMaxHeap(newTree) && !newTree.contains(elt);
    }

    /**
     * Checks if a given binary tree is a max heap.
     *
     * @param tree The binary tree to be checked.
     * @return true if the tree satisfies max heap properties, false otherwise.
     */
    private boolean isMaxHeap(IBinTree tree) {
        if (tree instanceof EmptyBT) {
            return true;
        }
        if (tree instanceof NodeBT) {
            NodeBT node = (NodeBT) tree;
            return isNodeMaxHeap(node, node.data) && isMaxHeap(node.left) && isMaxHeap(node.right);
        }
        return false;
    }

    /**
     * Helper method to check if a node and its children satisfy max heap properties.
     *
     * @param node The node to be checked.
     * @param parentValue The value of the parent node to compare with.
     * @return true if the node and its children maintain the max heap structure, false otherwise.
     */
    private boolean isNodeMaxHeap(NodeBT node, int parentValue) {
        boolean leftChildValid = (node.left instanceof EmptyBT) || (node.left.data <= parentValue);
        boolean rightChildValid = (node.right instanceof EmptyBT) || (node.right.data <= parentValue);

        return leftChildValid && rightChildValid;
    }
}

