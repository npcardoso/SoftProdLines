package sample;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.rules.Timeout;

public class Foo {

  @Rule
  public TestName testName = new TestName();

  @Rule
  public Timeout globalTimeout = new Timeout(3000);

  @Before
  public void setup() {
    if (testName.getMethodName().equals("foo1")) {
      // do nothing
    } else if (testName.getMethodName().equals("foo2")) {
      FooVariables.getSingleton().setF2(true);
    } else if (testName.getMethodName().equals("foo3")
        || testName.getMethodName().equals("foo4")
        || testName.getMethodName().equals("foo_loop")) {
    } else if (testName.getMethodName().equals("foo_exception")) {
      FooVariables.getSingleton().setF2(true);
    }

  }

  @Test
  public void foo1() {  }

  @Test
  public void foo2() {
    FooVariables.getSingleton().getF2();
    FooVariables.getSingleton().getF1();
  }

  @Test
  public void foo3() {
    if (FooVariables.getSingleton().getF1()) {
      if (FooVariables.getSingleton().getF2()) {
        
      }
    }
    if (FooVariables.getSingleton().getF3()) {
      
    }
    if (FooVariables.getSingleton().getF4()) {
      
    }
    if (FooVariables.getSingleton().getF7()) {
      if (FooVariables.getSingleton().getF6()) {
        
      }
      if (FooVariables.getSingleton().getF5()) {
        
      }
    }
    
    if (FooVariables.getSingleton().getF10()) {
      // do nothing
    } else {
      // do nothing
      if (FooVariables.getSingleton().getF12()) {
        throw new RuntimeException();
      }
    }
  }

  @Test
  public void foo_loop() {
    FooVariables.getSingleton().getF1();
    FooVariables.getSingleton().getF2();
    while (true) {
      // System.out.println(".");
    }
  }

  @Test
  public void foo_exception() {
    int[] numbers = new int[] { 5 };
    @SuppressWarnings("unused")
    int number;
    FooVariables.getSingleton().getF1();
    FooVariables.getSingleton().getF2();
    if (FooVariables.getSingleton().getF3()) {
      number = numbers[1]; // raises an exception
    } else {
      number = numbers[0];
    }
  }

}