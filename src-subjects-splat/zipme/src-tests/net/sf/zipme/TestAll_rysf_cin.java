package net.sf.zipme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import splat.ZipMeVariables;

public class TestAll_rysf_cin extends ZipMeTest{
  
  @Override
  protected void configure() {
    // set mandatory features
    super.configure();
    if (testName == null) {
      throw new RuntimeException();
    }
    String strTestName = testName.getMethodName();
    if (strTestName.equals("testM01")
        || strTestName.equals("testM02")
        || strTestName.equals("testM03")
        || strTestName.equals("testM04")
        || strTestName.equals("testM05")
        || strTestName.equals("testM06")
        || strTestName.equals("testM07")
        || strTestName.equals("testM08")
        || strTestName.equals("testM09")
        || strTestName.equals("testM10")
        || strTestName.equals("testM11")
        || strTestName.equals("testM12")
        || strTestName.equals("testM13")
        || strTestName.equals("testM14")
        || strTestName.equals("testM15")) {
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

	ZipEntry ze;
	ZipOutputStream zos;

	@Before
	public void setUp() throws Exception {
		ze = new ZipEntry("C:/Users/");
		zos = new ZipOutputStream(new OutputStream() {

			@Override
			public void write(int b) throws IOException {
				// nothing
			}
		});
	}

	// maybe repeated or nonsense
	@Test
	public void testM01() {
		assertTrue(ze.isDirectory());
	}

	@Ignore
	@Test
	public void testM02() {
		Long time = System.currentTimeMillis();
		ze.setTime(time);
		assertTrue(time.equals(ze.getTime()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testM03() {
		Long size = 108327489321L;
		ze.setSize(size);
		assertTrue(size.equals(ze.getSize()));
	}

	// maybe repeated or nonsense
	@Test
	public void testM04() {
		ze.setMethod(ZipOutputStream.STORED);
		assertEquals(ZipOutputStream.STORED, ze.getMethod());
	}

	@Test
	public void testM05() {
		byte[] a = { (byte) 0xffff, (byte) 0xffff, (byte) 0xffff };
		ze.setExtra(a);
		assertEquals(a, ze.getExtra());
	}

	// maybe repeated or nonsense
	@Test
	public void testM06() throws IOException {
		Inflater in = new Inflater();
		assertEquals(false, in.finished());
	}

	// maybe repeated or nonsense
	@Test
	public void testM07() {
		Inflater in = new Inflater();
		assertTrue(in.getTotalIn() <= 0);
	}

	// maybe repeated or nonsense
	@Test
	public void testM08() {
		Inflater in = new Inflater();
		assertEquals(false, in.needsDictionary());
	}

	// maybe repeated or nonsense
	@Test
	public void testM09() {
		assertTrue(ze.getSize() == -1);
	}

	// maybe repeated or nonsense
	@Test
	public void testM10() {
		ze = new ZipEntry("C:/Users.txt");
		assertFalse(ze.isDirectory());
	}

	@Test
	public void testM11() {
		Deflater d = new Deflater(Deflater.DEFAULT_COMPRESSION, true);
		assertFalse(d.finished());
	}

	@Test
	public void testM12() throws IOException {
		InputStream in = new InputStream() {

			@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		Inflater i = new Inflater();
		InflaterInputStream iis = new InflaterInputStream(in, i, 100);
		assertEquals(1, iis.available());
	}

	@Test
	public void testM13() throws IOException {
		InputStream in = new InputStream() {

			@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
		};

		ZipArchive_InflaterInputStream zaiis = new ZipArchive_InflaterInputStream(
				in, new Inflater(), 100);
		assertEquals(100, zaiis.available());
	}

	// maybe repeated or nonsense
	@Test
	public void testM14() {
		Deflater df = new Deflater();
		assertTrue(df.needsInput());
	}

	// maybe repeated or nonsense
	@Test
	public void testM15() {
		Deflater df = new Deflater();
		assertEquals(0, df.getTotalIn());
	}
}