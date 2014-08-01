package net.sf.zipme;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import splat.ZipMeVariables;

//import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class LittleFeatures {

  OutputStream o;
  
  @Before
  public void setup() throws IOException {
    byte[] b = {0};
    o = new OutputStream() {
      
      @Override
      public void write(int b) throws IOException {
        // TODO Auto-generated method stub
        
      }
    };
  }
  
  
  @Test (expected = ZipException.class)
  public void ArchiveCheckTest()throws ZipException {
    ZipMeVariables.getSINGLETON().setARCHIVECHECK___(true);
    byte[] b = {0, 1, 2, 3};

    ZipArchive z = new ZipArchive(b);
  }
  
  @Test
  /*
   * Contains:
   * -net.sf.zipme.CRC32.getValue()
   * -net.sf.zipme.CRC32.reset() 
   * -net.sf.zipme.CRC32.make_crc_table()
   * -net.sf.zipme.CRC32.update(byte[], int, int) 
   * -net.sf.zipme.ZipOutputStream.hook41()                                       
   * -net.sf.zipme.ZipOutputStream.hook42()                                       
   * -net.sf.zipme.ZipOutputStream.hook43(byte[], int, int)
   */
  public void DerivativeCompCRCTest(){
    ZipMeVariables.getSINGLETON().setDERIVATIVE_COMPRESS_CRC___(true); //Main Feature
    ZipMeVariables.getSINGLETON().setCRC___(true);                     //Feature Needed to test reset() and update()
    ZipMeVariables.getSINGLETON().setCOMPRESS___(true);                //Feature needed to initialize ZipEntry
    
//    OutputStream o = new ByteOutputStream();
    ZipOutputStream z = new ZipOutputStream(o);
    ZipEntry ze = new ZipEntry("test");
    byte[] b = {7};
    
    z.crc.crc = 21;
    try{
      z.putNextEntry(ze);
      assertEquals(z.crc.getValue(), 0);
      z.crc.crc = 30;
      assertEquals(z.curEntry.getCrc(), -1);
      z.hook42();
      assertEquals(z.curEntry.getCrc(), 30);
      z.crc.crc = 29;
      z.hook43(b, 0, 1);
      assertEquals(z.crc.getValue(), 794826487);
      z.hook41();
      assertEquals(z.crc.getValue(), 0);
    }catch(Exception e){
      System.out.println(e);
    }
     
  }

  @Test
  /*
   * Contains:
   *  -net.sf.zipme.GZIPOutputStream.hook()  
   *  -net.sf.zipme.GZIPOutputStream.hook31(byte[], int, int)  
   *  -net.sf.zipme.GZIPOutputStream_hook22.execute()
   *  -net.sf.zipme.GZIPOutputStream_hook22.GZIPOutputStream_hook22(GZIPOutputStream, byte[])
   *  
   */
  public void GZIPCRCderivativeTest(){
    //Features
    ZipMeVariables.getSINGLETON().setDERIVATIVE_COMPRESS_GZIPCRC___(true);
    ZipMeVariables.getSINGLETON().setCRC___(true);
    ZipMeVariables.getSINGLETON().setDERIVATIVE_COMPRESS_GZIP___(true);
    
    //Setting Variables
    byte[] b = {1, 2, 3, 4, 5};
//    ByteOutputStream out = new ByteOutputStream();
    GZIPOutputStream gz;
    
    try {
      gz = new GZIPOutputStream(o);
    } catch (IOException e) {
      gz = null;
      e.printStackTrace();
    }
    
    //Tests hook()
    gz.hook();
    assertNotNull(gz.crc);
    
    //Tests hook22 related methods
    GZIPOutputStream_hook22 h = new GZIPOutputStream_hook22(gz, b);
    assertEquals(gz, h._this);
    assertEquals(h.gzipFooter, b);
    h.execute();
    assertEquals(h.crcval, gz.crc.crc);  
    
    //Tests hook31()
    try {
      gz.crc.crc = 2;
      gz.hook31(b, 0, 1);
      assertEquals(gz.crc.getValue(), 1007455905); //Value changed, as expected
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
  
  private Object not(boolean equals) {
    // TODO Auto-generated method stub
    return null;
  }

  @Test
  public void CRCupdates(){
    ZipMeVariables.getSINGLETON().setCRC___(true);
    
//    OutputStream o = new ByteOutputStream();
    ZipOutputStream z = new ZipOutputStream(o);
    
    z.crc.crc = 3;
    z.crc.update(0);
    assertEquals(z.crc.getValue(), 1259060791);
    assertEquals(z.crc.getValue(), 1259060791);
  }
  
  
  @After
  public void teardown(){
//    ZipMeVariables.getSINGLETON().restore();
  }
}
