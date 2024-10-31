//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Calculating Partitions
// Course: CS 300 Fall 2024
//
// Author: Tristin Yun
// Email: tyun7@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: NOBODY
// Online Sources: NONE. Like I actually did all of the brainstorming myself.
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Random;

/**
 * A class that tests the PartitionCalculator class
 *
 */
public class PartitionCalculatorTester {

  /**
   * Tests the correctness of PartitionCalculator.numOfPartitions method for base cases n = 1 and n
   * = 2. This should also account for unexpected exceptions that MAY occur.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testNumOfPartitionsBase() {
    int numPartitions1 = PartitionCalculator.numOfPartitions(1);
    int numPartitions2 = PartitionCalculator.numOfPartitions(2);

    if (numPartitions1 != TesterUtility.getPartitionCount(1))
      return false;
    if (numPartitions2 != TesterUtility.getPartitionCount(2))
      return false;

    return true;
  }

  /**
   * Tests the correctness of PartitionCalculator.numOfPartitions method for recursive cases with n
   * >= 3. There must be a total of 3 test cases. This should also account for unexpected exceptions
   * that MAY occur.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testNumOfPartitionsRecursive() {
    int numPartitions3 = PartitionCalculator.numOfPartitions(3);
    int numPartitions5 = PartitionCalculator.numOfPartitions(5);
    int numPartitions7 = PartitionCalculator.numOfPartitions(7);

    if (numPartitions3 != TesterUtility.getPartitionCount(3))
      return false;
    if (numPartitions5 != TesterUtility.getPartitionCount(5))
      return false;
    if (numPartitions7 != TesterUtility.getPartitionCount(7))
      return false;

    return true;
  }

  /**
   * Tests the correctness of PartitionCalculator.numOfPartitions method for multiple random cases
   * of N. (See write-up for more details). This should also account for unexpected exceptions that
   * MAY occur.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testNumOfPartitionsFuzz() {
    Random rand = new Random();
    int numTests = rand.nextInt(100) + 100;
    for (int i = 0; i < numTests; i++) {
      int testCase = rand.nextInt(50) + 1;
      int numPartitions = PartitionCalculator.numOfPartitions(testCase);
      if (numPartitions != TesterUtility.getPartitionCount(testCase))
        return false;
    }
    return true;
  }

  /**
   * Tests the correctness of PartitionCalculator.calculatePartitions method for base cases n = 1
   * and n = 2. This should also account for unexpected exceptions that MAY occur.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testCalcPartitionsBase() {
    ArrayList<Partition> partitions1 = PartitionCalculator.calculatePartitions(1);
    ArrayList<Partition> partitions2 = PartitionCalculator.calculatePartitions(2);

    if (!TesterUtility.comparePartitionLists(partitions1, TesterUtility.getPartitions(1, false),
        false))
      return false;
    if (!TesterUtility.comparePartitionLists(partitions2, TesterUtility.getPartitions(2, false),
        false))
      return false;

    return true;
  }


  /**
   * Tests the correctness of PartitionCalculator.calculatePartitions method for recursive cases
   * with n >= 3. There must be a total of 3 test cases. This should also account for unexpected
   * exceptions that MAY occur.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testCalcPartitionsRecursive() {
    ArrayList<Partition> partitions3 = PartitionCalculator.calculatePartitions(3);
    ArrayList<Partition> partitions5 = PartitionCalculator.calculatePartitions(5);
    ArrayList<Partition> partitions7 = PartitionCalculator.calculatePartitions(7);

    if (!TesterUtility.comparePartitionLists(partitions3, TesterUtility.getPartitions(3, false),
        false))
      return false;
    if (!TesterUtility.comparePartitionLists(partitions5, TesterUtility.getPartitions(5, false),
        false))
      return false;
    if (!TesterUtility.comparePartitionLists(partitions7, TesterUtility.getPartitions(7, false),
        false))
      return false;

    return true;
  }

  /**
   * Tests the correctness of PartitionCalculator.calculatePartitions method for multiple random
   * cases of N. (See write-up for more details). This should also account for unexpected exceptions
   * that MAY occur.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testCalcPartitionsFuzz() {
    Random rand = new Random();
    int numTests = rand.nextInt(20) + 1;
    for (int i = 0; i < numTests; i++) {
      int testCase = rand.nextInt(35) + 1;
      ArrayList<Partition> partitions = PartitionCalculator.calculatePartitions(testCase);
      if (!TesterUtility.comparePartitionLists(partitions,
          TesterUtility.getPartitions(testCase, false), false))
        return false;
    }
    return true;
  }

  /**
   * Tests the correctness of PartitionCalculator.calculateAllPermuations method for base cases n =
   * 1 and n = 2. This should also account for unexpected exceptions that MAY occur.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testCalculateAllPermutationsBase() {
    ArrayList<Partition> allPermutations1 =
        PartitionCalculator.calculateAllPermutations(PartitionCalculator.calculatePartitions(1));
    ArrayList<Partition> allPermutations2 =
        PartitionCalculator.calculateAllPermutations(PartitionCalculator.calculatePartitions(2));

    if (!TesterUtility.comparePartitionLists(allPermutations1, TesterUtility.getPartitions(1, true),
        true))
      return false;
    if (!TesterUtility.comparePartitionLists(allPermutations2, TesterUtility.getPartitions(2, true),
        true))
      return false;

    return true;
  }

  /**
   * Tests the correctness of PartitionCalculator.calculateAllPermutations method for recursive
   * cases with n >= 3. There must be a total of 3 test cases. This should also account for
   * unexpected exceptions that MAY occur.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testCalculateAllPermutationsRecursive() {
    ArrayList<Partition> allPermutations3 =
        PartitionCalculator.calculateAllPermutations(PartitionCalculator.calculatePartitions(3));
    ArrayList<Partition> allPermutations5 =
        PartitionCalculator.calculateAllPermutations(PartitionCalculator.calculatePartitions(5));
    ArrayList<Partition> allPermutations9 =
        PartitionCalculator.calculateAllPermutations(PartitionCalculator.calculatePartitions(9));

    if (!TesterUtility.comparePartitionLists(allPermutations3, TesterUtility.getPartitions(3, true),
        true))
      return false;
    if (!TesterUtility.comparePartitionLists(allPermutations5, TesterUtility.getPartitions(5, true),
        true))
      return false;
    if (!TesterUtility.comparePartitionLists(allPermutations9, TesterUtility.getPartitions(9, true),
        true))
      return false;

    return true;
  }

  /**
   * Runs and outputs the results of all tester methods.
   * 
   * @return true if all tests return true, false otherwise
   * @author Michelle Jensen
   */
  public static boolean runAllTests() {
    boolean test1 = testNumOfPartitionsBase();
    System.out.println("testNumOfPartitionsBase(): " + (test1 ? "PASS" : "FAIL"));

    boolean test2 = testNumOfPartitionsRecursive();
    System.out.println("testNumOfPartitionsRecursive(): " + (test2 ? "PASS" : "FAIL"));

    boolean test3 = testCalcPartitionsBase();
    System.out.println("testUniquePartitionsBase(): " + (test3 ? "PASS" : "FAIL"));

    boolean test4 = testCalcPartitionsRecursive();
    System.out.println("testUniquePartitionsRecursive(): " + (test4 ? "PASS" : "FAIL"));

    boolean test5 = testCalculateAllPermutationsBase();
    System.out.println("testCalculateAllPermutationsBase(): " + (test5 ? "PASS" : "FAIL"));

    boolean test6 = testCalculateAllPermutationsRecursive();
    System.out.println("testCalculateAllPermutationsRecursive(): " + (test6 ? "PASS" : "FAIL"));

    boolean test7 = testNumOfPartitionsFuzz();
    System.out.println("testNumOfPartitionsFuzz(): " + (test7 ? "PASS" : "FAIL"));

    boolean test8 = testCalcPartitionsFuzz();
    System.out.println("testUniquePartitionsFuzz(): " + (test8 ? "PASS" : "FAIL"));

    return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8;
  }

  public static void main(String[] args) {
    System.out.println("runAllTest(): " + (runAllTests() ? "PASS" : "FAIL"));
  }

}
