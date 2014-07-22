package net.sf.zipme;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import splat.ZipMeVariables;

public class TestAll_chas_cin extends ZipMeTest {

  @Override
  protected void configure() {
    // set mandatory features
    super.configure();
    if (testName == null) {
      throw new RuntimeException();
    }
    String strTestName = testName.getMethodName();
    if (strTestName.equals("test06") 
        || strTestName.equals("test07")
        || strTestName.equals("test08") 
        || strTestName.equals("test09")
        || strTestName.equals("test10") 
        || strTestName.equals("test11")
        || strTestName.equals("test12") 
        || strTestName.equals("test13")
        || strTestName.equals("test14") 
        || strTestName.equals("test15")) {
    } else if (strTestName.equals("test01") 
        || strTestName.equals("test02")) {
//      ZipMeVariables.getSINGLETON().setADLER32CHECKSUM___(true);
    } else if (strTestName.equals("test03")
        || strTestName.equals("test04")) {
//      ZipMeVariables.getSINGLETON().setCOMPRESS___(true);
 } else if (strTestName.equals("test05")) {
//      ZipMeVariables.getSINGLETON().setCOMPRESS___(true);
//      ZipMeVariables.getSINGLETON().setDERIVATIVE_COMPRESS_ADLER32CHECKSUM___(true);
//      ZipMeVariables.getSINGLETON().setADLER32CHECKSUM___(true);
    } else {
      System.err.printf("%s did not set default configuration", strTestName);
    }
  }

  
  @Test
  public void test01() {
    net.sf.zipme.Adler32 a32 = new net.sf.zipme.Adler32();
    byte[] buffer = new byte[0xFFFF];
    a32.update(buffer, 7, 8);
    Assert.assertEquals(524289, a32.getValue());
  }

  @Test
  public void test02() {
    net.sf.zipme.Adler32 a32 = new net.sf.zipme.Adler32();
    a32.update(8);
    Assert.assertEquals(589833, a32.getValue());
  }

  @Ignore
  @Test
  public void test03() {
    Deflater df = new Deflater(5);
    boolean a = df.finished();
    Assert.assertEquals(true, a);
  }

  @Test
  public void test04() {
    Deflater df = new Deflater(5);
    boolean a = df.needsInput();

    Assert.assertEquals(true, a);
  }

  @Test
  public void test05() {
    // Output ow = new OutputWindow();
    Deflater df = new Deflater(5);
    // int a = 2;
    // byte[] buffer = new byte[0xFFFF];
    int a = df.getAdler();

    Assert.assertEquals(1, a);
  }

  @Test
  public void test06() {
    // Output ow = new OutputWindow();
    ZipEntry ze = new ZipEntry("null");
    // int a = 2;
    // byte[] buffer = new byte[0xFFFF];
    boolean a = ze.isDirectory();

    Assert.assertEquals(false, a);
  }

  @Test
  public void test07() {
    // Output ow = new OutputWindow();
    ZipEntry ze = new ZipEntry("null");
    // int a = 2;
    // byte[] buffer = new byte[0xFFFF];
    String a = ze.toString();

    Assert.assertEquals("null", a);
  }

  // maybe repeated or nonsense
  @Test
  public void test08() {
    // Output ow = new OutputWindow();
    ZipEntry ze = new ZipEntry("null");
    // int a = 2;
    // byte[] buffer = new byte[0xFFFF];
    int a = ze.getMethod();

    Assert.assertEquals(-1, a);
  }

  // maybe repeated or nonsense
  @Test
  public void test09() {
    // Output ow = new OutputWindow();
    Inflater in = new Inflater();
    // int a = 2;
    // byte[] buffer = new byte[0xFFFF];
    boolean a = in.needsDictionary();

    Assert.assertEquals(false, a);
  }

  @Test
  public void test10() {
    // Output ow = new OutputWindow();
    Inflater in = new Inflater();
    // int a = 2;
    // byte[] buffer = new byte[0xFFFFF];
    long a = in.getBytesRead();

    Assert.assertEquals(0, a);
  }

  @Test
  public void test11() {
    // Output ow = new OutputWindow();
    Deflater df = new Deflater(5);
    // int a = 2;
    // byte[] buffer = new byte[0xFFFF];
    int a = df.getTotalOut();

    Assert.assertEquals(0, a);
  }

  @Test
  public void test12() {
    // Output ow = new OutputWindow();
    ZipEntry ze = new ZipEntry("null");
    // int a = 2;
    // byte[] buffer = new byte[0xFFFF];
    int a = ze.hashCode();

    Assert.assertEquals(3392903, a);
  }

  // maybe repeated or nonsense
  @Test
  public void test13() {
    // Output ow = new OutputWindow();
    ZipEntry ze = new ZipEntry("null");
    // int a = 2;
    // byte[] buffer = new byte[0xFFFF];
    long a = ze.getTime();

    Assert.assertEquals(-1, a);
  }

  @Test
  public void test14() {
    // Output ow = new OutputWindow();
    ZipEntry ze = new ZipEntry("null");
    // int a = 2;
    // byte[] buffer = new byte[0xFFFF];
    String a = ze.getComment();

    Assert.assertEquals(null, a);
  }

//  @Test
//  public void test15() throws ZipException {
//    // Output ow = new OutputWindow();
//    byte[] buffer = new byte[1024];
//    ZipArchive ze = new ZipArchive(buffer, 0, 1);
//    // int a = 2;
//    int a = ze.size();
//
//    Assert.assertEquals(0, a);
//  }
}