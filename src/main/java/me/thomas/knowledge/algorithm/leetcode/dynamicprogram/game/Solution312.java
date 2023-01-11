package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.game;

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

    /**
     * 输入：nums = [3,1,5,8]
     * 输出：167
     * 解释：
     * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
     * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
     * <p>
     * 思路：为什么它比较难呢？
     * 原因在于，这个问题中我们每戳破一个气球nums[i]，得到的分数和该气球相邻的气球nums[i-1]和nums[i+1]是有相关性的。
     * 所以对于这个戳气球问题，如果想用动态规划，必须巧妙地定义dp数组的含义，避免子问题产生相关性，才能推出合理的状态转移方程。
     * 那么我们可以改变问题：在一排气球points中，请你戳破气球0和气球n+1之间的所有气球（不包括0和n+1），使得最终只剩下气球0和气球n+1两个气球，最多能够得到多少分？
     * 现在可以定义dp数组的含义：
     * dp[i][j] = x表示，戳破气球i和气球j之间（开区间，不包括i和j）的所有气球，可以获得的最高分数为x。
     * 那么根据这个定义，题目要求的结果就是dp[0][n+1]的值，而 base case 就是dp[i][j] = 0，其中0 <= i <= n+1, j <= i+1，因为这种情况下，开区间(i, j)中间根本没有气球可以戳。
     * <pre>
     * // base case 已经都被初始化为 0
     * * int[][] dp = new int[n + 2][n + 2];
     * </pre>
     * 现在我们要根据这个dp数组来推导状态转移方程了，根据我们前文的套路，所谓的推导「状态转移方程」，实际上就是在思考怎么「做选择」，也就是这道题目最有技巧的部分：
     * 不就是想求戳破气球i和气球j之间的最高分数吗，如果「正向思考」，就只能写出前文的回溯算法；我们需要「反向思考」，想一想气球i和气球j之间最后一个被戳破的气球可能是哪一个？
     * 其实气球i和气球j之间的所有气球都可能是最后被戳破的那一个，不防假设为k。回顾动态规划的套路，这里其实已经找到了「状态」和「选择」：i和j就是两个「状态」，最后戳破的那个气球k就是「选择」。
     * 根据刚才对dp数组的定义，如果最后一个戳破气球k，dp[i][j]的值应该为：
     * <pre>
     * dp[i][j] = dp[i][k] + dp[k][j]
     * + points[i]*points[k]*points[j]
     * </pre>
     * 你不是要最后戳破气球k吗？那得先把开区间(i, k)的气球都戳破，再把开区间(k, j)的气球都戳破；最后剩下的气球k，相邻的就是气球i和气球j，这时候戳破k的话得到的分数就是points[i]*points[k]*points[j]。
     * 那么戳破开区间(i, k)和开区间(k, j)的气球最多能得到的分数是多少呢？嘿嘿，就是dp[i][k]和dp[k][j]，这恰好就是我们对dp数组的定义嘛！
     */
    public int maxCoins(int[] nums) {
        int size = nums.length;
        int[][] dp = new int[size][size]; /* dp含义：从数组的i到j的获得的最大硬币数量为dp[i][j] */
        // base case: 数组中只存在一个元素时，获得的硬币数量就是数字本身
        for (int i = 0; i < size; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = size - 2; i >= 0; i--) {
            for (int j = i + 1; j < size; j++) {
                for (int k = i; k <= j; k++) { /* 遍历k从i到j，表示最后哪一个气球被戳破会得到的硬币数量最多 */
                    dp[i][j] = Math.max(dp[i][j], getFromDP(dp, i, k) + getFromNums(nums, i, j, k - 1) * nums[k] * getFromNums(nums, i, j, k + 1) + getFromDP(dp, k, j));
                }
            }
        }
        return dp[0][size - 1];
    }

    int getFromDP(int[][] dp, int i, int j) {
        return i <= j ? dp[i][j] : 0;
    }

    int getFromNums(int[] nums, int start, int end, int i) {
        return start <= i && i <= end ? nums[i] : 1;
    }

    public static void main(String[] args) {
        Solution312 solution = new Solution312();
        System.out.println(solution.maxCoins(new int[] { 3, 1, 5, 8 })); // output: 1797097
    }
}
