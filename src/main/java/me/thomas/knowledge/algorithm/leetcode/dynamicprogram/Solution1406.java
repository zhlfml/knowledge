package me.thomas.knowledge.algorithm.leetcode.dynamicprogram;

/**
 * 1406. 石子游戏 III
 * Alice 和 Bob 用几堆石子在做游戏。几堆石子排成一行，每堆石子都对应一个得分，由数组 stoneValue 给出。
 * <p>
 * Alice 和 Bob 轮流取石子，Alice 总是先开始。在每个玩家的回合中，该玩家可以拿走剩下石子中的的前 1、2 或 3 堆石子 。比赛一直持续到所有石头都被拿走。
 * <p>
 * 每个玩家的最终得分为他所拿到的每堆石子的对应得分之和。每个玩家的初始分数都是 0 。比赛的目标是决出最高分，得分最高的选手将会赢得比赛，比赛也可能会出现平局。
 * <p>
 * 假设 Alice 和 Bob 都采取 最优策略 。如果 Alice 赢了就返回 "Alice" ，Bob 赢了就返回 "Bob"，平局（分数相同）返回 "Tie" 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution1406.java, v 0.1 2022/8/28 21:35 xinsheng2.zhao Exp $
 */
public class Solution1406 {

    /**
     * 输入：values = [1,2,3,-9]
     * 输出："Alice"
     * 解释：Alice 要想获胜就必须在第一个回合拿走前三堆石子，给 Bob 留下负分。
     * 如果 Alice 只拿走第一堆，那么她的得分为 1，接下来 Bob 拿走第二、三堆，得分为 5 。之后 Alice 只能拿到分数 -9 的石子堆，输掉比赛。
     * 如果 Alice 拿走前两堆，那么她的得分为 3，接下来 Bob 拿走第三堆，得分为 3 。之后 Alice 只能拿到分数 -9 的石子堆，同样会输掉比赛。
     * 注意，他们都应该采取 最优策略 ，所以在这里 Alice 将选择能够使她获胜的方案。
     */
    public String stoneGameIII(int[] stoneValue) {
        int size = stoneValue.length;
        int[][] dp = new int[size][size]; /* 含义：从石子的第i到j堆的最大差值dp[i][j] */
        for (int i = 0; i < size; i++) {
            dp[i][i] = stoneValue[i];
        }
        for (int i = size - 2; i >= 0; i--) {
            for (int j = i + 1; j < size; j++) {
                dp[i][j] = max(preSum(stoneValue, i, j, 1) - dpValue(dp, i + 1, j), preSum(stoneValue, i, j, 2) - dpValue(dp, i + 2, j),
                        preSum(stoneValue, i, j, 3) - dpValue(dp, i + 3, j));
            }
        }

        String answer = "Tie";
        if (dp[0][size - 1] > 0) {
            answer = "Alice";
        } else if (dp[0][size - 1] < 0) {
            answer = "Bob";
        }
        return answer;
    }

    /**
     * 计算并返回数组stoneValue从start到end的前n个数之和。
     */
    int preSum(int[] stoneValue, int start, int end, int n) {
        int sum = 0;
        for (int i = start; i <= end && i - start < n; i++) {
            sum += stoneValue[i];
        }
        return sum;
    }

    int dpValue(int[][] dp, int i, int j) {
        if (i > j) {
            return 0;
        }
        return dp[i][j];
    }

    int max(int... values) {
        int maxValue = Integer.MIN_VALUE;
        for (int value : values) {
            maxValue = Math.max(maxValue, value);
        }
        return maxValue;
    }

    public static void main(String[] args) {
        Solution1406 solution = new Solution1406();
        System.out.println(solution.stoneGameIII(new int[] { -565, 891, 552, 969, 903 }));
    }
}
