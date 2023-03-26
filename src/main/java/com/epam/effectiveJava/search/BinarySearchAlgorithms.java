package com.epam.effectiveJava.search;

import com.epam.effectiveJava.sort.SortAlgorithms;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BinarySearchAlgorithms {

    private final SortAlgorithms sortAlgorithms;

    public int search(int[] array, int requiredElement, boolean sortRequired, boolean isRecursiveStrategy) {
        int[] searchArray = sortRequired
                ? sortAlgorithms.sort(array)
                : array;

        return isRecursiveStrategy
                ? searchRecursively(searchArray, requiredElement, 0, array.length - 1)
                : searchIteratively(searchArray, requiredElement);
    }

    public int searchRecursively(int[] array, int requiredElement, int startIndex, int endIndex) {
        if (endIndex >= startIndex) {
            int middleIndex = startIndex + (endIndex - startIndex) / 2;
            int middleElement = array[middleIndex];

            if (middleElement == requiredElement) {
                return middleIndex;
            }

            if (middleElement > requiredElement) {
                return searchRecursively(array, requiredElement, startIndex, middleIndex - 1);
            } else {
                return searchRecursively(array, requiredElement, middleIndex + 1, endIndex);
            }
        }

        return -1;
    }

    public int searchIteratively(int[] array, int requiredElement) {
        int startIndex = 0;
        int endIndex = array.length - 1;

        while (startIndex <= endIndex) {
            int middleIndex = startIndex + (endIndex - startIndex) / 2;

            if (array[middleIndex] == requiredElement) {
                return middleIndex;
            }

            if (array[middleIndex] > requiredElement) {
                endIndex = middleIndex - 1;
            } else {
                startIndex = middleIndex + 1;
            }
        }
        return -1;
    }
}
