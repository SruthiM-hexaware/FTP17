package ftp17;


import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    // test for sum of numbers
    @Test
    public void testSum()
    {
    Calc obj = new Calc();
    System.out.println("add");
    assertEquals(7,obj.sum(2,5));
    }
   // test for maximum of 3 numbers
   @Test
    public void testMax()
    {
        System.out.println("max");
    Calc obj = new Calc();
    assertEquals(4,obj.max(2,3,4));
    }

//test for min in an array
   @Test
    public void testminArray()
    {
    System.out.println("min");
    Calc obi = new Calc();
    assertEquals(-3,obi.minArray(new int[]{-3,3,4,2}));
    assertEquals(1,obi.minArray(new int[]{1,3,4,2}));
    }
//test for even number
@Test
public void TestEven() {

    assertTrue(new Calc().even(20222));
    System.out.println("even");
}
//test for odd number
@Test
public void TestOdd() {
    assertFalse(new Calc().even(15));
    System.out.println("odd");
}
//test for null
@Test
public void testMap() {
    assertNotNull(new Calc().getPropertyTest("sai"));
System.out.println("notnull");
}
//test for notnull
@Test
public void testMapNull() {
    assertNull(new Calc().getPropertyTest("ram"));
System.out.println("null");
}
@Test(expected=ArithmeticException.class)
public void testDiv(){
     System.out.println("exception");
    int x=new Calc().div(5,0);
   
}

}
