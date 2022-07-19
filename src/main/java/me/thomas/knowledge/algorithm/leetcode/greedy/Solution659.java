package me.thomas.knowledge.algorithm.leetcode.greedy;

/**
 * 659. 分割数组为连续子序列
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度至少为 3 的子序列，其中每个子序列都由连续整数组成。
 * <p>
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution659.java, v 0.1 2022/7/19 17:34 xinsheng2.zhao Exp $
 */
public class Solution659 {

    /**
     * 输入: [1,2,3,3,4,4,5,5]
     * 输出: True
     * 解释:
     * 你可以分割出这样两个连续子序列 :
     * 1, 2, 3, 4, 5
     * 3, 4, 5
     */
    public boolean isPossible(int[] nums) {
        int[] stats = new int[10001];
        int[] needs = new int[10001];
        // 数组内数字并非从1开始，将数字映射到x坐标上
        int base = nums[0];
        for (int num : nums) {
            stats[num - base]++;
        }

        for (int i = 0; i < stats.length; ) {
            if (stats[i] == 0) {
                i++;
                continue;
            }
            // 先检查能否续上
            if (needs[i] > 0) {
                stats[i]--;
                needs[i]--;
                needs[i + 1]++;
                continue;
            }
            // 贪心：先找到三个满足基本要求的连续数字
            int j = 0;
            for (; j < 3; j++) {
                if (i + j == stats.length || stats[i + j] <= 0) {
                    return false;
                }
                stats[i + j]--;
            }
            needs[i + j]++;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution659 solution = new Solution659();
        System.out.println(solution.isPossible(new int[] { 1, 2, 3, 4, 4, 5, 6, 7, 8 }));
    }
}
