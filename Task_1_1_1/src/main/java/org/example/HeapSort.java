package org.example;

import java.util.Arrays;

public class HeapSort {
    /**
     * Сортирует массив при помощью алгоритма пирамидальной сортировки
     *
     * @param array массив целых чисел для сортировки
     */
    public static void heapSort(int[] array) {
        int len = array.length;

        // строим кучу на исходном массиве с поддержанием max-элемента
        for (int i = len / 2 - 1; i >= 0; i--) {
            siftDown(array, len, i);
        }

        // поочерёдно извлекаем элементы из кучи
        for (int j = len - 1; j >= 0; j--) {
            // перемещаем при каждой операции корень в текущий конец массива
            int temp = array[0];
            array[0] = array[j];
            array[j] = temp;

            // повторяем процедуру построения max-кучи на массиве с меньшей длиной
            siftDown(array, j, 0);
        }
    }

    /**
     * Просеивает элемент вниз по куче, чтобы восстановить свойство max-кучи
     *
     * @param array исходный массив
     * @param len   текущий размер кучи
     * @param idx   индекс элемента, который нужно просеять вниз
     */
    public static void siftDown(int[] array, int len, int idx) {
        // вычисление индекса дочерних элементов текущего array[idx]
        int leftChild = 2 * idx + 1;
        int rightChild = 2 * idx + 2;

        // индекс текущего наибольшего элемента
        int newIdx = idx;

        // сравниваем текущий элемент с его детьми (если кто-то из детей больше - меняем индекс)
        if (leftChild < len && array[leftChild] > array[newIdx]) {
            newIdx = leftChild;
        }

        if (rightChild < len && array[rightChild] > array[newIdx]) {
            newIdx = rightChild;
        }

        // если один из дочерних элементов больше - меняем с текущим
        if (newIdx != idx) {
            int temp = array[newIdx];
            array[newIdx] = array[idx];
            array[idx] = temp;

            // вызываем рекурсию для newIdx (просеиваем его вниз по куче)
            siftDown(array, len, newIdx);
        }

    }
}

