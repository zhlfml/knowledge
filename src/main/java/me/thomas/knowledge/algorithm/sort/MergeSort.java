package me.thomas.knowledge.algorithm.sort;

/**
 * 归并排序
 *
 * Created by thomas on 6/10/14.
 */
public class MergeSort {

    /**
     * 先将一个长的无序数组分成两个短的无序数组，
     * 然后递归调用mergeSort方法将两个短的无序数组排成有序的驻足，
     * 最后调用mergeArray方法将两个短的有序的数组合并成一个长的有序数组。
     *
     * @param a 无序数组
     * @return  有序数组
     */
    public static int[] sort(int a[]) {
        if (a.length == 1) {
            return a;
        }

        int half = a.length / 2;
        int[] a1 = new int[half];
        int[] a2 = new int[a.length - half];
        System.arraycopy(a, 0, a1, 0, a1.length);
        System.arraycopy(a, half, a2, 0, a2.length);
        // 递归调用mergeSort方法，将a1，a2分别排序。
        a1 = sort(a1);
        a2 = sort(a2);

        return mergeArray(a1, a2);
    }

    /**
     * 合并两个有序数组
     *
     * @param a 有序数组
     * @param b 有序数组
     * @return  合并a,b所有元素后的有序数组
     */
    public static int[] mergeArray(int a[], int b[]) {
        int[] result = new int[a.length + b.length];

        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }
        if (i < a.length) {
            System.arraycopy(a, i, result, k, a.length - i);
        }
        if (j < b.length) {
            System.arraycopy(b, j, result, k, b.length - j);
        }

        return result;
    }

}
