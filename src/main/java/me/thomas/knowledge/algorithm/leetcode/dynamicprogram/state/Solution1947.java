package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.state;

/**
 * 1947. 最大兼容性评分和
 * 有一份由 n 个问题组成的调查问卷，每个问题的答案要么是 0（no，否），要么是 1（yes，是）。
 * <p>
 * 这份调查问卷被分发给 m 名学生和 m 名导师，学生和导师的编号都是从 0 到 m - 1 。学生的答案用一个二维整数数组 students 表示，其中 students[i] 是一个整数数组，包含第 i 名学生对调查问卷给出的答案（下标从 0 开始）。导师的答案用一个二维整数数组 mentors 表示，其中 mentors[j] 是一个整数数组，包含第 j 名导师对调查问卷给出的答案（下标从 0 开始）。
 * <p>
 * 每个学生都会被分配给 一名 导师，而每位导师也会分配到 一名 学生。配对的学生与导师之间的兼容性评分等于学生和导师答案相同的次数。
 * <p>
 * 例如，学生答案为[1, 0, 1] 而导师答案为 [0, 0, 1] ，那么他们的兼容性评分为 2 ，因为只有第二个和第三个答案相同。
 * 请你找出最优的学生与导师的配对方案，以 最大程度上 提高 兼容性评分和 。
 * <p>
 * 给你 students 和 mentors ，返回可以得到的 最大兼容性评分和 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution1947.java, v 0.1 2022/8/24 10:17 xinsheng2.zhao Exp $
 */
public class Solution1947 {

    /**
     * 输入：students = [[1,1,0],[1,0,1],[0,0,1]], mentors = [[1,0,0],[0,0,1],[1,1,0]]
     * 输出：8
     * 解释：按下述方式分配学生和导师：
     * - 学生 0 分配给导师 2 ，兼容性评分为 3 。
     * - 学生 1 分配给导师 0 ，兼容性评分为 2 。
     * - 学生 2 分配给导师 1 ，兼容性评分为 3 。
     * 最大兼容性评分和为 3 + 2 + 3 = 8 。
     * <p>
     * 思路：使用状态压缩动态规划时，数n的比特位为1表示当前需要后一个数组对应位上的元素与前一个数组的前bitCount(n)的元素做计算并存储最优值。
     */
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int dpSize = 1 << students.length;
        int[] dp = new int[dpSize];
        for (int i = 0; i < dpSize; i++) {
            for (int j = 0; j < students.length; j++) { // 假设当前数的比特位为 00110101
                if ((i & (1 << j)) > 0) { // 这一步能检测出j = 0, 2, 4, 5时的i对应的bit数等于1
                    dp[i] = Math.max(dp[i],
                            dp[i ^ (1 << j)] + matchScore(students[bitCount(i) - 1], mentors[j])); // 这一步的意思是计算00110101分别从00110100，00110001，00100101，00010101转化而来的最优解。
                }
            }
        }
        return dp[dpSize - 1];
    }

    int bitCount(int n) {
        int count = 0;
        while (n > 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    /**
     * 计算学生与导师的兼容性评分。
     *
     * @param student 学生
     * @param mentor  导师
     * @return 兼容性评分
     */
    int matchScore(int[] student, int[] mentor) {
        if (student.length != mentor.length) {
            throw new IllegalStateException("student.length != mentor.length");
        }
        int score = 0;
        for (int i = 0; i < student.length; i++) {
            if (student[i] == mentor[i]) {
                score++;
            }
        }
        return score;
    }

    public static void main(String[] args) {
        Solution1947 solution = new Solution1947();
        System.out.println(solution.maxCompatibilitySum(new int[][] { { 1, 1, 0 }, { 1, 0, 1 }, { 0, 0, 1 } }, new int[][] { { 1, 0, 0 }, { 0, 0, 1 }, { 1, 1, 0 } }));
    }
}
