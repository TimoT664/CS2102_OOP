/**
 * A default validator which says that all trees are valid
 */
public class DefaultBTValidator implements BTValidator {


    /**
     * A placeholder validator that says all adds are valid (even if they don't make sense)
     * @param oldTree the given tree we assume respects the invariants
     * @param i the element to add
     * @param newTree the new tree which we are validating
     * @return true always
     */
    @Override
    public boolean validAdd(IBinTree oldTree, int i, IBinTree newTree) {
        return true;
    }

    /**
     * A placeholder validator that says all removals are valid (even if they don't make sense)
     * @param oldTree the given tree we assume respects the invariants
     * @param i the element to remove
     * @param newTree the new tree which we are validating
     * @return true always
     */
    @Override
    public boolean validRemove(IBinTree oldTree, int i, IBinTree newTree) {
        return true;
    }
}
