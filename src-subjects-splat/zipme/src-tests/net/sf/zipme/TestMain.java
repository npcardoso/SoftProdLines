package net.sf.zipme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.test.GZIPTest;
import net.test.ZipMETest;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import splat.ZipMeVariables;

public class TestMain extends ZipMeTest {

  // @Override
  // protected void configure() {
  // // set mandatory features
  // super.configure();
  // if (testName == null) {
  // throw new RuntimeException();
  // }
  // String strTestName = testName.getMethodName();
  // if (strTestName.equals("testBestScenario")
  // || strTestName.equals("testAverageScenario")
  // || strTestName.equals("testWorstScenario")) {
  // // it works with just BASE___ feature enabled
  // } else {
  // System.err.printf("%s did not set default configuration", strTestName);
  // }
  // }

//  @Test
//  public void testBestScenario() throws IOException {
//    ZipMETest.main(new String[] {});
//  }
//
//  @Test
//  public void testAverageScenario() throws IOException {
//    GZIPTest.main(new String[] {});
//  }
//
//  @Test
//  public void testWorstScenario() throws IOException {
//    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(
//        1738);
//    Adler32 adler32_0 = new Adler32();
//    CheckedOutputStream checkedOutputStream0 = new CheckedOutputStream(
//        byteArrayOutputStream0, adler32_0);
//    checkedOutputStream0.write((-1));
//    byteArrayOutputStream0.size();
//    byteArrayOutputStream0.toString();
//    assertEquals(1, byteArrayOutputStream0.size());
//  }
//
//  private static File in = new File("../zipme/src-tests/net/sf/zipme/Teste.txt");
//  private static File zipIn = new File(
//      "../zipme/src-tests/net/sf/zipme/banco.gz");
//
//  @Test
//  public void testZipArchive1() throws ZipException, IOException {
//    FileInputStream inStream = new FileInputStream(zipIn);
//    ZipArchive zipArchive = new ZipArchive(inStream);
//    zipArchive.hook1();
//  }
//
//  @Test
//  public void testZipArchive2() throws ZipException, IOException {
//    FileInputStream inStream = new FileInputStream(zipIn);
//    ZipArchive zipArchive = new ZipArchive(inStream);
//    zipArchive.hook1();
//    zipArchive.entries();
//  }
//
//  @Test
//  public void testZipInputStream1() throws IOException {
//    ZipInputStream inStream = new ZipInputStream(new FileInputStream(in));
//    inStream.hook();
//    inStream.read();
//    inStream.close();
//  }
//
//  @Test
//  public void testZipInputStream2() throws IOException {
//    ZipInputStream inStream = new ZipInputStream(new FileInputStream(in));
//    inStream.hook36();
//    inStream.read();
//    inStream.close();
//  }
//
//  @Test
//  public void testZipInputStream3() throws IOException {
//    ZipInputStream inStream = new ZipInputStream(new FileInputStream(in));
//    inStream.hook38();
//    inStream.read();
//    inStream.close();
//  }
//
//  @Test
//  public void testZipInputStream4() throws IOException {
//    ZipInputStream inStream = new ZipInputStream(new FileInputStream(in));
//    inStream.hook();
//    inStream.hook36();
//    inStream.hook38();
//    inStream.read();
//    inStream.closeEntry();
//  }
//
//  @Test
//  public void testZipInputStream5() throws IOException {
//    ZipInputStream inStream = new ZipInputStream(new FileInputStream(in));
//    inStream.hook();
//    inStream.hook36();
//    inStream.hook38();
//    inStream.read();
//    inStream.closeEntry();
//    inStream.close();
//  }
//
//  @Ignore
//  @Test
//  public void testZipInputStream6() throws IOException {
//    ZipInputStream inStream = new ZipInputStream(new FileInputStream(in));
//    inStream.getNextEntry();
//    inStream.close();
//  }
//
//  @Test
//  public void testZipInputStream7() throws IOException {
//    File out = new File("../zipme/src-tests/net/sf/zipme/Teste.txt.zip");
//    ZipOutputStream outStream = new ZipOutputStream(new FileOutputStream(out));
//
//    ZipEntry ze = new ZipEntry(in.getPath());
//    ze.setMethod(ZipOutputStream.DEFLATED);
//
//    ZipInputStream inStream = new ZipInputStream(new FileInputStream(in));
//    inStream.read(new byte[3], 0, 0);
//    // inStream.getNextEntry();
//    inStream.close();
//  }
//
//  @Test
//  public void testZipInputStream8() throws IOException {
//    File out = new File("../zipme/src-tests/net/sf/zipme/Teste.txt");
//    ZipOutputStream outStream = new ZipOutputStream(new FileOutputStream(out));
//    outStream.putNextEntry(new ZipEntry("entry1"));
//    outStream.putNextEntry(new ZipEntry("entry2"));
//    outStream.putNextEntry(new ZipEntry("entry3"));
//    outStream.write(new byte[100]);
//    outStream.write(0);
//    ZipEntry ze = new ZipEntry(in.getPath());
//    ze.setMethod(ZipOutputStream.STORED);
//
//    ZipInputStream inStream = new ZipInputStream(new FileInputStream(in));
//    inStream.read(new byte[3], 0, 0);
//    inStream.createZipEntry("entry1");
//    inStream.createZipEntry("entry2");
//    inStream.createZipEntry("entry3");
//    inStream.createZipEntry("entry4");
//    inStream.createZipEntry("entry5");
//    inStream.getNextEntry();
//    inStream.close();
//  }
//
//  @Test
//  public void testDeflaterEngine1() {
//    DeflaterPending deflaterPending = new DeflaterPending();
//    DeflaterEngine deflaterEngine = new DeflaterEngine(deflaterPending);
//    deflaterEngine.getTotalIn();
//  }
//
//  @Test
//  public void testDeflaterEngine2() {
//    DeflaterPending deflaterPending = new DeflaterPending();
//    DeflaterEngine deflaterEngine = new DeflaterEngine(deflaterPending);
//    deflaterEngine.hook27();
//  }
//
//  @Test
//  public void testDeflaterEngine3() {
//    DeflaterPending deflaterPending = new DeflaterPending();
//    DeflaterEngine deflaterEngine = new DeflaterEngine(deflaterPending);
//    boolean boolean0 = true;
//    boolean boolean1 = false;
//    deflaterEngine.deflate(boolean0, boolean1);
//  }
//
//  @Test
//  public void testDeflaterEngine4() {
//    DeflaterPending deflaterPending = new DeflaterPending();
//    DeflaterEngine deflaterEngine = new DeflaterEngine(deflaterPending);
//    deflaterEngine.setStrategy(deflaterPending.end);
//  }
//
//  @Test
//  public void testDeflaterEngine5() {
//    DeflaterPending deflaterPending = new DeflaterPending();
//    DeflaterEngine deflaterEngine = new DeflaterEngine(deflaterPending);
//    deflaterEngine.setLevel(deflaterPending.bitCount);
//  }
//
//  @Test
//  public void testIntegration01() throws IOException {
//    ZipMETest.main(new String[] {});
//    GZIPTest.main(new String[] {});
//  }
//
//  @Test
//  public void testIntegration02() throws IOException {
//    ZipInputStream inStream = new ZipInputStream(new FileInputStream(in));
//    inStream.hook();
//    inStream.read();
//    inStream.close();
//    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(
//        1738);
//    Adler32 adler32_0 = new Adler32();
//    CheckedOutputStream checkedOutputStream0 = new CheckedOutputStream(
//        byteArrayOutputStream0, adler32_0);
//    checkedOutputStream0.write((-1));
//    byteArrayOutputStream0.size();
//    byteArrayOutputStream0.toString();
//    assertEquals(1, byteArrayOutputStream0.size());
//  }
//
//  @Test
//  public void testIntegration03() throws IOException {
//    DeflaterPending deflaterPending = new DeflaterPending();
//    DeflaterEngine deflaterEngine = new DeflaterEngine(deflaterPending);
//    boolean boolean0 = true;
//    boolean boolean1 = false;
//    deflaterEngine.deflate(boolean0, boolean1);
//    ZipInputStream inStream = new ZipInputStream(new FileInputStream(in));
//    inStream.hook();
//    inStream.hook36();
//    inStream.hook38();
//    inStream.read();
//    inStream.closeEntry();
//  }
//
//  @Test
//  public void testIntegration04() throws ZipException, IOException {
//    GZIPTest.main(new String[] {});
//    FileInputStream inStream = new FileInputStream(zipIn);
//    ZipArchive zipArchive = new ZipArchive(inStream);
//    zipArchive.hook1();
//    zipArchive.entries();
//    ZipMETest.main(new String[] {});
//  }
//
//  @Test
//  public void testIntegration05() throws IOException {
//    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(
//        1738);
//    Adler32 adler32_0 = new Adler32();
//    CheckedOutputStream checkedOutputStream0 = new CheckedOutputStream(
//        byteArrayOutputStream0, adler32_0);
//    checkedOutputStream0.write((-1));
//    byteArrayOutputStream0.size();
//    byteArrayOutputStream0.toString();
//    assertEquals(1, byteArrayOutputStream0.size());
//    GZIPTest.main(new String[] {});
//    DeflaterPending deflaterPending = new DeflaterPending();
//    DeflaterEngine deflaterEngine = new DeflaterEngine(deflaterPending);
//    deflaterEngine.getTotalIn();
//    ZipMETest.main(new String[] {});
//  }

  @Test
  public void testIntegration06() throws IOException {
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

//  @Test
//  public void testIntegration07() throws ZipException, IOException {
//    GZIPTest.main(new String[] {});
//    FileInputStream finStream = new FileInputStream(zipIn);
//    ZipArchive zipArchive = new ZipArchive(finStream);
//    zipArchive.hook1();
//    zipArchive.entries();
//    ZipMETest.main(new String[] {});
//    ZipInputStream inStream = new ZipInputStream(new FileInputStream(in));
//    inStream.hook();
//    inStream.hook36();
//    inStream.hook38();
//    inStream.read();
//    inStream.closeEntry();
//
//    File fileToZip = new File("src-tests/net/test/ZipMETest.java");
//    ZipEntry ze = new net.sf.zipme.ZipEntry(fileToZip.getPath());
//    ZipOutputStream zip = new ZipOutputStream(new OutputStream() {
//      public void write(int arg0) throws IOException {
//      }
//    });
//    zip.setComment("");
//
//    zip = new ZipOutputStream(new OutputStream() {
//      @Override
//      public void write(int b) throws IOException {
//      }
//    });
//    zip.setMethod(0);
//
//    Assert.assertEquals(-1, ze.getCrc());
//  }
//
//  @Test
//  public void testIntegration08() throws ZipException, IOException {
//    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(
//        1738);
//    Adler32 adler32_0 = new Adler32();
//    CheckedOutputStream checkedOutputStream0 = new CheckedOutputStream(
//        byteArrayOutputStream0, adler32_0);
//    checkedOutputStream0.write((-1));
//    byteArrayOutputStream0.size();
//    byteArrayOutputStream0.toString();
//    assertEquals(1, byteArrayOutputStream0.size());
//
//    Deflater deflater0 = new Deflater(0);
//    assertNotNull(deflater0);
//    deflater0.setInput(new byte[] { 0, 1, 2, 3, 4, 5, 6, 7 });
//    deflater0.end();
//    assertEquals(127, deflater0.state);
//
//    deflater0.totalOut = 1326L;
//    int int0 = deflater0.getTotalOut();
//    assertEquals(1326, deflater0.getTotalOut());
//    assertEquals(1326, int0);
//
//    deflater0.flush();
//    assertEquals(127, deflater0.state);
//
//    OutputWindow outputWindow0 = new OutputWindow();
//    assertNotNull(outputWindow0);
//
//    byte[] byteArray0 = new byte[6];
//    byte byte0 = (byte) 0;
//    outputWindow0.copyOutput(byteArray0, 0, 0);
//  }
//
////  @Test
////  public void testIntegration09() throws ZipException, IOException {
////    byte[] byteArray0 = new byte[3];
////    byte byte0 = (byte) (-1);
////    ZipArchive_PartialInputStream zipArchive_PartialInputStream0 = new ZipArchive_PartialInputStream(
////        byteArray0, (int) 16, (int) (-1));
////    assertNotNull(zipArchive_PartialInputStream0);
////
////    zipArchive_PartialInputStream0.setLength(287);
////    ZipArchive_PartialInputStream zipArchive_PartialInputStream1 = new ZipArchive_PartialInputStream(
////        byteArray0, (int) 16, (int) 16);
////    
////    ZipInputStream inStream = new ZipInputStream(new FileInputStream(in));
////    inStream.hook36();
////    inStream.read();
////    inStream.read(new byte[3], 0, 0);
////    inStream.createZipEntry("entry1");
////    inStream.createZipEntry("entry2");
////    inStream.createZipEntry("entry3");
////    inStream.createZipEntry("entry4");
////    inStream.createZipEntry("entry5");
////    inStream.getNextEntry();
////    inStream.close();
////    inStream.close();
////    
////    File out = new File("../zipme/src-tests/net/sf/zipme/Teste.txt");
////    ZipOutputStream outStream = new ZipOutputStream(new FileOutputStream(out));
////    outStream.putNextEntry(new ZipEntry("entry1"));
////    outStream.putNextEntry(new ZipEntry("entry2"));
////    outStream.putNextEntry(new ZipEntry("entry3"));
////    outStream.write(new byte[100]);
////    outStream.write(0);
////    ZipEntry ze = new ZipEntry(in.getPath());
////    ze.setMethod(ZipOutputStream.STORED);
////
////  }
//
//  @Test
//  public void testIntegration10() throws ZipException, IOException {
//    Deflater deflater0 = new Deflater(0);
//    assertNotNull(deflater0);
//    deflater0.setInput(new byte[]{0, 1, 2, 3, 4, 5, 6, 7});
//    deflater0.end();
//    assertEquals(127, deflater0.state);
//    
//    OutputWindow outputWindow0 = new OutputWindow();
//    assertNotNull(outputWindow0);
//
//    byte[] byteArray0 = new byte[5];
//    byteArray0[0] = (byte) 0;
//    byteArray0[1] = (byte) (-27);
//    byteArray0[2] = (byte) 95;
//    byteArray0[3] = (byte) 31;
//    byteArray0[4] = (byte) (-48);
//    outputWindow0.copyDict(byteArray0, (byte) 0, 2);
//    outputWindow0.repeat(byteArray0[0], byteArray0[0]);
//    byte byte0 = (byte) 12;
//    byte byte1 = (byte) 12;
//    outputWindow0.getAvailable();
//    
//    TestAll_jald_cin.zip(TestAll_jald_cin.fileToZip, TestAll_jald_cin.zipFile);
//    TestAll_jald_cin.unzip(TestAll_jald_cin.zipFile, TestAll_jald_cin.unzipFile);
//    TestAll_jald_cin.zipFile.delete(); 
//    TestAll_jald_cin.unzipFile.delete();
//
//  }
  
  

}
