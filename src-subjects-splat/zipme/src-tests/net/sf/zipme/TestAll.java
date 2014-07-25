package net.sf.zipme;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  TestMain_REPAIR.class,
  FirstSuit_REPAIR.class, 
  GZIPtest.class, 
  LittleFeatures.class,
  Addler32CheckSumTest.class, 
  CompressAdlerCSTests.class,
  DerivGZIPEXTRTest.class, 
  DerivGZIPEXTRTest2.class, 
  ExtractTest.class,
  Example_Paulo2_REPAIR.class,
  TestStreamManipulator.class,
  TestOutputWindow.class,
  TestDeflate.class,
  TestCheckedInputStream.class,
  TestCheckedOutputStream.class,
  TestOutputWindow.class,
  TestStreamManipulator.class
})

public class TestAll{ }