package sample;

import splat.SPLat;

public class Main {
  
  public static void main(String[] args) throws Exception {
    SPLat splat = new SPLat(FooVariables.getSingleton());
    splat.run(args);
  }

}