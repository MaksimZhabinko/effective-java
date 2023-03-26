package com.epam.effectiveJava.sort;

public class SortAlgorithms {
    public static final int INSERTION_SORT_THRESHOLD = 47;

    public int[] sort(int[] array) {
        if (array.length > INSERTION_SORT_THRESHOLD) {
            return mergeSort(array);
        }
        insertionSort(array);
        return array;
    }

    public int[] mergeSort(int[] array) {
        int[] currentSrc = array;
        int[] currentDest = new int[array.length];

        int size = 1;
        while (size < array.length) {
            for (int i = 0; i < array.length; i += size * 2) {
                merge(currentSrc, i, currentSrc, i + size, currentDest, i, size);
            }

            int[] temp = currentSrc;
            currentSrc = currentDest;
            currentDest = temp;

            size = size * 2;
        }

        return currentSrc;
    }

    public void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i;

            while (j > 0 && array[j - 1] > current) {
                array[j] = array[j - 1];
                j--;
            }

            array[j] = current;
        }
    }

    private void merge(int[] firstSource, int firstSourceStart,
                       int[] secondSource, int secondSourceStart,
                       int[] destination, int destinationStart, int size) {

        int firstIndex = firstSourceStart;
        int secondIndex = secondSourceStart;

        int firstSourceEnd = Math.min(firstSourceStart + size, firstSource.length);
        int secondSourceEnd = Math.min(secondSourceStart + size, secondSource.length);

        int iterationCount = firstSourceEnd - firstSourceStart + secondSourceEnd - secondSourceStart;

        for (int i = destinationStart; i < destinationStart + iterationCount; i++) {
            if (firstIndex < firstSourceEnd &&
                    (secondIndex >= secondSourceEnd || firstSource[firstIndex] < secondSource[secondIndex])) {
                destination[i] = firstSource[firstIndex];
                firstIndex++;
            } else {
                destination[i] = secondSource[secondIndex];
                secondIndex++;
            }
        }

    }
}
