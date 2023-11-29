import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class Examples {
    public BinaryTree someTree1;
    public BinaryTree someTree2;
    public BinaryTree someTree3;
    public IBinTree emptyTree;
    IBinTree mt = new EmptyBT();

    IBinTree tree1 = new NodeBT(7, new NodeBT(5, mt, mt),new NodeBT(4, mt, mt));
    IBinTree goodTree1Add = new NodeBT(7,
            new NodeBT(6,
                    new NodeBT(5, mt, mt),
                    mt),
            new NodeBT(4,mt, mt));
    IBinTree badTree1Add = new NodeBT(7,
            new NodeBT(0,
                    new NodeBT(5, mt, mt),
                    mt),
            new NodeBT(4,mt, mt));
    public Examples() {

       someTree1 = new BinaryTree(List.of(5,2,7,1,3,6,9));
       someTree2 = new BinaryTree(List.of(5,1));
       someTree3 = new BinaryTree(List.of(5,4,3,2,1));
       emptyTree = new EmptyBT();

    }
    //test valid working or not working
   @Test
   public void validateAddExistingTree() {
        MaxHeapValidator validator = new MaxHeapValidator();
        assertTrue(validator.validAdd(tree1,6, goodTree1Add ));
        assertFalse(validator.validAdd(tree1, 6, badTree1Add));

   }

    // contains
    @Test
    public void validateAddedContainsElt(){
        MaxHeapValidator validator = new MaxHeapValidator();
        assertTrue(validator.validAdd(mt, 3, new NodeBT(3, mt, mt)));
        assertFalse(validator.validAdd(mt, 3, new NodeBT(4, mt,mt)));
    }

    //Max at root
     @Test
    public void testMaxElementsAtRoot1() {
        BinaryTree tree = new BinaryTree(someTree1);
        tree.setValidator(new MaxHeapValidator());
        tree.setStrategy(new FaultyMaxHeapStrategy2());
        assertFalse(tree.addInt(4));
    }

    @Test
    public void testMaxElementsAtRoot2() {
        BinaryTree tree = new BinaryTree(someTree2);
        tree.setValidator(new MaxHeapValidator());
        assertTrue(tree.addInt(4));

    }

    //Add using a faulty strategy
    @Test
    public void testFaultyAdd1(){
        BinaryTree tree = new BinaryTree(someTree2);
        tree.setStrategy(new FaultyMaxHeapStrategy1());
        tree.setValidator(new MaxHeapValidator());
        assertFalse(tree.addInt(27));

    }

    @Test
    public void testAdd1(){
        BinaryTree tree = new BinaryTree(someTree2);
        tree.setStrategy(new MaxHeapStrategy2());
        tree.setValidator(new MaxHeapValidator());
        assertTrue(tree.addInt(27));

    }
    // Remove using faulty strategy
    @Test
    public void testFaultyRemove1(){
        MaxHeapValidator validator = new MaxHeapValidator();
        assertFalse(validator.validRemove(new NodeBT(4,new NodeBT(3,new NodeBT(2,emptyTree, emptyTree), emptyTree),emptyTree),
                4, new NodeBT(2,new NodeBT(3,emptyTree, emptyTree), emptyTree)));
    }

    @Test
    public void testFaultyRemove2(){
        MaxHeapValidator validator = new MaxHeapValidator();
        assertFalse(validator.validRemove(new NodeBT(4,new NodeBT(3,new NodeBT(2,emptyTree, emptyTree), emptyTree),emptyTree),
                4, new NodeBT(2,new NodeBT(3,emptyTree, emptyTree), emptyTree)));
    }
    @Test
    public void validateAddNonEmptyTree() {
        MaxHeapValidator validator = new MaxHeapValidator();
        IBinTree tree = new NodeBT(10, new NodeBT(8, mt, mt), new NodeBT(6, mt, mt));
        IBinTree expectedResult = new NodeBT(10, new NodeBT(8, new NodeBT(7, mt, mt), mt), new NodeBT(6, mt, mt));
        assertTrue(validator.validAdd(tree, 7, expectedResult));

        IBinTree badResult = new NodeBT(10, new NodeBT(7, new NodeBT(8, mt, mt), mt), new NodeBT(6, mt, mt));
        assertFalse(validator.validAdd(tree, 7, badResult));
    }
    @Test
    public void validateRemoveEmptyTree() {
        MaxHeapValidator validator = new MaxHeapValidator();
        assertFalse(validator.validRemove(mt, 5, mt));
    }
    @Test
    public void validateRemoveNonEmptyTree() {
        MaxHeapValidator validator = new MaxHeapValidator();
        IBinTree tree = new NodeBT(10, new NodeBT(8, mt, mt), new NodeBT(6, mt, mt));
        IBinTree expectedResult = new NodeBT(8, mt, new NodeBT(6, mt, mt));
        assertTrue(validator.validRemove(tree, 10, expectedResult));

        IBinTree badResult = new NodeBT(10, new NodeBT(6, mt, mt), new NodeBT(8, mt, mt));
        assertFalse(validator.validRemove(tree, 10, badResult));
    }


}
