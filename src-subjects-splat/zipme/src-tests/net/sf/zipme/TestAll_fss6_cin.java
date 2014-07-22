package net.sf.zipme;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Ignore;
import org.junit.Test;

import splat.ZipMeVariables;

public class TestAll_fss6_cin extends ZipMeTest{
  
  @Override
  protected void configure() {
    // set mandatory features
    super.configure();
    if (testName == null) {
      throw new RuntimeException();
    }
    String strTestName = testName.getMethodName();
    if (strTestName.equals("testGetTime")
        || strTestName.equals("testInflate")
        || strTestName.equals("testAvailable")
        || strTestName.equals("testZipArchive")) {
//      ZipMeVariables.getSINGLETON().setCOMPRESS___(true);
//      ZipMeVariables.getSINGLETON().setGZIP___(true);
//      ZipMeVariables.getSINGLETON().setEXTRACT___(true);
//      ZipMeVariables.getSINGLETON().setARCHIVECHECK___(true);
//      ZipMeVariables.getSINGLETON().setCRC___(true);
//      ZipMeVariables.getSINGLETON().setDERIVATIVE_COMPRESS_CRC___(true);
//      ZipMeVariables.getSINGLETON().setDERIVATIVE_COMPRESS_GZIP___(true);
//      ZipMeVariables.getSINGLETON().setDERIVATIVE_COMPRESS_GZIPCRC___(true);
//      ZipMeVariables.getSINGLETON().setDERIVATIVE_EXTRACT_CRC___(true);
//      ZipMeVariables.getSINGLETON().setADLER32CHECKSUM___(true);  ZipMeVariables.getSINGLETON().setDERIVATIVE_EXTRACT_CRC___(true);
    } else {
      System.err.printf("%s did not set default configuration", strTestName);
    }
  }

  @Ignore //REPEATED
	@Test
	public void testGetTime() {
		ZipEntry ze = new ZipEntry("name");
		long time = ze.getTime();
		assertEquals(-1,time);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testInflate() throws DataFormatException {
		
		Inflater inflater = new Inflater();
		byte [] buf = {1,2,3};
		inflater.inflate(buf, 1, buf.length);
	}
	
	@Test
	public void testAvailable() throws IOException {
		
		InputStream in = new InputStream() {	
			@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		
		Inflater inf = new Inflater();
		
		ZipArchive_InflaterInputStream zip_iis= new ZipArchive_InflaterInputStream(in, inf, -1);
		int result = zip_iis.available();
		assertEquals(1, result);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testZipArchive() throws ZipException {
		
		byte [] buf = {1,2,3};
		ZipArchive za = new ZipArchive(buf, 5, buf.length);
	}
	

}
