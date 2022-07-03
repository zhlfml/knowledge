package me.thomas.knowledge.algorithm.leetcode.design.monotonicqueue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个整数数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回 滑动窗口中的最大值 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution239.java, v 0.1 2022/7/3 11:42 xinsheng2.zhao Exp $
 */
public class Solution239 {

    /**
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] answer = new int[nums.length - k + 1];
        Deque<Integer> list = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            // 只有后面一个数比前一个绝对大时，才能碾压。
            while (!list.isEmpty() && list.peekLast() < nums[i]) {
                list.pollLast();
            }
            list.offerLast(nums[i]);
            if (i >= k - 1) {
                if (i >= k && list.peekFirst() == nums[i - k]) {
                    list.pollFirst();
                }
                answer[i - k + 1] = list.peekFirst();
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution239 solution = new Solution239();
        System.out.println(Arrays.toString(solution.maxSlidingWindow(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3)));
    }

}
