package net.sf.zipme;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestOutputWindow extends ZipMeTest {

  @Test
  public void test0() throws Throwable {
    OutputWindow outputWindow0 = new OutputWindow();
    assertNotNull(outputWindow0);

    byte[] byteArray0 = new byte[5];
    byte byte0 = (byte) 7;
    outputWindow0.write(1964);
    assertEquals(1, outputWindow0.getAvailable());
    assertEquals(32767, outputWindow0.getFreeSpace());
  }

  @Test
  public void test1() throws Throwable {
    OutputWindow outputWindow0 = new OutputWindow();
    assertNotNull(outputWindow0);
    assertEquals(0, outputWindow0.getAvailable());

    outputWindow0.reset();
    outputWindow0.repeat(1, 1);
    outputWindow0.write(32767);
    assertEquals(32766, outputWindow0.getFreeSpace());
  }

  @Test
  public void test2() throws Throwable {
    OutputWindow outputWindow0 = new OutputWindow();
    assertNotNull(outputWindow0);

    byte[] byteArray0 = new byte[5];
    byteArray0[0] = (byte) 0;
    byteArray0[1] = (byte) (-27);
    byteArray0[2] = (byte) 95;
    byteArray0[3] = (byte) 31;
    byteArray0[4] = (byte) (-48);
    outputWindow0.copyDict(byteArray0, (byte) 0, 2);
    outputWindow0.repeat(byteArray0[0], byteArray0[0]);
    byte byte0 = (byte) 12;
    byte byte1 = (byte) 12;
    outputWindow0.getAvailable();
  }

  @Test
  public void test5() throws Throwable {
    OutputWindow outputWindow0 = new OutputWindow();
    outputWindow0.repeat(0, 0);
    OutputWindow outputWindow1 = new OutputWindow();
    StreamManipulator streamManipulator0 = new StreamManipulator();
    assertNotNull(streamManipulator0);

    int int0 = 0;
    outputWindow1.repeat(1615, 1459);
    outputWindow1.copyStored(streamManipulator0, int0);

    assertEquals(0, streamManipulator0.getAvailableBytes());
    assertEquals(1615, outputWindow1.getAvailable());
  }

  @Test
  public void test6() throws Throwable {
    OutputWindow outputWindow0 = new OutputWindow();
    assertEquals(32768, outputWindow0.getFreeSpace());
    assertNotNull(outputWindow0);

    int int0 = outputWindow0.getFreeSpace();
    outputWindow0.write(int0);
  }

  @Test
  public void test7() throws Throwable {
    OutputWindow outputWindow0 = new OutputWindow();
    assertNotNull(outputWindow0);

    byte[] byteArray0 = new byte[6];
    byte byte0 = (byte) 0;
    outputWindow0.copyOutput(byteArray0, 0, 0);

  }

}
