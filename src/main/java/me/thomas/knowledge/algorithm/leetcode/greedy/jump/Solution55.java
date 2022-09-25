package me.thomas.knowledge.algorithm.leetcode.greedy.jump;

/**
 * 55. 跳跃游戏
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个下标。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution55.java, v 0.1 2022/9/25 15:18 xinsheng2.zhao Exp $
 */
public class Solution55 {

    /**
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        return canJump_dong(nums);
    }

    /**
     * 思路：模拟如何跳跃 -- 记录当前所在的位置，记录他可以到达的位置的能跳跃到的最远的距离，并跳跃到这个位置上。
     */
    boolean canJump_mine(int[] nums) {
        int prev = 0, next = 0; /* 上一步在哪里，下一步又在哪里 */
        int n = nums.length;
        while (prev < n - 1) { /* 一直跳，直到倒数第二个台阶 */
            int far = prev;
            for (int i = 1; i <= nums[prev] && prev + i < n; i++) {
                if (prev + i + nums[prev + i] > far) {
                    far = prev + i + nums[prev + i];
                    next = prev + i;
                }
            }
            if (prev == next) { /* 如果保持不变，则无法走出 */
                return false;
            }
            prev = next;
        }
        return true;
    }

    /**
     * 思路：使用局部的最优解更新全局的最优解。
     */
    boolean canJump_dong(int[] nums) {
        int mostFar = 0;
        for (int i = 0; i < nums.length - 1; i++) { /* 在[0, nums.length - 1)之间都需要跳跃 */
            mostFar = Math.max(mostFar, i + nums[i]);
            if (mostFar <= i) {
                break;
            }
        }
        return mostFar >= nums.length - 1;
    }

    public static void main(String[] args) {
        Solution55 solution = new Solution55();
        System.out.println(solution.canJump(new int[] { 5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0 }));
    }
}
