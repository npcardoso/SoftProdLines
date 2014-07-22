package net.sf.zipme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import net.test.GZIPTest;
import net.test.ZipMETest;

import org.junit.Test;

import splat.ZipMeVariables;

public class TestMain_REPAIR extends ZipMeTest {


  @Test
  public void testIntegration06() throws IOException {
    ZipMeVariables.getSINGLETON().setADLER32CHECKSUM___(true);//REPAIR
    ZipMETest.main(new String[] {});
    GZIPTest.main(new String[] {});

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

    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
    CheckedOutputStream checkedOutputStream0 = new CheckedOutputStream(
        byteArrayOutputStream0, adler32_0);
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

    Deflater deflater0 = new Deflater(0);
    assertNotNull(deflater0);
    deflater0.setInput(new byte[] { 0, 1, 2, 3, 4, 5, 6, 7 });
    deflater0.end();
    assertEquals(127, deflater0.state);

    OutputWindow outputWindow0 = new OutputWindow();
    outputWindow0.repeat(0, 0);
    OutputWindow outputWindow1 = new OutputWindow();
    StreamManipulator streamManipulator0 = new StreamManipulator();
    assertNotNull(streamManipulator0);

    outputWindow1.repeat(1615, 1459);
    outputWindow1.copyStored(streamManipulator0, 0);

    assertEquals(0, streamManipulator0.getAvailableBytes());
    assertEquals(1615, outputWindow1.getAvailable());
  }

}
