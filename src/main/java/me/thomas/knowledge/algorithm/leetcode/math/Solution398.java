package me.thomas.knowledge.algorithm.leetcode.math;

import java.util.Random;

/**
 * 给你一个可能含有 重复元素 的整数数组nums ，请你随机输出给定的目标数字target 的索引。你可以假设给定的数字一定存在于数组中。
 * <p>
 * 实现 Solution 类：
 * <p>
 * Solution(int[] nums) 用数组 nums 初始化对象。
 * int pick(int target) 从 nums 中选出一个满足 nums[i] == target 的随机索引 i 。如果存在多个有效的索引，则每个索引的返回概率应当相等。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution398.java, v 0.1 2022/6/29 23:36 xinsheng2.zhao Exp $
 */
public class Solution398 {

    private final int[]  nums;
    private final Random random;

    public Solution398(int[] nums) {
        this.nums = nums;
        this.random = new Random();
    }

    public int pick(int target) {
        int answer = -1;
        int times = 0; // target出现的次数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                times++;
                if (random.nextInt(times) == 0) {
                    answer = i;
                }
            }
        }
        return answer;
    }
}
