package com.epam.effectiveJava.search;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import lombok.extern.java.Log;

import java.util.Arrays;

@Log
@ExtendWith(MockitoExtension.class)
class BinarySearchAlgorithmsTest {

    @Spy
    BinarySearchAlgorithms sut;

    @Test
    void searchRecursively() {
        int[] array = generateRandomArray(1_000_000, true);
        long startTime = System.currentTimeMillis();
        sut.searchRecursively(array, 2948, 0, array.length - 1);
        LOG.info("Recursive binary search worked - " + (System.currentTimeMillis() - startTime));
    }

    @Test
    void searchIteratively() {
        int[] array = generateRandomArray(1_000_000, true);
        long startTime = System.currentTimeMillis();
        sut.searchIteratively(array, 2948);
        LOG.info("Iterative binary search worked - " + (System.currentTimeMillis() - startTime));
    }

    private static int[] generateRandomArray(int length, boolean isSorted) {
        int[] array = new int[length];

        for (int i = 0; i < length - 1; i++) {
            array[i] = (int) Math.round(Math.random() * 10000);
        }

        if (isSorted) {
            Arrays.sort(array);
        }

        return array;
    }
}