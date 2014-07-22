package net.sf.zipme;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.Test;

import splat.ZipMeVariables;

public class TestAll_rbm3_cin extends ZipMeTest{
  
  @Override
  protected void configure() {
    // set mandatory features
    super.configure();
    if (testName == null) {
      throw new RuntimeException();
    }
    String strTestName = testName.getMethodName();
    if (strTestName.equals("test01")
        || strTestName.equals("test02")
        || strTestName.equals("test03") 
        || strTestName.equals("test04")
        || strTestName.equals("test05") 
        || strTestName.equals("test06")
        || strTestName.equals("test07") 
        || strTestName.equals("test08")
        || strTestName.equals("test09") 
        || strTestName.equals("test10")
        || strTestName.equals("test11") 
        || strTestName.equals("test12")
        || strTestName.equals("test13") 
        || strTestName.equals("test14")
        || strTestName.equals("test15")
        || strTestName.equals("test16")
        || strTestName.equals("test17")) {
//      ZipMeVariables.getSINGLETON().setADLER32CHECKSUM___(true);
//      ZipMeVariables.getSINGLETON().setCOMPRESS___(true);
//      ZipMeVariables.getSINGLETON().setDERIVATIVE_COMPRESS_ADLER32CHECKSUM___(true);
    } else {
      System.err.printf("%s did not set default configuration", strTestName);
    }
  }

	public static File fileToZip = new File("../zipme/src-tests/net/sf/zipme/ZipMeTest.java");
	public static File zipFile = new File(
			"../zipme/src-tests/net/sf/zipme/ZipMETest.java.zip");
	public static File unzipFile = new File(
			"../zipme/src-tests/net/sf/zipme/ZipMETest.java-Copy.java");

	/**
	 * Run each test according to the number.
	 */
	/*
	 * @Test public void runTest() { int test = 1; switch(test){ case 1:
	 * test1(); break; case 2: test2(); break;
	 * 
	 * default: throw new RuntimeException("This test does not exist"); } }
	 */

	// maybe repeated or nonsense
	@Test
	public void test01 () {
		ZipEntry zip = new ZipEntry("teste");
		Assert.assertEquals(-1, zip.getTime());
	}

	// maybe repeated or nonsense
	@Test
	public void test02 () throws ZipException {
		ZipOutputStream zipO = new ZipOutputStream(new OutputStream() {
			
			@Override
			public void write(int b) throws IOException {
				// TODO Auto-generated method stub					
			}
		});
		zipO.setMethod(0);
	}
	
	// maybe repeated or nonsense
	@Test
	public void test03 () throws IOException, DataFormatException {
		byte[] buffer = new byte[0xffff];
		Inflater in = new Inflater();
		in.inflate(buffer, 1, 1);

	}
	
	@Test
	public void test04 () throws IOException, DataFormatException {
		ZipOutputStream zipO = new ZipOutputStream(new OutputStream() {
			
			public void write(int arg0) throws IOException {				
			}			
		});
		
		zipO.setComment("");

	}
	
	// maybe repeated or nonsense
	@Test
	public void test05 () {	
		Inflater in = new Inflater();
		Assert.assertEquals(false, in.finished());
	}	
	
	// maybe repeated or nonsense
	@Test
	public void test06 (){
		Deflater deflater = new Deflater();		
		Assert.assertEquals(false, deflater.finished());	
	}
	
	// maybe repeated or nonsense
	@Test
	public void test07 () {
		
		InputStream in = new InputStream() {
			
			@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		
		Inflater inf = new Inflater();
		
		InflaterInputStream infStream = new InflaterInputStream(in, inf, 2);
		
	}
	
	@Test
	public void test08 () throws IOException {
		
		Deflater d = new Deflater();
		
		byte[] b = new byte[] { (byte) 0x01, (byte) 0x04, (byte)0x00, (byte)0x01,(byte)0x00, (byte) 0x01};
		
		d.setInput(b, 0, b.length);		
	}
	
	// maybe repeated or nonsense
	@Test
	public void test09 () throws IOException {
		
		byte[] buffer = new byte [0xffff];	
		
		Deflater d = new Deflater();
		
		d.setDictionary(buffer);
	
	}
	
	@Test
	public void test10 () throws IOException {
		
		byte[] buffer = new byte [0xffff];	
		
		Deflater d = new Deflater();
		
		d.deflate(buffer);
	
	}
	
	// maybe repeated or nonsense
	@Test
	public void test11() throws IOException {
		byte[] buffer = new byte [0xffff];	
		
		Deflater d = new Deflater();		
		Assert.assertTrue(d.needsInput());
	}
	
	// maybe repeated or nonsense
//	@Test
//	public void test12 () {
//		byte[] buffer = new byte [0xffff];	
//		
//		Deflater d = new Deflater();		
//		Assert.assertEquals(1, d.getAdler());
//	}
	
	@Test
	public void test13 () throws IOException {
		byte[] buffer = new byte [0xffff];	
		
		Deflater d = new Deflater();		
		Assert.assertEquals(0, d.getTotalIn());
	}
	
//	// maybe repeated or nonsense
//	@Test
//	public void test14() {
//		Adler32 adl = new Adler32();
//		byte[] buffer = new byte[0xffff];
//		adl.update(buffer, 1, 0);
//		Assert.assertEquals(1, adl.getValue());		
//	}
	
//	// maybe repeated or nonsense
//	@Test
//	public void test15() throws ZipException {
//		byte[] buffer = new byte [0xffff];
//		ZipArchive zipA = new ZipArchive(buffer, 1, 1);		
//	}
	
	// maybe repeated or nonsense
	@Test
	public void test16 (){
		ZipEntry zip = new ZipEntry("testando");
		zip.hashCode();		
	}
	@Test
	public void test17 (){
		ZipEntry zip = new ZipEntry("testando");
		zip.setComment("testando");
	}
	

	


	
	


}
