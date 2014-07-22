package net.sf.zipme;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestStreamManipulator extends ZipMeTest {

  @Test
  public void test0() throws Throwable {
    StreamManipulator streamManipulator0 = new StreamManipulator();
    assertNotNull(streamManipulator0);

    streamManipulator0.getAvailableBits();
  }

  @Test
  public void test1() throws Throwable {
    StreamManipulator streamManipulator0 = new StreamManipulator();
    assertEquals(0, streamManipulator0.getAvailableBytes());
    assertNotNull(streamManipulator0);
    assertEquals(0, streamManipulator0.getAvailableBits());
    assertEquals(-1, streamManipulator0.getBits((byte) 10));

  }

  @Test
  public void test2() throws Throwable {
    StreamManipulator streamManipulator0 = new StreamManipulator();
    assertNotNull(streamManipulator0);
    assertEquals(0, streamManipulator0.getAvailableBytes());

    streamManipulator0.skipToByteBoundary();

    assertEquals(0, streamManipulator0.getBits((byte) 0));
    assertEquals(-1, streamManipulator0.peekBits(1978));

  }

  @Test
  public void test3() throws Throwable {
    StreamManipulator streamManipulator0 = new StreamManipulator();
    assertNotNull(streamManipulator0);

    streamManipulator0.dropBits((-2023));
    assertEquals(2023, streamManipulator0.getAvailableBits());
    assertEquals(252, streamManipulator0.getAvailableBytes());
  }

  @Test
  public void test4() throws Throwable {
    StreamManipulator streamManipulator0 = new StreamManipulator();
    assertNotNull(streamManipulator0);

    int int1 = streamManipulator0.getAvailableBytes();
    assertEquals(0, streamManipulator0.getAvailableBytes());
    assertEquals(0, streamManipulator0.getAvailableBits());
    assertEquals(0, int1);
  }

  @Test
  public void test5() throws Throwable {
    StreamManipulator streamManipulator0 = new StreamManipulator();
    assertNotNull(streamManipulator0);

    streamManipulator0.needsInput();

  }

}
