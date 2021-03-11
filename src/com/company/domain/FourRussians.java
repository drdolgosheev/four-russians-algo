package com.company.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements algorithm of FourRussians
 * Used literature: https://louridas.github.io/rwa/assignments/four-russians/
 * Complexity Ο(n3/lgn)
 * @author Dmitriy Dolgosheev
 * @version 1.0
 **/

public class FourRussians {

    private final List<int[][]> fragmentsOfA = new ArrayList<>();
    private final List<int[][]> fragmentsOfB = new ArrayList<>();

    // Matrix multiplying
    public int[][] MatrixMultiplication(int[][] A, int[][] B, int N) {
        int M = (int) (Math.log(N) / Math.log(2));
        int[][] C = new int[N][N];

        // For matrix 1x1
        if(N == 1) {
            int[][] C1 = new int[1][1];
            C1[0][0] = A[0][0] * B[0][0];
            return C1;
        }

        // Zero initialize C
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                C[i][j] = 0;

        // Preparation before FR multiplication
        preparation(A, B, M, N);

        // In each iteration of the loop of cycle we calculate a product Ci=AiBi and we add it in C
        for (int i = 0; i < N / M; i++) {
            // Calculating all possible sums of rows of Bi and store them in matrix RS (rowsums), with dimensions 2m×n
            int[][] RS = new int[(int) Math.pow(2, M)][N];

            // Zero initialize RS[0]
            for (int j = 0; j < N; j++)
                RS[0][j] = 0;

            int bp = 1;
            int k = 0;

            // Adding rows from Bi to RS[j−2k], which is a sum that has already been calculated, so that we get new sums.
            for (int j = 1; j < Math.pow(2, M); j++) {
                // The call rowFromBottom(Bi,k+1) returns the k+1 row counting from the end of matrix Bi.
                // RS[j] = RS[j - 2^k] V rowFromBottom(Bi,k+1)
                RS[j] = ArrUtils.rowSum(RS[j - (int) Math.pow(2, k)], ArrUtils.rowFromBottom(fragmentsOfB.get(i), k));

                if (bp == 1) {
                    bp = j + 1;
                    k = k + 1;
                } else {
                    bp = bp - 1;
                }
            }
            // Creating C fragment NxN
            int[][] Ci = new int[N][N];

            // The call num(Ai[j]) returns the decimal number that corresponds to the jth row of matrix Ai.
            // For example, if Ai[j]=[1,0,1], num(Ai[j]) returns 5
            for (int j = 0; j < N; j++)
                Ci[j] = RS[ArrUtils.num(fragmentsOfA.get(i)[j])];

            // C = C V Ci
            C = ArrUtils.arrSum(C, Ci, N);
        }

        return C;
    }

    // Dividing matrices by number of parts = N/M
    private void preparation(int[][] A, int[][] B, int M, int N) {
        for (int i = 0; i < N / M; i++) {
            addFragmentToA(A, M, i * M, N);
            addFragmentToB(B, M, i * M, N);
        }
    }

    // Adding other part of matrix B to the list
    private void addFragmentToB(int[][] B, int M, int startIndex, int N) {
        int[][] fragment = new int[M][N];

        for (int row = 0; row < M; row++)
            fragment[row] = B[startIndex + row];

        fragmentsOfB.add(fragment);
    }

    // Adding another part of matrix B to the list
    private void addFragmentToA(int[][] A, int M, int startIndex, int N) {
        int[][] fragment = new int[N][M];

        for (int column = 0; column < M; column++) {
            int[] tmpColumn = ArrUtils.getColumn(A, startIndex + column);

            for (int row = 0; row < N; row++)
                fragment[row][column] = tmpColumn[row];
        }

        fragmentsOfA.add(fragment);
    }
}
