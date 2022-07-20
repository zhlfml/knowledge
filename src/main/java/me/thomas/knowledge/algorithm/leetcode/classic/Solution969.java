package me.thomas.knowledge.algorithm.leetcode.classic;

import java.util.ArrayList;
import java.util.List;

/**
 * 969. 煎饼排序
 * 给你一个整数数组 arr ，请使用 煎饼翻转 完成对数组的排序。
 * <p>
 * 一次煎饼翻转的执行过程如下：
 * <p>
 * 选择一个整数 k ，1 <= k <= arr.length
 * 反转子数组 arr[0...k-1]（下标从 0 开始）
 * 例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。
 * <p>
 * 以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在 10 * arr.length 范围内的有效答案都将被判断为正确。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution969.java, v 0.1 2022/7/19 21:56 xinsheng2.zhao Exp $
 */
public class Solution969 {

    /**
     * 输入：[3,2,4,1]
     * 输出：[4,2,4,3]
     * 解释：
     * 我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
     * 初始状态 arr = [3, 2, 4, 1]
     * 第一次翻转后（k = 4）：arr = [1, 4, 2, 3]
     * 第二次翻转后（k = 2）：arr = [4, 1, 2, 3]
     * 第三次翻转后（k = 4）：arr = [3, 2, 1, 4]
     * 第四次翻转后（k = 3）：arr = [1, 2, 3, 4]，此时已完成排序。
     */
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> answer = new ArrayList<>();
        // 每轮查找最大值的遍历长度
        for (int i = arr.length - 1; i > 0; i--) {
            int index = 0; /* 最大值所在的下标 */
            int maxValue = arr[0];
            for (int j = 1; j <= i; j++) {
                if (maxValue < arr[j]) {
                    index = j;
                    maxValue = arr[j];
                }
            }
            // 如果最大值不再最后一个位置，则需要翻转
            if (index < i) {
                reverse(arr, 0, index);
                answer.add(index + 1);
                reverse(arr, 0, i);
                answer.add(i + 1);
            }
        }
        return answer;
    }

    /**
     * 翻转数组
     *
     * @param arr   数组
     * @param start 起点 包含
     * @param end   终点 包含
     */
    void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        Solution969 solution = new Solution969();
        System.out.println(solution.pancakeSort(new int[] { 3, 2, 4, 1 }));
    }
}
