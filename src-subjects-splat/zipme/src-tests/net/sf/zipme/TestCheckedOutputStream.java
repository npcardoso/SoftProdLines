package net.sf.zipme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;

import net.test.ZipMETest;

import org.junit.Test;

import splat.ZipMeVariables;

public class TestCheckedOutputStream extends ZipMETest {

  @Test
  public void test0() throws Throwable {
    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
    Adler32 adler32_0 = new Adler32();
    assertNotNull(adler32_0);

    CheckedOutputStream checkedOutputStream0 = new CheckedOutputStream(
        byteArrayOutputStream0, adler32_0);
    byte[] byteArray0 = new byte[8];
    byteArray0[2] = (byte) (-11);
    checkedOutputStream0.getChecksum();

  }

  @Test
  public void test1() throws Throwable {
    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(
        1738);
    Adler32 adler32_0 = new Adler32();
    CheckedOutputStream checkedOutputStream0 = new CheckedOutputStream(
        byteArrayOutputStream0, adler32_0);
    checkedOutputStream0.write((-1));
    checkedOutputStream0.flush();
    checkedOutputStream0.close();
  }

  @Test
  public void test2() throws Throwable {
    ZipMeVariables.getSINGLETON().setADLER32CHECKSUM___(true);
    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(
        1970);
    Adler32 adler32_0 = new Adler32();
    assertNotNull(adler32_0);

    CheckedOutputStream checkedOutputStream0 = new CheckedOutputStream(
        byteArrayOutputStream0, adler32_0);
    assertEquals(1, adler32_0.getValue());

    byte[] byteArray0 = new byte[7];
    adler32_0.update(byteArray0);
    byteArray0[1] = (byte) 0;
    checkedOutputStream0.write(byteArray0);
    assertEquals(7, byteArrayOutputStream0.size());
    checkedOutputStream0.flush();
    checkedOutputStream0.close();
  }

  @Test
  public void test4() throws Throwable {
    ZipMeVariables.getSINGLETON().setADLER32CHECKSUM___(true);
    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
    Adler32 adler32_0 = new Adler32();
    assertNotNull(adler32_0);

    CheckedOutputStream checkedOutputStream0 = new CheckedOutputStream(
        byteArrayOutputStream0, adler32_0);
    byte[] byteArray0 = new byte[8];
    byteArray0[2] = (byte) (-11);
    checkedOutputStream0.getChecksum();
    
    checkedOutputStream0.write((-1));
    checkedOutputStream0.flush();
    checkedOutputStream0.close();

    ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream(
        1970);
    Adler32 adler32_1 = new Adler32();
    assertNotNull(adler32_1);

    CheckedOutputStream checkedOutputStream1 = new CheckedOutputStream(
        byteArrayOutputStream1, adler32_1);
    assertEquals(1, adler32_1.getValue());

    byte[] byteArray1 = new byte[7];
    adler32_1.update(byteArray1);
    byteArray1[1] = (byte) 0;
    checkedOutputStream1.write(byteArray1);
    assertEquals(7, byteArrayOutputStream1.size());
    checkedOutputStream1.flush();
    checkedOutputStream1.close();

  }

}
