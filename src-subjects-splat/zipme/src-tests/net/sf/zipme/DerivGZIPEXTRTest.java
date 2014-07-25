package net.sf.zipme;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import splat.ZipMeVariables;

//import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

public class DerivGZIPEXTRTest {
  
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
   *  -net.sf.zipme.ZipInputStream.hook()                                   
   *  -net.sf.zipme.ZipInputStream.hook2(int)  
   *  -net.sf.zipme.ZipInputStream.hook30(byte[], int, int)                  
   */
  public void testGZICRC() {
    //Features
    ZipMeVariables.getSINGLETON().setDERIVATIVE_GZIPCRC___(true);
    ZipMeVariables.getSINGLETON().setCRC___(true);
    
    //Variables
    byte[] b = {0};
//    ByteInputStream in = new ByteInputStream(b, 1);
    ZipInputStream z = new ZipInputStream(in);
    
    //Tests
    //z.crc = null;         //before hook()
    z.hook();
    assertEquals(z.crc.crc, 0); //after hook()
    
    z.headCRC = new CRC32();        //HeadCRC needs to be initialized
    assertEquals(z.headCRC.crc, 0); //Before hook2()
    z.hook2(0);           
    Assert.assertEquals(z.headCRC.crc, -771559539);  //After hook2() the value changed
    
    assertEquals(z.crc.crc, 0);     //Value before hook30()
    try {
      z.hook30(b, 0, 1);
    } catch (IOException e) {
      e.printStackTrace();
    }
    Assert.assertEquals(z.crc.crc, -771559539);  //Value changed
    
  }
  
  @Test (expected = IOException.class)
  /*
   * The method hook3() won't do anything special other than throwing an exception
   */
  public void hook3Test() throws IOException {
    //Features
    ZipMeVariables.getSINGLETON().setDERIVATIVE_GZIPCRC___(true);
    ZipMeVariables.getSINGLETON().setCRC___(true);
    
    //Variable
    byte[] b = {0};
//    ByteInputStream in = new ByteInputStream(b, 1);
    ZipInputStream z = new ZipInputStream(in);
    
    z.headCRC = new CRC32(); //headCRC needs to be initialized
    assertEquals(z.headCRC.crc, 0);
    z.hook3(1);              //Throws exception if parameter is different than headCRC.crc
  }
  
  @Test (expected = IOException.class)
  /*
   * The method hook4() won't do anything special other than throwing an exception,
   * its functionality is very similar to hook3()'s
   */
  public void hook4Test() throws IOException {
    //Features
    ZipMeVariables.getSINGLETON().setDERIVATIVE_GZIPCRC___(true);
    ZipMeVariables.getSINGLETON().setCRC___(true);
    
    //Variables
    byte[] b = {0};
//    ByteInputStream in = new ByteInputStream(b, 1);
    ZipInputStream z = new ZipInputStream(in);
    
    z.headCRC = new CRC32(); //headCRC needs to be initialized
    assertEquals(z.headCRC.crc, 0);
    z.hook4(1);              //Throws exception if parameter is different than headCRC.crc
  }


  @After
  public void teardown(){
//    ZipMeVariables.getSINGLETON().restore();
  }
}
