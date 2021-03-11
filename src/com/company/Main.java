package com.company;

import com.company.domain.FourRussians;
import com.company.domain.ArrUtils;

import java.io.IOException;

/**
 * Main application class
 * @author Dmitriy Dolgosheev
 * @version 1.0
 **/

public class Main {
    public static void main(String[] args) throws IOException {
        FourRussians fourRussians = new FourRussians();

        String pathnameA = "src/com/company/input/array_1.txt";
        String pathnameB = "src/com/company/input/array_2.txt";

        // As far as arrays have same size
        int size = ArrUtils.getLength(pathnameA);
        System.out.println("Size: " + size + "x" + size);

        // Creating arrays
        int[][] A = new int[size][size];
        int[][] B = new int[size][size];

        // Getting data for A array and printing
        ArrUtils.ReadFileLineByLine(A, pathnameA, size);
        System.out.println("\nArray A:");
        ArrUtils.printArr(A);

        // Getting data for B array and printing
        ArrUtils.ReadFileLineByLine(B, pathnameB, size);
        System.out.println("\nArray B:");
        ArrUtils.printArr(B);

        // Counting and printing the result of Four Russians algo
        int[][] C = fourRussians.MatrixMultiplication(A, B, size);
        System.out.println("\nArray C:");
        ArrUtils.printArr(C);
    }
}
