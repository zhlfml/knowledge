package me.thomas.knowledge.algorithm.sort;

/**
 * Created by thomas on 6/10/14.
 */
public class MergeSort {

    /**
     * 归并排序
     *
     * @param a 无序数组
     * @return
     */
    public int[] mergeSort(int a[]) {
        if (a.length == 1) {
            return a;
        }

        int half = a.length / 2;
        int[] a1 = new int[half];
        int[] a2 = new int[a.length - half];
        System.arraycopy(a, 0, a1, 0, a1.length);
        System.arraycopy(a, half, a2, 0, a2.length);
        a1 = mergeSort(a1);
        a2 = mergeSort(a2);

        return mergeArray(a1, a2);
    }

    /**
     * 合并两个有序数组
     *
     * @param a 有序数组
     * @param b 有序数组
     */
    public int[] mergeArray(int a[], int b[]) {
        int[] result = new int[a.length + b.length];

        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }
        while (i < a.length) {
            result[k++] = a[i++];
        }
        while (j < b.length) {
            result[k++] = b[j++];
        }

        return result;
    }
}
