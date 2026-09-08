package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {

    @Test
    void heapSortOnReverseArray() {
        int[] input = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};

        HeapSort.heapSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    void heapSortOnSortedArray() {
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};

        HeapSort.heapSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    void heapSortOnEmptyArray() {
        int[] input = {};
        int[] expected = {};

        HeapSort.heapSort(input);
        assertArrayEquals(expected, input);
    }
}