package me.thomas.knowledge.algorithm.leetcode.backtrack;

import java.util.Arrays;

/**
 * 698. 划分为k个相等的子集
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution698.java, v 0.1 2022/7/4 20:26 xinsheng2.zhao Exp $
 */
public class Solution698 {

    /**
     * 从数组中任意取N个数凑成leftValue。
     * 思路：nums已从小到达排序，我们从后向前取数组中的数组，看能否凑成整数leftValue。
     * 不能从小到大凑整的原因是：前面N个小数组成leftValue后，后面剩余的数太大导致不能再凑成leftValue了。
     * 从大到小凑数能尽可能大数和小数1比1搭配。
     *
     * @param nums      数组
     * @param start     从start查找前面的数 -- Arrays.sort(nums)逆序太麻烦，所以这里从后向前查找。
     * @param leftValue 剩余的数
     * @return true 能组成 false不能组成
     */
    boolean backtrack(int[] nums, int start, boolean[] selected, int leftValue) {
        if (leftValue == 0) {
            return true;
        }
        for (int i = start; i >= 0; i--) {
            // start位置的数没有选过并且小于leftValue时可以选择
            if (selected[i] || nums[i] > leftValue) {
                continue;
            }
            selected[i] = true;
            if (backtrack(nums, i - 1, selected, leftValue - nums[i])) {
                return true;
            }
            selected[i] = false;
        }
        return false;
    }

    /**
     * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
     * 输出： True
     * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k == 1) {
            return true;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k > 0) {
            return false;
        }

        int avg = sum / k;
        // 从小到大排序
        Arrays.sort(nums);
        // 如果最大的数已经超过的平均数，则无法平分
        if (nums[nums.length - 1] > avg) {
            return false;
        }

        boolean[] selected = new boolean[nums.length];
        // 拼凑出k次
        for (; k > 0; k--) {
            // 剩余的数不能凑出avg则失败
            if (!backtrack(nums, nums.length - 1, selected, avg)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution698 solution = new Solution698();
        System.out.println(solution.canPartitionKSubsets(new int[] { 4, 5, 9, 3, 10, 2, 10, 7, 10, 8, 5, 9, 4, 6, 4, 9 }, 5));
    }
}
