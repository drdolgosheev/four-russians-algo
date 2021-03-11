package com.company.domain;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class to read data, display arrays and etc.
 * @author Dmitriy Dolgosheev
 * @version 1.0
 **/

public class ArrUtils {

    // Reading data from file
    public static void ReadFileLineByLine(int[][] arr, String pathname, int size) {
        try {
            File file = new File(pathname);

            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            for (int i = 0; i < size; i++) {
                // Deleting char ','
                line = line.replaceAll(",", "");

                // Adding to array
                for (int j = 0; j < size; j++) {
                    arr[i][j] = Character.digit(line.charAt(j), 10);
                }

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get size for array
    public static int getLength(String pathname) throws IOException {
        File file = new File(pathname);

        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();

        // Deleting char ','
        line = line.replaceAll(",", "");

        return line.length();
    }

    // Printing array
    public static void printArr(int[][] arr) {
        for (int[] row : arr)
            System.out.println(Arrays.toString(row));
    }

    // Creating random matrix - required for tests
    public static void createRandomMatrix(int[][] A, int[][] B, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                A[i][j] = ThreadLocalRandom.current().nextInt(0, 2);
                B[i][j] = ThreadLocalRandom.current().nextInt(0, 2);
            }
        }
        System.out.println("Array A:");
        for (int[] row : A)
            System.out.println(Arrays.toString(row));

        System.out.println("\nArray B:");
        for (int[] row : B)
            System.out.println(Arrays.toString(row));
    }

    // Get arr's column
    public static int[] getColumn(int[][] arr, int index) {
        int size = arr.length;
        int[] column = new int[size];

        for (int i = 0; i < size; i++)
            column[i] = arr[i][index];

        return column;
    }

    // The call rowFromBottom(Bi,k+1) returns the k+1 row counting from the end of matrix Bi.
    public static int[] rowFromBottom(int[][] arr, int k) {
        return arr[arr.length - k - 1];
    }

    // Returns A V B
    public static int[] rowSum(int[] A, int[] B) {
        int size = A.length;
        int[] result = new int[size];

        for (int i = 0; i < size; i++) {
            if (A[i] + B[i] > 0)
                result[i] = 1;
            else
                result[i] = 0;
        }
        return result;
    }

    // The call num(Ai[j]) returns the decimal number that corresponds to the jth row of matrix Ai.
    // For example, if Ai[j]=[1,0,1], num(Ai[j]) returns 5
    public static int num(int[] row) {
        int num = 0;
        int size = row.length - 1;

        for (int i = 0; i <= size; i++)
            if (row[i] != 0)
                num += Math.pow(2, i);

        return num;
    }

    // Returns A V B
    public static int[][] arrSum(int[][] A, int[][] B, int N) {
        int[][] result = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result[i][j] = A[i][j] + B[i][j];

                if (result[i][j] > 0)
                    result[i][j] = 1;
            }
        }

        return result;
    }

    // Default matrix multiplication - required for tests
    // Resource: https://stackoverflow.com/questions/17623876/matrix-multiplication-using-arrays
    public static int[][] MatrixMultiplicationDefault(int[][] A, int[][] B) {

        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        int[][] C = new int[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                C[i][j] = 0;
            }
        }

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                for (int k = 0; k < aColumns; k++) {
                    C[i][j] += A[i][k] * B[k][j];

                    if (C[i][j] > 1)
                        C[i][j] = 1;
                }
            }
        }

        return C;
    }
}
