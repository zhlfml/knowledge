package me.thomas.knowledge.algorithm.leetcode.backtrack;

/**
 * 494. 目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 * <p>
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * <p>
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution494.java, v 0.1 2022/8/11 23:20 xinsheng2.zhao Exp $
 */
public class Solution494 {

    int ways = 0;

    int[] ops = new int[] { 1, -1 };

    /**
     * 输入：nums = [1,1,1,1,1], target = 3
     * 输出：5
     * 解释：一共有 5 种方法让最终目标和为 3 。
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     */
    public int findTargetSumWays(int[] nums, int target) {
        backtrack(nums, 0, target);
        return ways;
    }

    void backtrack(int[] nums, int start, int target) {
        if (start == nums.length) {
            if (target == 0) {
                ways++;
            }
            return;
        }
        // 只有两种选择
        for (int op : ops) {
            backtrack(nums, start + 1, target + nums[start] * op);
        }
    }

    public static void main(String[] args) {
        Solution494 solution = new Solution494();
        System.out.println(solution.findTargetSumWays(new int[] { 1, 1, 1, 1, 1 }, 3));
    }
}
