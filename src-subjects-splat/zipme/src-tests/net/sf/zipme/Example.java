package net.sf.zipme;

import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import splat.ZipMeVariables;

public class Example extends ZipMeTest{
  
  //gets the project path
  static String homeDir;
  static {
    try {
      homeDir = (new File(".")).getCanonicalPath();
    } catch (IOException e) {
      e.printStackTrace();
    }
  };

  @Before
  public void setup(){
    
  }
  
//  /**
//   * This test covers the 2 methods which access ARCHIVECHECK___
//   * Satisfies: requirement1, requirement2, and requirement3.
//   */
//  @Test
//  public void testARCHIVECHECK() throws ZipException, IOException {
//    System.out.println(">>>>>testARCHIVECHECK2<<<<<");
//    FileInputStream zipFile = new FileInputStream(new File(homeDir + "/files/file.gz"));
//    ZipArchive zipArchive = new ZipArchive(zipFile);
//    
//    //checkZipArchive() checks if the file is valid
//    //ZipArchive calls hook1() that calls checkZipArchive()
//    //If the test reaches this point with ARCHIVECHECK disabled and 
//    // an invalid file, the size() will return 0;
//    assertFalse(zipArchive.size() > 0);
//  }
//  
  /**
   * This test covers the 1 method that accesses DERIVATIVE_COMPRESS_CRC___ 
   * Satisfies: requirement1 for method hook41(), requirement2, and requirement3.
   */
  @Test
  public void testDERIVATIVE_COMPRESS_CRC() throws IOException{
    System.out.println(">>>>>testDERIVATIVE_COMPRESS_CRC1<<<<<");
    ZipMeVariables.getSINGLETON().setCRC___(true);
    ZipMeVariables.getSINGLETON().setCOMPRESS___(true);
    ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(new File(
        homeDir + "/files/src.zip")));
    zout.crc.update(1);
    ZipEntry ze = new ZipEntry("C://");
    zout.putNextEntry(ze);
    
    //This happens because the test does not reach 
    //hook41() that resets the crc value, because 
    //DERIVATIVE_COMPRESS_CRC___ is disabled.
    assertFalse(zout.crc.crc == 0); 
  }

  @After
  public void tearDown(){
    ZipMeVariables.getSINGLETON().restore();
  }
}
