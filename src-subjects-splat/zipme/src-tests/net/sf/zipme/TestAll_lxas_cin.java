package net.sf.zipme;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import splat.ZipMeVariables;

public class TestAll_lxas_cin extends ZipMeTest{
  
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
        || strTestName.equals("T15")
        || strTestName.equals("T16") 
        || strTestName.equals("T17")) {
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
  
    private static File fileToZip = new File("../zipme/src-tests/net/sf/zipme/file.txt");
    private static File zipFile = new File("../zipme/src-tests/net/sf/zipme/file.gz");
    private static File zipFileSanity = new File("../zipme/src-tests/net/sf/zipme/banco.gz");

    @Test
	public void T01() throws FileNotFoundException {
		ZipOutputStream outStream = new ZipOutputStream(new FileOutputStream(zipFile));
		outStream.setLevel(-1);
	}
    
    @Test
	public void T02() throws FileNotFoundException {
    	ZipOutputStream outStream = new ZipOutputStream(new FileOutputStream(zipFile));
		outStream.setMethod(0);
	}
    @Test
	public void T03() throws IOException {
		ZipOutputStream outStream = new ZipOutputStream(new FileOutputStream(zipFile));
		ZipEntry zipEntry = new ZipEntry(fileToZip.getPath());
		zipEntry.setSize(10L);
		zipEntry.setCrc(928739L);
		zipEntry.setCompressedSize(10L);
		outStream.putNextEntry(zipEntry);
	}
    
    @Test
	public void T04() throws Exception {
		ZipOutputStream outStream = new ZipOutputStream(new FileOutputStream(zipFile));
		outStream.setComment("Comentario com tamanho valido");
	}
    
    @Test(expected = ZipException.class)
	public void T05() throws IOException {
    	ZipOutputStream outStream = new ZipOutputStream(new FileOutputStream(zipFile));
		ZipEntry entry = new ZipEntry("entry");
		entry.setMethod(0);
		entry.setSize(10L);
		outStream.putNextEntry(entry);
	}
    
    @Test(expected = ZipException.class)
	public void T06() throws IOException {
    	ZipOutputStream outStream = new ZipOutputStream(new FileOutputStream(zipFile));
		ZipEntry entry = new ZipEntry("entry");
		entry.setMethod(0);
		entry.setCrc(928739L);
		outStream.putNextEntry(entry);
	}
    
    @Test(expected = ZipException.class)
	public void T07() throws IOException {
		ZipOutputStream outStream = new ZipOutputStream(new FileOutputStream(zipFile));
		ZipEntry entry = new ZipEntry("entry");
		entry.setMethod(0);
		entry.setSize(10L);
		entry.setCrc(928739L);
		entry.setCompressedSize(0L);
		outStream.putNextEntry(entry);
	}
    
    @Test
	public void T08() throws IOException {
        FileInputStream inStream = new FileInputStream(zipFileSanity);
        ZipArchive zipArchive = new ZipArchive(inStream);
        zipArchive.hook1();
        ZipEntry entry = zipArchive.getEntry("Banco/.classpath");
        
        Assert.assertTrue(entry.getSize() >= entry.getCompressedSize());
	}
        
    @Test
    public void T09() throws IOException {
        FileInputStream inStream = new FileInputStream(zipFileSanity);
        ZipArchive zipArchive = new ZipArchive(inStream);
        zipArchive.hook1();
        int size = zipArchive.size();
        
        Assert.assertTrue(size > 0);
    }
    
    @Test
    public void T10() throws IOException {
    	Deflater d = new Deflater(Deflater.BEST_COMPRESSION);
    	
    	Assert.assertEquals(Deflater.BEST_COMPRESSION, d.level);
    }
    
    @Test
    public void T11() throws IOException {
    	//TODO
    	Deflater d = new Deflater(Deflater.BEST_COMPRESSION);
    	
    	byte[] output = new byte[10];
		int deflate = d.deflate(output);
		
		Assert.assertTrue(deflate > 0);
		
		deflate = d.deflate(output);
		
		Assert.assertTrue(deflate == 0);
    }
    
    @Test
    public void T12() throws DataFormatException {
    	Inflater inflater = new Inflater();
    	byte[] buf = new byte[10]; // Empty byte array should return 0
    	int inflate = inflater.inflate(buf);
		
		Assert.assertTrue(inflate == 0);
    }
    
    // maybe repeated or nonsense
    @Test
    public void T13() throws DataFormatException {
    	Inflater inflater = new Inflater();
    	boolean needsDictionary = inflater.needsDictionary();
		
		Assert.assertFalse(needsDictionary);
    }
    
    @Test
    public void T14() throws DataFormatException {
    	Inflater inflater = new Inflater();
    	inflater.end();
    	boolean finished = inflater.finished();
		
		Assert.assertFalse(finished);
    }
    
    @Test(expected = NullPointerException.class)
    public void T15() throws DataFormatException {
    	Inflater inflater = new Inflater();
    	inflater.end();
    	
    	inflater.getAdler();
    }
    
    @Test
    public void T16() throws IOException {
        FileInputStream inStream = new FileInputStream(zipFileSanity);
        InflaterInputStream inflater = new InflaterInputStream(inStream);
        int available = inflater.available();
        
        Assert.assertEquals(1, available);
    }
    
    @Test(expected = ZipException.class)
    public void T17() throws IOException {
        FileInputStream inStream = new FileInputStream(zipFileSanity);
        InflaterInputStream inflater = new InflaterInputStream(inStream);
        inflater.skip(10L);
    }
	
}
