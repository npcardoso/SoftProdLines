package net.sf.zipme;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import splat.ZipMeVariables;

//import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

public class DerivGZIPEXTRTest2 {
  
  InputStream in;
  
  @Before
  public void setup() throws IOException {
    byte[] b = {0};
    in = new InputStream() {
      
      @Override
      public int read() throws IOException {
        // TODO Auto-generated method stub
        return 0;
      }
    };
    in.read(b);
  }


  @Test
  /*
   * Contains:
   * -net.sf.zipme.ZipInputStream.hook36()  
   * -net.sf.zipme.ZipInputStream.hook40()
   */
  public void testEXTRCRC() throws IOException{
    //Feature
    ZipMeVariables.getSINGLETON().setDERIVATIVE_EXTRACT_CRC___(true);
    ZipMeVariables.getSINGLETON().setCRC___(true);
    
//    InputStream in = new ByteInputStream(b, 1);
    ZipInputStream z = new ZipInputStream(in);

    //Tests
    z.crc.crc = 2;
    try {
      z.hook36();
    } catch (IOException e) {
     e.printStackTrace();
    }
    assertEquals(z.crc.crc, 0);
    
    try {
      z.hook40();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals(z.crc, null);
    
    
  }

  @Test (expected = IOException.class)
  /*
   * Method is able to throw exception with the feature DERIVATIVE_EXTRACT_CRC___
   * This test catches this exception
   */
  public void testCloseEntry() throws IOException{
    //Feature
    ZipMeVariables.getSINGLETON().setDERIVATIVE_EXTRACT_CRC___(true);

    //Variables
    byte[] b = {0};
//    ByteInputStream in = new ByteInputStream(b, 1);
    ZipInputStream z = new ZipInputStream(in);

    //Test
    z.crc = null;
    z.closeEntry();

  }

  @Test (expected = IOException.class)
  /*
   * Just like the last test
   * Method is able to throw exception with the feature DERIVATIVE_EXTRACT_CRC___
   * This test catches this exception
   */
  public void testGetNextEntry() throws IOException{
    //Feature
    ZipMeVariables.getSINGLETON().setDERIVATIVE_EXTRACT_CRC___(true);

    //Variables
    byte[] b = {0};
//    ByteInputStream in = new ByteInputStream(b, 1);
    ZipInputStream z = new ZipInputStream(in);

    //Test
    z.crc = null;
    z.getNextEntry();

  }
  
  @Test (expected = IOException.class)
  /*
   * Method is able to throw exception with the feature DERIVATIVE_EXTRACT_CRC___
   * This test catches this exception
   */
  public void testhook38() throws IOException{
    //Feature
    ZipMeVariables.getSINGLETON().setDERIVATIVE_EXTRACT_CRC___(true);

    //Variables
    byte[] b = {0};
//    ByteInputStream in = new ByteInputStream(b, 1);
    ZipInputStream z = new ZipInputStream(in);

    //Test
    z.crc = null;
    z.hook38();

  }

  @After
  public void teardown(){
//    ZipMeVariables.getSINGLETON().restore();

  }

}
