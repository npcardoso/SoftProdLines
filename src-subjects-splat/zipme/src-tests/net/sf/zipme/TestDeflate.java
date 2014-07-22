package net.sf.zipme;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDeflate extends ZipMeTest {

  @Test
  public void test0()  throws Throwable  {
      Deflater deflater0 = new Deflater(0);
      assertNotNull(deflater0);
      deflater0.setInput(new byte[]{0, 1, 2, 3, 4, 5, 6, 7});
      deflater0.end();
      assertEquals(127, deflater0.state);
  }
  
  @Test
  public void test1()  throws Throwable  {
      Deflater deflater0 = new Deflater();
      assertNotNull(deflater0);
      
      deflater0.totalOut = 1326L;
      int int0 = deflater0.getTotalOut();
      assertEquals(1326, deflater0.getTotalOut());
      assertEquals(1326, int0);
  }
  
  @Test
  public void test2()  throws Throwable  {
      Deflater deflater0 = new Deflater(0);
      assertNotNull(deflater0);
      
      deflater0.flush();
      assertEquals(4, deflater0.state);
      assertEquals(0L, deflater0.totalOut);
      assertEquals(0, deflater0.getTotalOut());
  }
  
  @Test
  public void test3()  throws Throwable  {
    Deflater deflater0 = new Deflater(0);
    assertNotNull(deflater0);
    deflater0.setInput(new byte[]{0, 1, 2, 3, 4, 5, 6, 7});
    deflater0.end();
    assertEquals(127, deflater0.state);
    
    deflater0.totalOut = 1326L;
    int int0 = deflater0.getTotalOut();
    assertEquals(1326, deflater0.getTotalOut());
    assertEquals(1326, int0);
    
    deflater0.flush();
    assertEquals(127, deflater0.state);
   
  }

}
