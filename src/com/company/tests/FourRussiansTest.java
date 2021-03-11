package com.company.tests;

import com.company.domain.FourRussians;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.company.domain.ArrUtils;

import java.util.Arrays;

class FourRussiansTest {

    private final FourRussians fourRussians = new FourRussians();

    @Test
    void matrixMultiplicationSize1() {
        int size = 1;
        assertFourRussiansAndDefault(size);
    }

    @Test
    void matrixMultiplicationSize2() {
        int size = 2;
        assertFourRussiansAndDefault(size);
    }

    @Test
    void matrixMultiplicationSize3() {
        int size = 3;
        assertFourRussiansAndDefault(size);
    }

    @Test
    void matrixMultiplicationSize99() {
        int size = 99;
        assertFourRussiansAndDefault(size);
    }

    private void assertFourRussiansAndDefault(int size) {
        int[][] A = new int[size][size];
        int[][] B = new int[size][size];

        ArrUtils.createRandomMatrix(A, B, size);

        int[][] resultFourRussians = fourRussians.MatrixMultiplication(A, B, size);
        int[][] resultMultiplicationDefault = ArrUtils.MatrixMultiplicationDefault(A, B);

        System.out.println("\nResult four russians algo:");
        ArrUtils.printArr(resultFourRussians);

        System.out.println("\nResult default multiplication:");
        ArrUtils.printArr(resultMultiplicationDefault);

        // deepEquals for iterating all numbers
        Assertions.assertTrue(Arrays.deepEquals(resultFourRussians, resultMultiplicationDefault));
        System.out.println("\nOK");
    }
}