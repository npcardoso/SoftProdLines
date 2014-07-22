package net.sf.zipme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.junit.Ignore;
import org.junit.Test;



public class TestAll_jald_cin extends ZipMeTest{
  
//  @Override
//  protected void configure() {
//    // set mandatory features
//    super.configure();
//    if (testName == null) {
//      throw new RuntimeException();
//    }
//    String strTestName = testName.getMethodName();
//    if (strTestName.equals("ziparArquivoTest")
//        || strTestName.equals("unzipArquivoTest")
//        || strTestName.equals("mutante01Test")
//        || strTestName.equals("mutante02Test")
//        || strTestName.equals("mutante03Test")
//        || strTestName.equals("mutante04Test")
//        || strTestName.equals("mutante05Test")
//        || strTestName.equals("mutante06Test")
//        || strTestName.equals("mutante07Test")
//        || strTestName.equals("mutante08Test")
//        || strTestName.equals("mutante09Test")
//        || strTestName.equals("mutante10Test")
//        || strTestName.equals("mutante11Test")
//        || strTestName.equals("mutante12Test")
//        || strTestName.equals("mutante13Test")
//        || strTestName.equals("mutante14Test")
//        || strTestName.equals("mutante15Test")) {
////      ZipMeVariables.getSINGLETON().setCOMPRESS___(true);
////      ZipMeVariables.getSINGLETON().setGZIP___(true);
////      ZipMeVariables.getSINGLETON().setEXTRACT___(true);
////      ZipMeVariables.getSINGLETON().setARCHIVECHECK___(true);
////      ZipMeVariables.getSINGLETON().setCRC___(true);
////      ZipMeVariables.getSINGLETON().setDERIVATIVE_COMPRESS_CRC___(true);
////      ZipMeVariables.getSINGLETON().setDERIVATIVE_COMPRESS_GZIP___(true);
////      ZipMeVariables.getSINGLETON().setDERIVATIVE_COMPRESS_GZIPCRC___(true);
////      ZipMeVariables.getSINGLETON().setDERIVATIVE_EXTRACT_CRC___(true);
////      ZipMeVariables.getSINGLETON().setADLER32CHECKSUM___(true);
//    } else {
//      System.err.printf("%s did not set default configuration", strTestName);
//    }
//  }

  static File fileToZip = new File("../zipme/src-tests/net/sf/zipme/file.txt");
  static File zipFile = new File(
      "../zipme/src-tests/net/sf/zipme/file.txt.zip");
  static File unzipFile = new File(
      "../zipme/src-tests/net/sf/zipme/file.txt-Copy.txt");

   
//  @Ignore
	@Test
	public void ziparArquivoTest() throws IOException{

		zip(fileToZip, zipFile);
		assertTrue(zipFile.exists());

		zipFile.delete();	
	}
    
//  @Ignore
	@Test
	public void unzipArquivoTest() throws IOException{
	
		zip(fileToZip, zipFile);
		unzip(zipFile, unzipFile);

		zipFile.delete();	
		unzipFile.delete();

	}
	
	/*
	 * MUTANTE 01
	 * 
	 * net.sf.zipme.ZipEntry:57
	 * 
	 * Correto:
	 * if (length > 65535)
	 * 
	 * Mutante
	 * if (length < 65535)
	 * 
	 */
    
  // maybe repeated or nonsense
	@Test
	public void mutante01Test(){
		String nomeGrande = "";

		for(int i = 0; i < 65534; i++){
			nomeGrande+="a";
		}

		ZipEntry ze = new ZipEntry(nomeGrande);
	}
	
	/*
	 * MUTANTE 02
	 * 
	 * net.sf.zipme.Inflater:160
	 * 
	 * Correto:
	 * return (int) (totalIn - getRemaining());
	 * 
	 * Mutante
	 * return (int) (totalIn - getAvailableBits());
	 * 
	 */	
  // maybe repeated or nonsense
	@Test
	public void mutante02Test(){
		Inflater inf = new Inflater(true);
		assertEquals(0, inf.getTotalIn());
	}
    
	/*
	 * MUTANTE 03
	 * 
	 * net.sf.zipme.PendingBuffer:127
	 * 
	 * Correto:
	 *  start = 0;
	 * 
	 * Mutante
	 * 	length = (end - start) + 8;;
	 * 
	 */	
	@Test
	public void mutante03Test() {
		PendingBuffer pb = new PendingBuffer();
		assertEquals(0, pb.flush(new byte[5], 0, 5));
	}
	
	/*
	 * MUTANTE 04
	 * 
	 * net.sf.zipme.ZipEntry:59
	 * 
	 * Correto:
	 * this.name = name;
	 * 
	 * Mutante
	 * this.name = "NONE_PADRAO";
	 * 
	 */
	// maybe repeated or nonsense
	@Test
	public void mutante04Test(){
		String nome = "arquivo";
		ZipEntry ze = new ZipEntry(nome);
		ZipEntry ze2 = new ZipEntry(ze, nome);
		assertEquals(nome, ze2.getName());
		
	}
	
	/*
	 * MUTANTE 05
	 * 
	 * net.sf.zipme.ZipEntry:131
	 * 
	 * Correto:
	 * if ((known & KNOWN_TIME) == 0)
	 * 
	 * Mutante
	 * if ((known & KNOWN_TIME) != 0)
	 * 
	 */
	// maybe repeated or nonsense
	@Test
	public void mutante05Test(){
		String nome = "arquivo";
		ZipEntry ze = new ZipEntry(nome);
		ze.setTime(8474);
		assertEquals(4039372808474l,ze.getTime());
		
	}
	
	/*
	 * MUTANTE 06
	 * 
	 * net.sf.zipme.ZipEntry:326
	 * 
	 * Correto:
	 * return nlen > 0 && name.charAt(nlen - 1) == '/';
	 * 
	 * Mutante
	 * return nlen > 0 && name.charAt(nlen - 1) == '|';
	 * 
	 */
	@Test
	public void mutante06Test(){

		String nome = "pasta/";		
		ZipEntry ze = new ZipEntry(nome);
		assertTrue(ze.isDirectory());

	}
	
	/*
	 * MUTANTE 07
	 * 
	 * net.sf.zipme.ZipEntry:326
	 * 
	 * Correto:
	 * throw new IllegalArgumentException();
	 * 
	 * Mutante
	 * throw new NullPointerException();
	 * 
	 */

	@Test(expected=IllegalArgumentException.class)
	public void mutante07Test(){

		String comentario = "";
		for(int i = 0; i < 65537; i++){
			comentario+="a";
		}
		ZipEntry ze = new ZipEntry("arquivo");
		ze.setComment(comentario);

	}
	
	/*
	 * MUTANTE 08
	 * 
	 * net.sf.zipme.DeflaterEngine:503
	 * 
	 * Correto:
	 * return inputEnd == inputOff;
	 * 
	 * Mutante
	 * return inputEnd != inputOff;
	 * 
	 */
	@Test
	public void mutante08Test(){

		Deflater df = new Deflater();
		assertTrue(df.needsInput());
		
	}
	
	/*
	 * MUTANTE 9
	 * 
	 * net.sf.zipme.InflaterInputStream:188
	 * 
	 * Correto:
	 * 	throw new IllegalArgumentException();
	 * 
	 * Mutante
	 *  throw new IOException("stream closed");
	 * 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void mutante09Test() throws IOException{

		InflaterInputStream  iis = new InflaterInputStream(new InputStream() {
		@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
		});
		
		iis.skip(-9);
	}
	
	/*
	 * MUTANTE 10
	 * 
	 * net.sf.zipme.Inflater:253
	 * 
	 * Correto:
	 * 	int count = 0;
	 * 
	 * Mutante
	 *  int count = ++off;
	 * 
	 */
	// maybe repeated or nonsense
	@Test
	public void mutante10Test() throws IOException, DataFormatException{

		Inflater inf = new Inflater();
		assertEquals(0 , inf.inflate( new byte[1], 0, 1));

	}
	
	/*
	 * MUTANTE 11
	 * 
	 * net.sf.zipme.StreamManipulator:22
	 * 
	 * Correto:
	 * 	private int window_end = 0;
	 * 
	 * Mutante
	 *  private int window_end = 10;
	 * 
	 */
	@Test
	public void mutante11Test() throws IOException{

		Inflater inf = new Inflater();
		assertTrue(inf.needsInput());
		
	}
	
	/*
	 * MUTANTE 12
	 * 
	 * net.sf.zipme.StreamManipulator:22
	 * 
	 * Correto:
	 * 	return window_end - window_start + (bits_in_buffer >> 3);
	 * 
	 * Mutante
	 *  return (window_end + 3) + bits_in_buffer - window_start;
	 * 
	 */
	@Test
	public void mutante12Test(){

		Inflater inf = new Inflater();
		assertEquals(0, inf.getRemaining());
		
	}
	
	/*
	 * MUTANTE 13
	 * 
	 * net.sf.zipme.PendingBuffer:31
	 * 
	 * Correto:
	 * 	int numBytes = super.read(buffer, offset, length);
	 * 
	 * Mutante
	 *  int numBytes = super.read(-1, -1, -1);
	 * 
	 */
	@Test
	public void mutante13Test() throws ZipException, IOException{
		PendingBuffer pendingBuffer = new PendingBuffer();
		
		 ZipArchive_PartialInputStream zip = new ZipArchive_PartialInputStream(new byte[5], 0, 5);
		 assertEquals(2, zip.read(new byte[2], 0, 2));

	}
		
	/*
	 * MUTANTE 14
	 * 
	 * net.sf.zipme.ZipOutputStream:160
	 * 
	 * Correto:
	 * if (method == STORED)
	 * 
	 * Mutante
	 * if (method != STORED)
	 * 
	 */
	@Test (expected = ZipException.class)
	public void mutante14Test() throws IOException{
		
	  criarArquivo("../zipme/src-tests/net/sf/zipme/Teste", "");

    File in = new File("../zipme/src-tests/net/sf/zipme/Teste.txt");
    File out = new File("../zipme/src-tests/net/sf/zipme/Teste.txt.zip");
		
		FileInputStream inStream = new FileInputStream(in);
        ZipOutputStream outStream = new ZipOutputStream(new FileOutputStream(out));
        
        ZipEntry ze = new ZipEntry(in.getPath());
        ze.setMethod(ZipOutputStream.STORED);
        
        outStream.putNextEntry(ze);
	}
	
	/*
	 * MUTANTE 15
	 * 
	 * net.sf.zipme.ZipOutputStream:280
	 * 
	 * Correto:
	 * if (curEntry == null) 
	 * 
	 * Mutante
	 * if (curEntry != null)
	 * 
	 */
	@Ignore
	@Test
	public void mutante15Test() throws IOException{

		zip(fileToZip, zipFile);
		assertTrue(zipFile.exists());
		zipFile.delete();	

	}
	
	
//	// ==================== Auxiliar
    public static void zip(File in, File out) throws IOException {
        FileInputStream inStream = new FileInputStream(in);
        ZipOutputStream outStream = new ZipOutputStream(new FileOutputStream(
                out));
        outStream.putNextEntry((new ZipEntry(in.getPath())));
        byte[] buffer = new byte[0xFFFF];
        for (int len; (len = inStream.read(buffer)) != -1;) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        outStream.close();
//        System.out.println("Zipping file: Success");
    }
   
    public static void unzip(File in, File out) throws IOException {
        ZipInputStream inStream = new ZipInputStream(new FileInputStream(in));
        FileOutputStream outStream = new FileOutputStream(out);
        byte[] buffer = new byte[0xFFFF];
        for (int len; (len = inStream.read(buffer)) != -1;) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        outStream.close();
//        System.out.println("Unzipping file: Success");
    }

    public static void criarArquivo(String nome, String conteudo){
    	
    	FileWriter arquivo;

    	try {
			arquivo = new FileWriter(new File(nome + ".txt"));
			arquivo.write(conteudo);
			arquivo.close();
		
    	} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
  @Test
  public void testTotal() throws IOException {
    zip(fileToZip, zipFile);
    assertTrue(zipFile.exists());
    zipFile.delete();
    zip(fileToZip, zipFile);
    unzip(zipFile, unzipFile);
    zipFile.delete();
    unzipFile.delete();

    Inflater inf = new Inflater(true);
    assertEquals(0, inf.getTotalIn());

    PendingBuffer pb = new PendingBuffer();
    assertEquals(0, pb.flush(new byte[5], 0, 5));

    PendingBuffer pendingBuffer = new PendingBuffer();

    ZipArchive_PartialInputStream zip = new ZipArchive_PartialInputStream(
        new byte[5], 0, 5);
    assertEquals(2, zip.read(new byte[2], 0, 2));

    criarArquivo("../zipme/src-tests/net/sf/zipme/Teste", "");

    File in = new File("../zipme/src-tests/net/sf/zipme/Teste.txt");
    File out = new File("../zipme/src-tests/net/sf/zipme/Teste.txt.zip");

    FileInputStream inStream = new FileInputStream(in);
    ZipOutputStream outStream = new ZipOutputStream(new FileOutputStream(out));

    ZipEntry ze = new ZipEntry(in.getPath());
    ze.setMethod(ZipOutputStream.STORED);

  }
    
    
}
