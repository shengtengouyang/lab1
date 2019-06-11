import org.junit.Test;
import org.junit.Assert;

public class ArrayDequeTest {
//    @Test
//    public void addFirstTest(){
//        ArrayDeque<Integer> a=new ArrayDeque<>(5);
//        a.addFirst(6);
//        Integer [] b=new Integer[]{5,null,null,null,null,null,null,6};
//        Assert.assertArrayEquals(a.items,b);
//        a.addFirst(7);
//        Integer [] c=new Integer[]{5,null,null,null,null,null,7,6};
//        Assert.assertArrayEquals(a.items,c);
//        c[5]=1;
//        a.addFirst(1);
//        Assert.assertArrayEquals(a.items,c);
//        a.addFirst(1);
//        a.addFirst(1);
//        a.addFirst(1);
//        a.addFirst(1);
//        Integer [] d=new Integer[]{1,1,1,1,7,6,5,null,null,null,null,null,null,1};
//        Assert.assertArrayEquals(a.items,d);
//    }
//    @Test
//    public void addLastTest(){
//        ArrayDeque<Integer> a=new ArrayDeque<>(5);
//        a.addLast(1);
//        Integer [] b=new Integer[]{5,1,null,null,null,null,null,null};
//        Assert.assertArrayEquals(a.items,b);
//        a.addLast(2);
//        Integer [] c=new Integer[]{5,1,2,null,null,null,null,null};
//        Assert.assertArrayEquals(a.items,c);
//        c[3]=3;
//        a.addLast(3);
//        Assert.assertArrayEquals(a.items,c);
//        Integer [] d=new Integer[]{5,1,2,3,3,3,3,3,null,null,null,null,null,null};
//        a.addLast(3);
//        a.addLast(3);
//        a.addLast(3);
//        a.addLast(3);
//        Assert.assertArrayEquals(a.items,d);
//
//    }
//    @Test
//    public void removeFirstTest(){
//        ArrayDeque<Integer> a=new ArrayDeque<>();
//        Assert.assertNull(a.removeFirst());
//        a.addLast(1);
//        a.addLast(2);
//        a.addLast(3);
//        a.addLast(4);
//        a.addLast(5);
//        Integer[] b=new Integer[]{1,2,3,4,5,null,null,null};
//        a.removeFirst();
//        a.removeFirst();
//        a.removeFirst();
//        a.removeFirst();
//        Integer[] c=new Integer[]{5,null,null,null};
//        Assert.assertArrayEquals(a.items,c);
//        a.removeFirst();
//        Integer[] d=new Integer[]{null,null};
//        Assert.assertArrayEquals(a.items,d);
//    }
//    @Test
//    public void removeLastTest(){
//        ArrayDeque<Integer> a=new ArrayDeque<>();
//        Assert.assertNull(a.removeFirst());
//        a.addLast(1);
//        a.addLast(2);
//        a.addLast(3);
//        a.addLast(4);
//        a.addLast(5);
//        Integer[] b=new Integer[]{1,2,3,4,5,null,null,null};
//        Assert.assertArrayEquals(a.items,b);
//        a.removeLast();
//        a.removeLast();
//        a.removeLast();
//        a.removeLast();
//        Integer[] c=new Integer[]{1,null,null,null};
//        Assert.assertArrayEquals(a.items,c);
//    }
//    @Test
//    public void printDequeTest(){
//        ArrayDeque<Integer> a=new ArrayDeque<>(2);
//        a.addFirst(3);
//        a.addFirst(4);
//        a.addLast(5);
//        a.printDeque();
//    }
    @Test
    public void getTest(){
        ArrayDeque<Integer> a= new ArrayDeque<>();
        a.addLast(0);
        a.addLast(1);
        a.isEmpty();
        a.isEmpty();
        a.addLast(4);
        a.addLast(5);
        a.addLast(6);
        int b=a.removeFirst();
        Assert.assertEquals(0,b);
    }
}
