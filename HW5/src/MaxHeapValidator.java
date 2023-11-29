public class MaxHeapValidator implements BTValidator {

    @Override
    public boolean validAdd(IBinTree before, int elt, IBinTree after) {
        // Check if before tree does not contain elt, after tree does, and all elements in before are in after
        return !containsElt(before, elt) && containsElt(after, elt) && containsAll(before, after) &&
                containsOnlyElements(before, after, elt) && isValidMaxHeap(after);
    }

    @Override
    public boolean validRemove(IBinTree before, int elt, IBinTree after) {
        // Check if before tree contains elt, after tree does not, and all elements in after are in before
        return containsElt(before, elt) && !containsElt(after, elt) && containsAll(after, before) &&
                containsOnlyElements(after, before, elt) && isValidMaxHeap(after);
    }

    protected boolean containsElt(IBinTree tree, int elt) {
        if (tree instanceof EmptyBT) {
            return false;
        } else if (tree instanceof NodeBT) {
            NodeBT node = (NodeBT) tree;
            return node.getRoot() == elt || containsElt(node.getLeft(), elt) || containsElt(node.getRight(), elt);
        }
        return false;
    }

    protected boolean containsAll(IBinTree source, IBinTree target) {
        if (source instanceof EmptyBT) {
            return true;
        } else if (source instanceof NodeBT) {
            NodeBT node = (NodeBT) source;
            return containsElt(target, node.getRoot()) && containsAll(node.getLeft(), target) &&
                    containsAll(node.getRight(), target);
        }
        return false;
    }

    protected boolean containsOnlyElements(IBinTree source, IBinTree target, int additionalElt) {
        return containsAll(target, new NodeBT(additionalElt, source, new EmptyBT()));
    }

    protected boolean isValidMaxHeap(IBinTree tree) {
        if (tree instanceof EmptyBT) {
            return true;
        } else if (tree instanceof NodeBT) {
            NodeBT node = (NodeBT) tree;
            return (node.getLeft() instanceof EmptyBT || node.getRoot() >= ((NodeBT)node.getLeft()).getRoot()) &&
                    (node.getRight() instanceof EmptyBT || node.getRoot() >= ((NodeBT)node.getRight()).getRoot()) &&
                    isValidMaxHeap(node.getLeft()) && isValidMaxHeap(node.getRight());
        }
        return false;
    }
}
