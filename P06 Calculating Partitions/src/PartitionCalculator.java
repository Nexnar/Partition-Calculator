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

/**
 * A class that performs operations on Partitions
 *
 */
public class PartitionCalculator {

  /**
   * Recursive method that calculates the number of Partitions for a given N (order does not matter)
   * 
   * @param N the desired N to calculate # of partitions for
   * @return the number of partitions
   */
  public static int numOfPartitions(int N) {
    Partition partition = new Partition(N);
    partition.orderMatters = false;

    return helper(N, partition, N, 0);
  }

  /**
   * Recursive helper method that recurses and counts the total number of partitions. We can use
   * recursion and anchor ourselves to LARGEST number in the partition, which will be changed in
   * order to achieve every possible partition. We know that these partitions will be UNIQUE since
   * the largest number will be kept on the left-side of the partition list.
   * 
   * @param N              the desired N to calculate # of partitions for
   * @param partition      the current partition being made
   * @param largestNum     the largest number in the partition
   * @param partitionTotal a total that is carried through ALL recursions and used as a return value
   * @return the number of partitions (unique)
   */
  private static int helper(int N, Partition partition, int largestNum, int partitionTotal) {

    // base case: if the largest number is 0, then we've successfully went from N all
    // the way to 1 in our recursions, and can stop the current call
    if (largestNum == 0) {
      return partitionTotal;
    }
    // Once a partition reaches the value N, it is a VALID partition and is added to the partition
    // count
    if (partition.getSum() == N) {
      partitionTotal++;
      return partitionTotal; // stops the current call and returns to the previous call on the stack
    } else { // recursive case
      Partition partitionCopy = partition.copyOf();
      // at every step m we are at in the partition, this cycles m from current largest to 1
      partitionTotal = helper(N, partitionCopy, largestNum - 1, partitionTotal);
      if (N - partition.getSum() >= largestNum) {
        partition.addNumber(largestNum);
        partitionCopy = partition.copyOf();
        partitionTotal = helper(N, partitionCopy, largestNum, partitionTotal); // this begins the
                                                                               // NEXT step, m + 1
      }
    }

    return partitionTotal;
  }

  /**
   * Method that calculates the actual Partitions for a given N (order does not matter)
   * 
   * @param N the desired N to calculate partitions for
   * @return a Partition ArrayList of all of the partitions
   */
  public static ArrayList<Partition> calculatePartitions(int N) {
    Partition partition = new Partition(N);
    partition.orderMatters = false;
    ArrayList<Partition> allPartitions = new ArrayList<>();
    return helper2(N, partition, N, allPartitions);
  }

  /**
   * Another recursive helper method that recurses and keeps track of the unique partitions. Works
   * in the same way as the other helper method, except partitionTotal is replaced with an
   * allPartitions ArrayList, which stores all of the partitions
   * 
   * @param N             the desired N to calculate partitions for
   * @param partition     the current partition being made
   * @param largestNum    the largest number in the partition
   * @param allPartitions an ArrayList that is carried through ALL recursions and used as a return
   *                      value
   * @return an ArrayList of all of the partitions (unique)
   */
  private static ArrayList<Partition> helper2(int N, Partition partition, int largestNum,
      ArrayList<Partition> allPartitions) {

    if (largestNum == 0) {
      return allPartitions;
    }
    if (partition.getSum() == N) {
      allPartitions.add(partition);
      return allPartitions;
    } else { // recursive case
      Partition partitionCopy = partition.copyOf();
      allPartitions = helper2(N, partitionCopy, largestNum - 1, allPartitions);
      if (N - partition.getSum() >= largestNum) {
        partition.addNumber(largestNum);
        partitionCopy = partition.copyOf();
        allPartitions = helper2(N, partitionCopy, largestNum, allPartitions);
      }
    }

    return allPartitions;
  }

  /**
   * Method that calculates all of the permutations for valid partitions of a given N
   * 
   * @param partitions an ArrayList containing all valid partitions for a given N
   * @return an ArrayList of all valid permutations
   */
  public static ArrayList<Partition> calculateAllPermutations(ArrayList<Partition> partitions) {
    ArrayList<Partition> allPermutations = new ArrayList<Partition>();
    for (Partition p : partitions) {
      permutePartition(allPermutations, new Partition(p.getN()), p, p.length());
    }
    return allPermutations;
  }

  /**
   * Recursive helper method that permutes all of the valid partitions
   * 
   * @param allPermutations an ArrayList which stores all of the valid permutations through ALL
   *                        recursions
   * @param fixed           a Partition which stores the current partition being created
   * @param partition       the partition to permute
   * @param partitionLength a constant length that is used to reference for the base case
   */
  private static void permutePartition(ArrayList<Partition> allPermutations, Partition fixed,
      Partition partition, int partitionLength) {
    // base case: if the length of the current Partition being built is the proper length,
    // and it isn't the exact same as another permutation, then add it to allPermutations
    // and go back one call on the stack.
    if (fixed.length() == partitionLength) {
      for (Partition p : allPermutations) {
        fixed.orderMatters = true;
        if (fixed.equals(p)) {
          return;
        }
      }
      allPermutations.add(fixed);
    } else {
      // otherwise, create a copy of the current partition and fixed partition (to protect
      // against changes made by other calls on the stack when we want to go back) and
      // iterate through the possibilities and add it to fixed; each possibility creates
      // a new call on the stack.
      for (int i = 0; i < partition.length(); i++) {
        Partition copyPartition = partition.copyOf();
        Partition copyFixed = fixed.copyOf();
        copyFixed.addNumber(copyPartition.getNumAt(i));
        copyPartition.removeNumber(partition.getNumAt(i));
        // recursive call
        permutePartition(allPermutations, copyFixed, copyPartition, partitionLength);
      }
    }
  }



}
