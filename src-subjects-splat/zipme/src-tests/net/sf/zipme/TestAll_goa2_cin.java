package net.sf.zipme;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import splat.ZipMeVariables;

public class TestAll_goa2_cin extends ZipMeTest {

//  @Override
//  protected void configure() {
//    // set mandatory features
//    super.configure();
//    if (testName == null) {
//      throw new RuntimeException();
//    }
//    String strTestName = testName.getMethodName();
//    if (strTestName.equals("mutant_01") 
//        || strTestName.equals("mutant_02")
//        || strTestName.equals("mutant_03") 
//        || strTestName.equals("mutant_04")
//        || strTestName.equals("mutant_05") 
//        || strTestName.equals("mutant_06")
//        || strTestName.equals("mutant_07") 
//        || strTestName.equals("mutant_08")
//        || strTestName.equals("mutant_09") 
//        || strTestName.equals("mutant_10")
//        || strTestName.equals("mutant_11") 
//        || strTestName.equals("mutant_12")
//        || strTestName.equals("mutant_13") 
//        || strTestName.equals("mutant_14")
//        || strTestName.equals("mutant_15")) {      
////      ZipMeVariables.getSINGLETON().setCOMPRESS___(true);
////        ZipMeVariables.getSINGLETON().setGZIP___(true);
////        ZipMeVariables.getSINGLETON().setEXTRACT___(true);
////        ZipMeVariables.getSINGLETON().setARCHIVECHECK___(true);
////        ZipMeVariables.getSINGLETON().setCRC___(true);
////        ZipMeVariables.getSINGLETON().setDERIVATIVE_COMPRESS_CRC___(true);
////        ZipMeVariables.getSINGLETON().setDERIVATIVE_COMPRESS_GZIP___(true);
////        ZipMeVariables.getSINGLETON().setDERIVATIVE_COMPRESS_GZIPCRC___(true);
////        ZipMeVariables.getSINGLETON().setDERIVATIVE_EXTRACT_CRC___(true);
////        ZipMeVariables.getSINGLETON().setADLER32CHECKSUM___(true);
//    } else {
//      System.err.printf("%s did not set default configuration", strTestName);
//    }
//  }

  /* Mutation Testing */

  public static File fileToZip = new File("src-tests/net/test/ZipMETest.java");

  net.sf.zipme.ZipEntry ze = new net.sf.zipme.ZipEntry(fileToZip.getPath());
  Adler32 adl;
  Inflater in;
  net.sf.zipme.Deflater def;
  ZipOutputStream zip;
  ZipArchive za;
  net.sf.zipme.Deflater d;

//  // maybe repeated or nonsense
//  @Test
//  public void mutant_01() {
//
//    adl = new Adler32();
//    byte[] buffer = new byte[0xffff];
//
//    adl.update(buffer, 1, 0);
//
//    Assert.assertEquals(1, adl.getValue());
//
//  }

  // maybe repeated or nonsense
  @Test
  public void mutant_02() {

    def = new net.sf.zipme.Deflater();

    Assert.assertEquals(false, def.finished());

  }

  @Test
  public void mutant_03() throws IOException {

    byte[] buffer = new byte[0xffff];

    def = new net.sf.zipme.Deflater();

    def.setDictionary(buffer);

  }

  // maybe repeated or nonsense
  @Test
  public void mutant_04() {

    in = new Inflater();

    Assert.assertEquals(false, in.finished());

  }

  // maybe repeated or nonsense
  @Test
  public void mutant_05() throws IOException, DataFormatException {

    byte[] buffer = new byte[0xffff];

    in = new Inflater();

    in.inflate(buffer, 1, 1);

  }

  @Test
  public void mutant_06() {

    InputStream in = new InputStream() {

      @Override
      public int read() throws IOException {

        return 0;

      }

    };

    Inflater inf = new Inflater();

    InflaterInputStream infStream = new InflaterInputStream(in, inf, 2);

  }

  @Ignore
  @Test
  public void mutant_07() throws ZipException {

    byte[] b = new byte[] { (byte) 0x01, (byte) 0x04, (byte) 0x00, (byte) 0x01,
        (byte) 0x00, (byte) 0x01 };

    za = new ZipArchive(b, 2, 2);

    za.getEntry("");

  }

  @Test
  public void mutant_08() throws IOException {

    InputStream in = new InputStream() {

      @Override
      public int read() throws IOException {

        return 0;

      }

    };

    Inflater inf = new Inflater();

    ZipArchive_InflaterInputStream zipInflater = new ZipArchive_InflaterInputStream(
        in, inf, 1);
    Assert.assertEquals(1, zipInflater.available());

  }

  @Test
  public void mutant_09() throws IOException {

    d = new net.sf.zipme.Deflater();

    byte[] b = new byte[] { (byte) 0x01, (byte) 0x04, (byte) 0x00, (byte) 0x01,
        (byte) 0x00, (byte) 0x01 };

    d.setInput(b, 0, b.length);

  }

  @Test
  public void mutant_10() {

    ze = new net.sf.zipme.ZipEntry("Zip");

  }

  @Test
  public void mutant_11() {

    ze = new net.sf.zipme.ZipEntry("Zip");

    ze.setSize(0);

  }

  @Test
  public void mutant_12() {

    ze = new net.sf.zipme.ZipEntry(fileToZip.getPath());
    String comment = null;

    ze.setComment(comment);

    Assert.assertEquals(comment, ze.getComment());

  }

  @Test
  public void mutant_13() throws IOException, DataFormatException {

    zip = new ZipOutputStream(new OutputStream() {

      public void write(int arg0) throws IOException {

      }

    });

    zip.setComment("");

  }

  @Test
  public void mutant_14() throws ZipException {

    zip = new ZipOutputStream(new OutputStream() {

      @Override
      public void write(int b) throws IOException {

      }

    });

    zip.setMethod(0);

  }

  @Test
  public void mutant_15() {

    Assert.assertEquals(-1, ze.getCrc());

  }
  
  @Test
  public void testTotal() throws DataFormatException, IOException {
    adl = new Adler32();
    byte[] buffer = new byte[0xffff];
    adl.update(buffer, 1, 0);
    Assert.assertEquals(1, adl.getValue());
    
    def = new net.sf.zipme.Deflater();
    Assert.assertEquals(false, def.finished());
    
    def = new net.sf.zipme.Deflater();
    def.setDictionary(new byte[0xffff]);
    
    in = new Inflater();
    Assert.assertEquals(false, in.finished());

    in = new Inflater();
    in.inflate(new byte[0xffff], 1, 1);
    
    InputStream in = new InputStream() {
      @Override
      public int read() throws IOException {
        return 0;
      }
    };
    Inflater inf = new Inflater();
    InflaterInputStream infStream = new InflaterInputStream(in, inf, 2);
    
    byte[] b = new byte[] { (byte) 0x01, (byte) 0x04, (byte) 0x00, (byte) 0x01,
        (byte) 0x00, (byte) 0x01 };
    
    ZipArchive_InflaterInputStream zipInflater = new ZipArchive_InflaterInputStream(
        in, inf, 1);
    Assert.assertEquals(1, zipInflater.available());
    
    d = new net.sf.zipme.Deflater();
    d.setInput(b, 0, b.length);
    
    ze = new net.sf.zipme.ZipEntry("Zip");
    
    ze.setSize(0);
    
    ze = new net.sf.zipme.ZipEntry(fileToZip.getPath());
    String comment = null;
    ze.setComment(comment);
    Assert.assertEquals(comment, ze.getComment());
    
    zip = new ZipOutputStream(new OutputStream() {
      public void write(int arg0) throws IOException {
      }
    });
    zip.setComment("");

    zip = new ZipOutputStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
      }
    });
    zip.setMethod(0);
    
    Assert.assertEquals(-1, ze.getCrc());
  }

}
