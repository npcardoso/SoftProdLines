package net.sf.zipme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import splat.ZipMeVariables;

public class FirstSuit_REPAIR extends ZipMeTest {

  @Test
  /*
   * This test includes the methods: net.sf.zipme.Deflater.deflate(byte[])
   * net.sf.zipme.Deflater.deflate(byte[], int, int) net.sf.zipme.Deflater.end()
   * net.sf.zipme.Deflater.finish() net.sf.zipme.Deflater.finished()
   * net.sf.zipme.Deflater.getBytesWritten()
   */
  public void testDeflation() {
    ZipMeVariables.getSINGLETON().setCOMPRESS___(true);

    byte[] b = { 1, 2, 3 };
    Deflater d = new Deflater();

    assertEquals(d.state, 0x00);
    d.finish();
    assertEquals(d.state, 0x0C);

    assertNotSame(d.deflate(b), 0);
    assertEquals(d.getBytesWritten(), 3);

    assertNotSame(d.deflate(b), -1);
    // assertTrue(d.finished());
    // REPAIR
    if (!ZipMeVariables.getSINGLETON()
        .isDERIVATIVE_COMPRESS_ADLER32CHECKSUM___())
      assertTrue(d.finished());
    else
      assertFalse(d.finished());

    d.end();
    assertEquals(d.engine, null);
  }

  @After
  public void teardown() {
//    ZipMeVariables.getSINGLETON().restore();

  }
}
