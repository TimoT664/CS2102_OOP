package src;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



public class Examples {
    public BinaryTree someTree1;
    public BinaryTree someTree2;
    public BinaryTree someTree3;
    public IBinTree emptyTree;

    public Examples() {

       someTree1 = new BinaryTree(List.of(5,2,7,1,3,6,9));
       someTree2 = new BinaryTree(List.of(5,1));
       someTree3 = new BinaryTree(List.of(5,4,3,2,1));
       emptyTree = new EmptyBT();

    }
    //Max at root
     @Test
    public void testMaxElementsAtRoot1() {
        BinaryTree tree = new BinaryTree(someTree1);
        tree.setValidator(new MaxHeapValidator());
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
    //Remove using faulty strategy

}
