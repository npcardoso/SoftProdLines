package net.sf.zipme;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import splat.ZipMeVariables;

public class TestAll_bnf_cin extends ZipMeTest {

  
  @Override
  protected void configure() {
    // set mandatory features
    super.configure();
    if (testName == null) {
      throw new RuntimeException();
    }
    String strTestName = testName.getMethodName();
    if (strTestName.equals("test1")
        || strTestName.equals("test2")
        || strTestName.equals("test3")
        || strTestName.equals("test4")
        || strTestName.equals("test5")
        || strTestName.equals("test6")
        || strTestName.equals("test10")
        || strTestName.equals("test11")
        || strTestName.equals("test12")
        || strTestName.equals("test13")
        || strTestName.equals("test14")
        || strTestName.equals("test15")) {
    } else if (strTestName.equals("test7")
        || strTestName.equals("test8")
        || strTestName.equals("test9")){
//      ZipMeVariables.getSINGLETON().setADLER32CHECKSUM___(true);
    } else {
      System.err.printf("%s did not set default configuration", strTestName);
    }
  }
  
	Adler32 ad;
	Inflater inf;
	ZipEntry zpe;
	Deflater def;

	@Before
	public void setUp() throws Exception {
		ad = new Adler32();
		inf = new Inflater();
		zpe = new ZipEntry("teste");
		def = new Deflater();
		// ad.update(2);
		// System.out.println(ad.getValue());
		// System.out.println(zpe.isDirectory());
	}
	
	
	@Test
	public void test1() {

		assertEquals("Teste 1", false, inf.finished());

	}
	
	@Test
	public void test2() {

		assertEquals("Teste 1", 0, inf.getTotalIn());

	}
	
	@Test
	public void test3() {

		assertEquals("Teste 1", false, inf.needsDictionary());

	}
	
	@Test
	public void test4() {
		assertEquals("Teste 1",false, zpe.isDirectory());

	}
	
	// maybe repeated or nonsense
	@Test
	public void test5() {

		assertEquals("Teste 1", false, zpe.isDirectory());

	}
	
	// maybe repeated or nonsense
	@Test
	public void test6() {

		assertEquals("Teste 1", false, zpe.isDirectory());

	}
	
//	// maybe repeated or nonsense
//	@Test
//	public void test7() {
//		ad.reset();
//		assertEquals("Teste 1", 1, ad.getValue());
//
//	}

	// maybe repeated or nonsense
	@Test
	public void test8() {
		ad.update(12);
		assertEquals("Teste 1", 851981, ad.getValue());
	}

//	@Test
//	public void test9() {
//		ad.update(12);
//		assertEquals("Teste 1", 851981, ad.getValue());
//	}

	@Test
	public void test10() {
		assertEquals("Teste 1", "teste", zpe.getName());
	}

	@Test
	public void test11() {
		assertEquals("Teste 1", -1, zpe.getTime());
	}
	
	// maybe repeated or nonsense
	@Test
	public void test12() {
		assertEquals("Teste 1", -1, zpe.getTime());
	}
	
	@Test
	public void test13() {
		zpe.setSize(21L);
		assertEquals("Teste 1", 21, zpe.getSize());
	}

	@Test
	public void test14() {
		zpe.setMethod(8);
		assertEquals("Teste 1", 8, zpe.getMethod());
	}
	
	@Test
	public void test15() {
		zpe.setCompressedSize(5);
		assertEquals("Teste 1", 5, zpe.getCompressedSize());
	}

}