package net.sf.zipme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;

import splat.ZipMeVariables;

public class TestAll_jpmirandas_gmail extends ZipMeTest {

  @Override
  protected void configure() {
    // set mandatory features
    super.configure();
    if (testName == null) {
      throw new RuntimeException();
    }
    String strTestName = testName.getMethodName();
    if (strTestName.equals("id01") || strTestName.equals("id02")
        || strTestName.equals("id03") || strTestName.equals("id04")
        || strTestName.equals("id05") || strTestName.equals("id06")
        || strTestName.equals("id07") || strTestName.equals("id08")
        || strTestName.equals("id09") || strTestName.equals("id10")
        || strTestName.equals("id11") || strTestName.equals("id12")
        || strTestName.equals("id13") || strTestName.equals("id14")
        || strTestName.equals("id15")) {
      // ZipMeVariables.getSINGLETON().setADLER32CHECKSUM___(true);
      // ZipMeVariables.getSINGLETON().setCOMPRESS___(true);
    } else {
      System.err.printf("%s did not set default configuration", strTestName);
    }
  }

  ZipEntry ze;
  ZipArchive za;
  Inflater inf;
  Adler32 ad;
  ZipOutputStream zo;
  CheckedInputStream ci;
  Deflater df;

  @Before
  public void setUp() throws Exception {
    ze = new ZipEntry("../zipme/src-tests/net/sf/zipme/Teste.txt.zip");
    inf = new Inflater();
    ad = new Adler32();
    df = new Deflater();
//    za = new ZipArchive(new byte[100]);
    FileInputStream inStream = new FileInputStream(new File(
        "../zipme/src-tests/net/sf/zipme/banco.gz"));
    za = new ZipArchive(inStream);
    zo = new ZipOutputStream(new FileOutputStream(new File(
        "../zipme/src-tests/net/sf/zipme/Teste.txt.zip")));
    ci = new CheckedInputStream(new FileInputStream(new File(
        "../zipme/src-tests/net/sf/zipme/Teste.txt.zip")),
        new net.sf.zipme.Checksum() {
          private long checked;

          @Override
          public void update(byte[] buf, int off, int len) {
            // TODO Auto-generated method stub
            checked = buf.hashCode() * off + len;
          }

          @Override
          public void update(int bval) {
            // TODO Auto-generated method stub
            checked += bval;
          }

          @Override
          public void reset() {
            // TODO Auto-generated method stub
            checked = 0;
          }

          @Override
          public long getValue() {
            // TODO Auto-generated method stub
            return checked;
          }
        });
  }

  // maybe repeated or nonsense
  @Test
  public void id01() {
    ze.setTime(1293242400000L);
    assertEquals("Teste02", 1293242400000L, ze.getTime());
  }

  @Test(expected = IllegalArgumentException.class)
  public void id02() {
    ze.setMethod(1);
  }

  // maybe repeated or nonsense
  @Test
  public void id03() {
    ze.setComment("comentario_legal");
    assertEquals("Teste04", "comentario_legal", ze.getComment());

  }

  @Test(expected = IllegalArgumentException.class)
  public void id04() throws ZipException {
    byte[] b = new byte[100];

    za = new ZipArchive(b, -1, -1);

  }

  // maybe repeated or nonsense
  @Test
  public void id05() {
    assertFalse(inf.finished());
  }

  // maybe repeated or nonsense
  @Test
  public void id06() {
    assertFalse(ze.isDirectory());
  }

  @Test
  public void id07() {
    assertNull(ze.getComment());
  }

  // maybe repeated or nonsense
  @Test
  public void id08() {
    byte[] buf = new byte[110];
    ad.update(buf, 10, 99);

    assertEquals("Teste01", 6488065, ad.getValue());
  }

  @Test(expected = ZipException.class)
  public void id09() throws IOException {

    zo.setMethod(zo.STORED);
    ZipEntry ze = new ZipEntry("C://");
    ze.setSize(200);
    zo.putNextEntry(ze);

  }

  @Test(expected = NullPointerException.class)
  public void id10() throws DataFormatException {
    inf.end();
    inf.getRemaining();
  }

  @Test
  public void id11() throws IOException {
    long valor = ci.skip(2);
    assertEquals(0, valor);
  }

//  // maybe repeated or nonsense
//  @Test
//  public void id12() {
//    ad.update(200);
//    assertEquals("id12", 13172937, ad.getValue());
//  }

  @Test(expected = IllegalArgumentException.class)
  public void id13() {
    df.setStrategy(20);

  }

  @Test(expected = ZipException.class)
  public void id14() throws IOException {
    ZipEntry zp = new ZipEntry("C:");
    zp.setMethod(zp.STORED);
    zo.putNextEntry(zp);

  }

  @Test
  public void id15() {
    assertEquals("id15", 2, df.deflate(new byte[100]));
  }

}
