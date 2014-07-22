package net.sf.zipme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.junit.Test;

public class TestCheckedInputStream extends ZipMeTest {

  @Test
  public void test0() throws Throwable {
    Adler32 adler32_0 = new Adler32();
    assertNotNull(adler32_0);
    byte[] byteArray0 = new byte[1];
    ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(
        byteArray0);
    CheckedInputStream checkedInputStream0 = new CheckedInputStream(
        (InputStream) byteArrayInputStream0, (Checksum) adler32_0);
    assertNotNull(checkedInputStream0);
    assertEquals(1, adler32_0.getValue());
  }

  @Test
  public void test1() throws Throwable {
    byte[] byteArray0 = new byte[8];
    ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(
        byteArray0);
    Adler32 adler32_0 = new Adler32();
    assertNotNull(adler32_0);

    CheckedInputStream checkedInputStream0 = new CheckedInputStream(
        byteArrayInputStream0, adler32_0);
    assertEquals(1L, adler32_0.getValue());
    checkedInputStream0.read(byteArray0, (byte) 0, (byte) 0);
    
    assertEquals(8, checkedInputStream0.available());
    int int0 = checkedInputStream0.read(byteArray0);
    assertEquals((8), int0);
  }

  @Test
  public void test2() throws Throwable {
    CRC32 cRC32_0 = new CRC32();
    cRC32_0.update(0);
  }

  @Test
  public void test3() throws Throwable {
    BufferedInputStream bufferedInputStream0 = new BufferedInputStream(
        (InputStream) null);
    Adler32 adler32_0 = new Adler32();
    assertNotNull(adler32_0);
    CheckedInputStream checkedInputStream0 = new CheckedInputStream(
        bufferedInputStream0, adler32_0);
    assertNotNull(checkedInputStream0);
    byte[] byteArray0 = new byte[8];
    adler32_0.update(byteArray0);
    assertEquals(524289, adler32_0.getValue());
  }

  @Test
  public void test4() throws Throwable {
    InputStream inputStream0 = null;
    Adler32 adler32_0 = new Adler32();
    assertNotNull(adler32_0);
    CheckedInputStream checkedInputStream0 = new CheckedInputStream(
        (InputStream) null, adler32_0);
    byte[] byteArray0 = new byte[8];
    byteArray0[0] = (byte) (-1);
    adler32_0.update(byteArray0, (byte) (-54), (byte) (-45));
    assertEquals(1, adler32_0.getValue());
  }
  
  @Test
  public void test5()  throws Throwable  {
      PipedOutputStream pipedOutputStream0 = new PipedOutputStream();
      PipedInputStream pipedInputStream0 = new PipedInputStream(pipedOutputStream0, 1);
      CheckedInputStream checkedInputStream0 = new CheckedInputStream((InputStream) pipedInputStream0, (Checksum) null);
      checkedInputStream0.mark(0);
  }
  
  @Test
  public void test6()  throws Throwable  {
      byte[] byteArray0 = new byte[4];
      ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, (-1), (int) (byte)0);
      CheckedInputStream checkedInputStream0 = new CheckedInputStream((InputStream) byteArrayInputStream0, (Checksum) null);
      boolean boolean0 = checkedInputStream0.markSupported();
      assertEquals(true, boolean0);
  }
  
  @Test
  public void test7()  throws Throwable  {
      byte[] byteArray0 = new byte[4];
      ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, (-1), (int) (byte)0);
      CheckedInputStream checkedInputStream0 = new CheckedInputStream((InputStream) byteArrayInputStream0, (Checksum) null);
      checkedInputStream0.reset();
      assertEquals(0, checkedInputStream0.available());
  }
  
  @Test
  public void test8()  throws Throwable  {
    Adler32 adler32_0 = new Adler32();
    assertNotNull(adler32_0);
    byte[] byteArray0 = new byte[8];
    ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(
        byteArray0);
    CheckedInputStream checkedInputStream0 = new CheckedInputStream(
        (InputStream) byteArrayInputStream0, (Checksum) adler32_0);
    assertNotNull(checkedInputStream0);
    assertEquals(1L, adler32_0.getValue());
    checkedInputStream0.read(byteArray0, (byte) 0, (byte) 0);
    
    assertEquals(8, checkedInputStream0.available());
    int int0 = checkedInputStream0.read(byteArray0);
    assertEquals((8), int0);
    
    CRC32 cRC32_0 = new CRC32();
    cRC32_0.update(0);
    
    adler32_0.update(byteArray0, (byte) (-54), (byte) (-45));
    assertEquals(524289, adler32_0.getValue());
    
    checkedInputStream0.mark(0);
    boolean boolean0 = checkedInputStream0.markSupported();
    assertEquals(true, boolean0);
    
    checkedInputStream0.reset();
    assertEquals(0, checkedInputStream0.available());
  }

}
