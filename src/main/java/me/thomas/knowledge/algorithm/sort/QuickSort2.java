package me.thomas.knowledge.algorithm.sort;

/**
 * @author zhaoxinsheng
 * @date 2020/08/16 Sunday
 */
public class QuickSort2 {

    public static void sort(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = array[low],
                i = low + 1,
                j = high;
        while (true) {
            while (array[j] > pivot) {
                j--;
            }
            while (i < j && array[i] < pivot) {
                i++;
            }
            if (i < j) {
                swap(array, i, j);
            } else break;
        }
        swap(array, low, j);
        sort(array, low, j - 1);
        sort(array, j + 1, high);
    }

    public static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
}
