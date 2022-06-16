package me.thomas.knowledge.algorithm.sort;

/**
 * 快速排序: 选择一个数，将小于它的放在左边，大于它的放在右边。继续对两边递归处理
 *
 * @author zhaoxinsheng
 * @date 2019/02/13 Wednesday
 */
public class QuickSort {

    public static void sort(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = array[low]; // 第一个用来做被比较数
        int i = low + 1,
                j = high;
        while (true) {
            // 这里不需要判断j >= low, 因为移到low时，array[low] == pivot
            while (array[j] > pivot) {
                j--;
            }
            // 先缩小j的值可以提高性能，因为i < j可以少比较一些元素
            while (array[i] < pivot && i < j) {
                i++;
            }
            if (i >= j) {
                break;
            }
            swap(array, i, j);
        }
        swap(array, low, j);
        sort(array, low, j - 1);
        sort(array, j + 1, high);
    }

    private static void swap(int[] array, int i, int j) {
        System.out.println("swap(" + array[i] + ", " + array[j] + ")");;
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        print(array);
    }

    private static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println("\n");
    }
}
