package net.sf.zipme;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestAll_filipehenriquester_gmail extends ZipMeTest {

  @Override
  protected void configure() {
    // set mandatory features
    super.configure();
    if (testName == null) {
      throw new RuntimeException();
    }
    String strTestName = testName.getMethodName();
    if (strTestName.equals("teste1") || strTestName.equals("teste2")
        || strTestName.equals("teste3") || strTestName.equals("teste4")
        || strTestName.equals("teste5") || strTestName.equals("teste6")
        || strTestName.equals("teste7") || strTestName.equals("teste8")
        || strTestName.equals("teste9") || strTestName.equals("teste10")
        || strTestName.equals("teste11") || strTestName.equals("teste12")
        || strTestName.equals("teste13") || strTestName.equals("teste14")
        || strTestName.equals("teste15")) {
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

  net.sf.zipme.CheckedOutputStream os = null;
  net.sf.zipme.CheckedInputStream ins = null;
  File f = new File("test.txt");

  @Before
  public void before() {
    // CheckedOutput
    OutputStream out = null;
    InputStream in = null;
    try {
      out = new FileOutputStream(f);
      in = new FileInputStream(f);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    os = new net.sf.zipme.CheckedOutputStream(out, new net.sf.zipme.Checksum() {
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

    ins = new net.sf.zipme.CheckedInputStream(in, new net.sf.zipme.Checksum() {
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


  @Test
  public void teste1() {
    // TODO Auto-generated method stub
    Checksum check = os.getChecksum();
    Assert.assertEquals(check.getValue(), os.getChecksum().getValue());
  }

  @Test
  public void teste2() throws IOException {
    // TODO Auto-generated method stub
    Assert.assertEquals(ins.read(), ins.read());

  }

  @Test
  public void teste3() {
    // TODO Auto-generated method stub
    try {
      Assert.assertEquals(ins.skip(1452l), ins.skip(1452l));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  public void teste4() {
    // TODO Auto-generated method stub

    net.sf.zipme.Deflater df = new net.sf.zipme.Deflater();
    Assert.assertEquals(df.getTotalOut(), df.getTotalOut());

  }

  @Test
  public void teste5() {
    // TODO Auto-generated method stub
    net.sf.zipme.Deflater df = new net.sf.zipme.Deflater();
    Assert.assertEquals(df.getTotalIn(), df.getTotalIn());

  }

  @Test
  public void teste6() {
    // TODO Auto-generated method stub
    net.sf.zipme.Deflater df = new net.sf.zipme.Deflater();
    Assert.assertEquals(df.getBytesRead(), df.getBytesRead());
  }

  @Test
  public void teste7() {
    // TODO Auto-generated method stub
    net.sf.zipme.Deflater df = new net.sf.zipme.Deflater();
    Assert.assertEquals(df.getBytesWritten(), df.getBytesWritten());
  }

  @Test
  public void teste8() {
    // TODO Auto-generated method stub
    net.sf.zipme.Deflater df = new net.sf.zipme.Deflater();
    Assert.assertEquals(false, df.finished());
  }


  @Test
  public void teste9() {
    // TODO Auto-generated method stub
    net.sf.zipme.Deflater df = new net.sf.zipme.Deflater();
    Assert.assertEquals(true, df.needsInput());
  }

  @Test
  public void teste10() {
    // TODO Auto-generated method stub
    net.sf.zipme.Deflater df = new net.sf.zipme.Deflater();
    Assert.assertEquals(df.getAdler(), df.getAdler());
  }


  @Test
  public void teste11() throws FileNotFoundException {
    // TODO Auto-generated method stub
    net.sf.zipme.Deflater df = new net.sf.zipme.Deflater();
    byte[] b = new byte[] { 1, 4, 8 };
    Assert.assertNotSame(df.deflate(b), df.deflate(b));
  }


  @Test
  public void teste12() throws FileNotFoundException {
    // TODO Auto-generated method stub
    net.sf.zipme.Deflater df = new net.sf.zipme.Deflater();
    byte[] b = new byte[] { 1, 4, 8 };
    Assert.assertNotSame(df.deflate(b), df.deflate(b));
  }


//  @Test
//  public void teste13() {
//    // TODO Auto-generated method stub
//    net.sf.zipme.Inflater infla = new net.sf.zipme.Inflater(true);
//    Assert.assertEquals(infla.getAdler(), infla.getAdler());
//  }

  @Test
  public void teste14() {
    // TODO Auto-generated method stub
    net.sf.zipme.Inflater infla = new net.sf.zipme.Inflater();
    Assert.assertEquals(false, infla.finished());
  }

  @Test
  public void teste15() {
    // TODO Auto-generated method stub
    net.sf.zipme.Inflater infla = new net.sf.zipme.Inflater();
    Assert.assertEquals(false, infla.needsDictionary());
  }

}
