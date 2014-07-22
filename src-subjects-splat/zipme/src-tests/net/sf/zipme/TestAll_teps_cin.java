package net.sf.zipme;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import splat.ZipMeVariables;

public class TestAll_teps_cin extends ZipMeTest{
  
  @Override
  protected void configure() {
    // set mandatory features
    super.configure();
    if (testName == null) {
      throw new RuntimeException();
    }
    String strTestName = testName.getMethodName();
    if (strTestName.equals("T01")
        || strTestName.equals("T02")
        || strTestName.equals("T03")
        || strTestName.equals("T04")
        || strTestName.equals("T05")
        || strTestName.equals("T06")
        || strTestName.equals("T07")
        || strTestName.equals("T08")
        || strTestName.equals("T09")
        || strTestName.equals("T10")
        || strTestName.equals("T11")
        || strTestName.equals("T12")
        || strTestName.equals("T13")
        || strTestName.equals("T14")
        || strTestName.equals("T15")) {
//      ZipMeVariables.getSINGLETON().setCOMPRESS___(true);
//      ZipMeVariables.getSINGLETON().setGZIP___(true);
//      ZipMeVariables.getSINGLETON().setEXTRACT___(true);
//      ZipMeVariables.getSINGLETON().setARCHIVECHECK___(true);
//      ZipMeVariables.getSINGLETON().setCRC___(true);
//      ZipMeVariables.getSINGLETON().setDERIVATIVE_COMPRESS_CRC___(true);
//      ZipMeVariables.getSINGLETON().setDERIVATIVE_COMPRESS_GZIP___(true);
//      ZipMeVariables.getSINGLETON().setDERIVATIVE_COMPRESS_GZIPCRC___(true);
//      ZipMeVariables.getSINGLETON().setDERIVATIVE_EXTRACT_CRC___(true);
//      ZipMeVariables.getSINGLETON().setADLER32CHECKSUM___(true);
    } else {
      System.err.printf("%s did not set default configuration", strTestName);
    }
  }
	
	public static File fileToZip;
    public static File zipFile;
    public static File unzipFile;	
    public static ZipEntry zipEntry;
    public static Deflater deflater;
    public static Inflater inflater;
	public static Adler32 adler32;
	public static FileOutputStream fileOS;    	
	public static DeflaterOutputStream deflaterOS;		
    
    //Initial setup
    @Before
    public void setup() throws FileNotFoundException{
      super.setup();
    	fileToZip = new File("../zipme/src-tests/net/sf/zipme/file.txt");
    	zipFile = new File("../zipme/src-tests/net/sf/zipme/file.txt.zip");
    	unzipFile = new File("../zipme/src-tests/net/sf/zipme/file.txt-Copy.txt");
    	zipEntry = new ZipEntry(fileToZip.getPath());
    	deflater = new Deflater();
    	inflater = new Inflater();
    	adler32 = new Adler32();
        fileOS = new FileOutputStream(fileToZip);
    	deflaterOS = new DeflaterOutputStream(fileOS, deflater, 1);
    }
    
    //Tests
    // maybe repeated or nonsense
    @Test
	public void T01() {												        
	        zipEntry.setComment(null);	  	        
	}
	
    // maybe repeated or nonsense
    @Test
	public void T02() {											        
        zipEntry.setMethod(0);
	}
	    
    // maybe repeated or nonsense
    @Test
	public void T03() {											        
        Assert.assertEquals(-1, zipEntry.getSize());
	}
    
    // maybe repeated or nonsense
    @Test
	public void T04() {											        
        Assert.assertFalse(deflater.finished());        
	}
        
    // maybe repeated or nonsense
    @Test
	public void T05() {											        
        Assert.assertFalse(inflater.finished());                
	}
        
    // maybe repeated or nonsense
    @Test
	public void T06() {											         
        zipEntry.setTime(1);
        Assert.assertEquals(4039372800001L, zipEntry.getTime());
	}
    
//    // maybe repeated or nonsense
//    @Test
//	public void T07() {											        
//        byte[] buffer = new byte[0xFFFF];
//        
//        adler32.update(buffer);
//        Assert.assertEquals(917505, adler32.getValue());        
//	}
    
    // maybe repeated or nonsense
    @Test
	public void T08() {											    	
        zipEntry.setComment("test");
    	Assert.assertEquals("test", zipEntry.getComment());
	}
    
    // maybe repeated or nonsense
    @Test
	public void T09() {											    	
        Assert.assertFalse(zipEntry.isDirectory());        
	}
    
    @Test
	public void T10() {											    	
    	byte[] buffer = new byte[0xFFFF];
    	zipEntry.setExtra(buffer);
	}        
    
    @Test
	public void T11() {											
    	Assert.assertEquals(-1, zipEntry.getCrc());
	} 
    
    @Test
	public void T12() {											    	
    	zipEntry.setCrc(1);
	} 
    
    // maybe repeated or nonsense
    @Test
	public void T13() {
    	zipEntry.setSize(1);
	}
          
    // maybe repeated or nonsense
    @Test
    public void T14() throws IOException {    	
    	deflater.setLevel(0);
	}
       
    @Test
	public void T15() throws IOException {	    	
    	deflaterOS.finish();
    	deflaterOS.close();
	}            	
	
}