package util;

import java.util.Arrays;
/**
 * Since last visited: 10 points in stack overflow
 * 
 * http://stackoverflow.com/questions/7988486/how-do-you-calculate-the-variance-median-and-standard-deviation-in-c-or-java *
 */

public class Statistics {

  /***
   * operations on double[] 
   */
  
  public static double getMean(double[] data) {
    double size = data.length;
    double sum = 0.0;
    for(double a : data)
      sum += a;
    return sum/size;
  }

  public static double getVariance(double[] data) {
    double size = data.length;
    double mean = getMean(data);
    double temp = 0;
    for(double a :data)
      temp += (mean-a)*(mean-a);
    return temp/size;
  }

  public static double getStdDev(double[] data) {
    return Math.sqrt(getVariance(data));
  }

  public double median(double[] data) {
    double[] b = new double[data.length];
    System.arraycopy(data, 0, b, 0, b.length);
    Arrays.sort(b);
    if (data.length % 2 == 0) {
      return (b[(b.length / 2) - 1] + b[b.length / 2]) / 2.0;
    } else {
      return b[b.length / 2];
    }
  }
  
  public static double min(double[] data) {
    double min = Double.MAX_VALUE;
    for (int i = 0; i < data.length; i++) {
      if (data[i] < min) {
        min = data[i];
      }
    }
    return min;
  }
  
  public static double max(double[] data) {
    double max = Double.MIN_VALUE;
    for (int i = 0; i < data.length; i++) {
      if (data[i] > max) {
        max = data[i];
      }
    }
    return max;
  }

  /***
   * operations on int[] 
   */

  
  private static double[] toDoubleArray(int[] ar) {
    double[] result = new double[ar.length];
    for (int i = 0; i < ar.length; i++) {
      result[i] = (double) ar[i];
    }
    return result;
  }
  
  public static double getMean(int[] data) {
    return getMean(toDoubleArray(data));
  }
  
  public static double getStdDev(int[] data) {
    return getStdDev(toDoubleArray(data));
  }
  
  public static double min(int[] data) {
    return min(toDoubleArray(data));
  }
  
  public static double max(int[] data) {
    return max(toDoubleArray(data));
  }

  public static int sum(int[] numConfPerTest) {
    int result = 0;
    for (int i = 0; i < numConfPerTest.length; i++) {
      result += numConfPerTest[i];
    }
    return result;
  }

  public static double getMedian(int[] ar) {
    int[] numArray = Arrays.copyOf(ar, ar.length);
    Arrays.sort(numArray);
    double median;
    if ((numArray.length % 2) == 0) {
      median = ((double)numArray[numArray.length/2] + (double)numArray[numArray.length/2+1])/2;
    } else {
      median = (double) numArray[numArray.length/2];
    }
    return median;
  }
  
}