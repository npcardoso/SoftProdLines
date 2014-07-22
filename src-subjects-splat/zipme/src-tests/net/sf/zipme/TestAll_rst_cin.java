package net.sf.zipme;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Ignore;
import org.junit.Test;

import splat.ZipMeVariables;

public class TestAll_rst_cin extends ZipMeTest {

  @Override
  protected void configure() {
    // set mandatory features
    super.configure();
    if (testName == null) {
      throw new RuntimeException();
    }
    String strTestName = testName.getMethodName();
    if (strTestName.equals("testZipEntry")
        || strTestName.equals("testZipLenght")
        || strTestName.equals("testDosTime")
        || strTestName.equals("testSetSize")
        || strTestName.equals("testSetCompressedSize")
        || strTestName.equals("testSetCrc")
        || strTestName.equals("testSetMethod")
        || strTestName.equals("testSetComment")
        || strTestName.equals("testIsDirectory")
        || strTestName.equals("testNeedsDictionary")
        || strTestName.equals("testInflater")
        || strTestName.equals("testDeflater")
        || strTestName.equals("testFinished")
        || strTestName.equals("testDeflate")
        || strTestName.equals("testDeflateSlow")) {
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
  
  String nome = "../zipme/src-tests/net/sf/zipme/file.txt";

  // T1
  // maybe repeated or nonsense
  @Test
  public void testZipEntry() {
    ZipEntry zip = new ZipEntry(nome);
    assertEquals(nome, zip.getName());

  }

  // T2
  // maybe repeated or nonsense
  @Test
  public void testZipLenght() {
    String nome = "TESTE";
    ZipEntry zip = new ZipEntry(nome);
    assertEquals(nome, zip.getName());
  }

  // T3
  @Test
  public void testDosTime() {
    ZipEntry zip = new ZipEntry(nome);
    zip.setDOSTime(1);
    assertEquals(1, zip.getDOSTime());
  }

  // T4
  // maybe repeated or nonsense
  @Test
  public void testSetSize() {
    ZipEntry zip = new ZipEntry(nome);
    zip.setSize(12345678);
    assertEquals(12345678, zip.getSize());
  }

  // T5
  @Test
  public void testSetCompressedSize() {
    ZipEntry zip = new ZipEntry(nome);
    zip.setCompressedSize(1234567890);
    assertEquals(1234567890, zip.getCompressedSize());
  }

  // T6
  @Test
  public void testSetCrc() {
    ZipEntry zip = new ZipEntry(nome);
    zip.setCrc(12345678);
    assertEquals(12345678, zip.getCrc());
  }

  // T7
  @Test(expected = IllegalArgumentException.class)
  public void testSetMethod() {
    ZipEntry zip = new ZipEntry(nome);
    zip.setMethod(1);
    assertEquals(1, zip.getMethod());
  }

  // T8
  // maybe repeated or nonsense
  @Test
  public void testSetComment() {
    ZipEntry zip = new ZipEntry(nome);
    zip.setComment(nome);
    assertEquals(nome, zip.getComment());
  }

  // T9
  @Test
  public void testIsDirectory() {
    ZipEntry zip = new ZipEntry(nome);
    assertEquals(false, zip.isDirectory());
  }

  // T10
  @Test
  public void testNeedsDictionary() {
    boolean bool = false;
    Inflater inf = new Inflater(bool);
    boolean resp = inf.needsDictionary();
    assertEquals(resp, false);
  }

  // T11
  // maybe repeated or nonsense
  @Test
  public void testInflater() {
    boolean bool = false;
    Inflater inf = new Inflater(bool);
    boolean resp = inf.finished();
    assertEquals(resp, false);
  }

  // T12
  @Test
  public void testDeflater() {
    Deflater defs = new Deflater(2);
    defs.setLevel(5);
    assertEquals(defs.level, 5);
  }

  // T13
  // maybe repeated or nonsense
  @Test
  public void testFinished() {
    Deflater def = new Deflater(2);
    boolean resp = def.finished();
    assertEquals(resp, false);
  }

  // T14
  @Test
  public void testDeflate() {
    DeflaterPending pend = new DeflaterPending();
    DeflaterEngine def = new DeflaterEngine(pend);
    boolean resp = def.deflate(false, false);
    assertEquals(resp, false);
  }

  // T15
  @Test
  public void testDeflateSlow() throws IOException {
    InputStream in = new InputStream() {
      public int read() throws IOException {
        return 0;
      }
    };
    DeflaterPending pend = new DeflaterPending();
    DeflaterEngine def = new DeflaterEngine(pend);
    ZipArchive_InflaterInputStream zip = new ZipArchive_InflaterInputStream(in,
        new Inflater(), 0);
    assertEquals(0, zip.available());
  }
}
