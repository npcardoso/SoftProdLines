package net.sf.zipme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import splat.ZipMeVariables;

public class GZIPtest {

  @Test
  /*
   * Contains:
   *  -net.sf.zipme.GZIPInputStream.close()                                            
   *  -net.sf.zipme.GZIPInputStream.GZIPInputStream(InputStream, int)
   *  -net.sf.zipme.GZIPInputStream.GZIPInputStream(InputStream)
   *  -net.sf.zipme.GZIPInputStream.readHeader()
   */
  public void CrDstTest() {
    ZipMeVariables.getSINGLETON().setGZIP___(true);
    
    int[] b = {-1};
    TestInputStream in = new TestInputStream(b);
    try{
      GZIPInputStream g = new GZIPInputStream(in);
      assertTrue(g.eos);
   
      Assert.assertNotNull(in.stream);
      g.close();
      assertEquals(in.stream, null);
    }catch(IOException e){
      System.out.println(e);
    }
  }

  @After
  public void teardown() {
    // ZipMeVariables.getSINGLETON().restore();
  }

}
