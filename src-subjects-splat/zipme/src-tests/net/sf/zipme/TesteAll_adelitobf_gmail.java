package net.sf.zipme;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import splat.ZipMeVariables;

public class TesteAll_adelitobf_gmail extends ZipMeTest {

  @Override
  protected void configure() {
    // set mandatory features
    super.configure();
    if (testName == null) {
      throw new RuntimeException();
    }
    String strTestName = testName.getMethodName();
    if (strTestName.equals("mutacaoTest01")
        || strTestName.equals("mutacaoTest02")
        || strTestName.equals("mutacaoTest03")
        || strTestName.equals("mutacaoTest04")
        || strTestName.equals("mutacaoTest05")
        || strTestName.equals("mutacaoTest06")
        || strTestName.equals("mutacaoTest07")
        || strTestName.equals("mutacaoTest11")
        || strTestName.equals("mutacaoTest12")
        || strTestName.equals("mutacaoTest13")
        || strTestName.equals("mutacaoTest14")
        || strTestName.equals("mutacaoTest15")
        || strTestName.equals("mutacaoTest16")) {
    } else if (strTestName.equals("mutacaoTest08")) {
//      ZipMeVariables.getSINGLETON().setCOMPRESS___(true);
    } else if (strTestName.equals("mutacaoTest09")) {
//      ZipMeVariables.getSINGLETON().setCOMPRESS___(true);
//      ZipMeVariables.getSINGLETON().setADLER32CHECKSUM___(true);
//      ZipMeVariables.getSINGLETON().setDERIVATIVE_COMPRESS_ADLER32CHECKSUM___(true);
    } else if (strTestName.equals("mutacaoTest10")) {
//      ZipMeVariables.getSINGLETON().setADLER32CHECKSUM___(true);
    } else {
      System.err.printf("%s did not set default configuration", strTestName);
    }
  }

  private Inflater INFLANTE;
  private InflaterInputStream INFLANTER_InputStream;
  private Deflater DEFLATER;
  private PendingBuffer PendingBuffer;
  private Adler32 Adler32;
  private ZipArchive_PartialInputStream ZipArchive_PartialInputStream;

  @Before
  public void before() {
    INFLANTE = new Inflater(true);
    INFLANTER_InputStream = new InflaterInputStream(new InputStream() {
      @Override
      public int read() throws IOException {
        return 0;
      }

    });

    DEFLATER = new Deflater();
    PendingBuffer = new PendingBuffer();

    Adler32 = new Adler32();
    ZipArchive_PartialInputStream = new ZipArchive_PartialInputStream(
        new byte[3], 0, 3);

  }

  // Classe Inflater

  @Test
  // [01]
  public void mutacaoTest01() {
    // verificar se o valor do contador esta correto
    assertEquals(0, INFLANTE.getTotalIn());
  }

  @Test
  // [02]
  public void mutacaoTest02() throws DataFormatException {

    assertEquals(0, INFLANTE.inflate(new byte[3], 0, 3));
  }

  // Classe InflaterInputStream
  @Test
  // [03]
  public void mutacaoTest03() throws IOException {
    assertEquals(0, INFLANTER_InputStream.read(new byte[3], 0, 0));
  }

  @Test
  // [04] ao pode ter duas linhas mudadas
  public void mutacaoTest04() throws IOException {
    assertEquals(0, INFLANTER_InputStream.skip(0));
  }

  @Test
  // [05]
  public void mutacaoTest05() throws IOException {
    assertEquals(1, INFLANTER_InputStream.available());
  }

  @Test
  // [06]
  public void mutacaoTest06() {
    assertEquals(false, INFLANTER_InputStream.markSupported());
  }

  // Classe Deflater
  @Test
  // [07]
  public void mutacaoTest07() {
    assertEquals(0, DEFLATER.getTotalOut());
  }

  @Test
  // [08]
  public void mutacaoTest08() {
    assertEquals(0, DEFLATER.getTotalIn());
  }

  @Test
  // [09]
  public void mutacaoTest09() {
    assertEquals(1, DEFLATER.getAdler());
  }

//  // Classe Adler32
//  @Test
//  // [10]
//  public void mutacaoTest10() {
//    assertEquals(1, Adler32.getValue());
//  }

  // Classe ZipArchive_PartialInputStream
  @Test
  // [11]
  public void mutacaoTest11() {
    assertEquals(0, ZipArchive_PartialInputStream.read());
  }

  @Test
  // [12]
  public void mutacaoTest12() {
    assertEquals(3, ZipArchive_PartialInputStream.read(new byte[4], 0, 4));
  }

  // Classe PendingBuffer
  @Test
  // [13]
  public void mutacaoTest13() {
    assertEquals(0, PendingBuffer.flush(new byte[4], 0, 4));
  }

  @Test
  // [14]
  public void mutacaoTest14() {
    assertEquals(0, PendingBuffer.getBitCount());
  }

  @Test
  // [15]
  public void mutacaoTest15() {
    assertEquals(true, PendingBuffer.isFlushed());
  }

  @Test
  // [16]
  public void mutacaoTest16() {
    assertEquals(new byte[] {}.length, PendingBuffer.toByteArray().length);
  }

}