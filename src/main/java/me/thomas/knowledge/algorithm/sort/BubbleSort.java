package me.thomas.knowledge.algorithm.sort;

/**
 * 冒泡排序：比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
 * 针对所有的元素重复以上的步骤，除了最后一个。
 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 *
 * Created by Thomas on 2016/1/11.
 */
public class BubbleSort {

    public static int[] sort(int[] array) {
        // 外循环有几个元素就循环几次
        for (int i = 0; i < array.length; i++) {
            // 内循环每次都减少一次循环
            for (int j = 0; j < array.length - i - 1; j++) {
                // 比较相邻的两个数，如果前者大于后者，则交换位置。
                if (array[j] > array[j + 1]) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
        }

        return array;
    }

}
