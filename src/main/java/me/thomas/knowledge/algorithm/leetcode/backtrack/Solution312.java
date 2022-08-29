package me.thomas.knowledge.algorithm.leetcode.backtrack;

/**
 * 312. 戳气球
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * <p>
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 * <p>
 * 求所能获得硬币的最大数量。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution312.java, v 0.1 2022/8/29 20:43 xinsheng2.zhao Exp $
 */
public class Solution312 {

    int maxCoins = 0;

    /**
     * 输入：nums = [3,1,5,8]
     * 输出：167
     * 解释：
     * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
     * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
     * <p>
     * 思路：暴力回溯法，但是只能处理规模不超过10的数组。
     */
    public int maxCoins(int[] nums) {
        boolean[] selected = new boolean[nums.length];
        backtrack(nums, selected, 0, 0);
        return maxCoins;
    }

    void backtrack(int[] nums, boolean[] selected, int start, int coins) {
        if (start == nums.length) {
            maxCoins = Math.max(maxCoins, coins);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (selected[i]) {
                continue;
            }
            selected[i] = true;
            backtrack(nums, selected, start + 1, coins + getFromNums(nums, left(selected, i)) * nums[i] * getFromNums(nums, right(selected, i)));
            selected[i] = false;
        }
    }

    /**
     * 返回i的左边第一个为false的下标
     */
    int left(boolean[] selected, int i) {
        do {
            i--;
        } while (i >= 0 && selected[i]);
        return i;
    }

    /**
     * 返回i的左边第一个为false的下标
     */
    int right(boolean[] selected, int i) {
        do {
            i++;
        } while (i < selected.length && selected[i]);
        return i;
    }

    int getFromNums(int[] nums, int i) {
        return i >= 0 && i < nums.length ? nums[i] : 1;
    }

    public static void main(String[] args) {
        Solution312 solution = new Solution312();
        System.out.println(solution.maxCoins(new int[] { 38, 19, 51, 32, 87, 47, 26, 55, 63, 12, 81 })); // output: 1797097
    }
}
