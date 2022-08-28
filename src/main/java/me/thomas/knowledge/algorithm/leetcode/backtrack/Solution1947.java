package me.thomas.knowledge.algorithm.leetcode.backtrack;

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
     * 最优的学生与导师的配对方案，以 最大程度上 提高 兼容性评分和。
     */
    int maxScore = 0;

    /**
     * 输入：students = [[1,1,0],[1,0,1],[0,0,1]], mentors = [[1,0,0],[0,0,1],[1,1,0]]
     * 输出：8
     * 解释：按下述方式分配学生和导师：
     * - 学生 0 分配给导师 2 ，兼容性评分为 3 。
     * - 学生 1 分配给导师 0 ，兼容性评分为 2 。
     * - 学生 2 分配给导师 1 ，兼容性评分为 3 。
     * 最大兼容性评分和为 3 + 2 + 3 = 8 。
     */
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        backtrack(students, 0, mentors, new boolean[mentors.length], 0);
        return maxScore;
    }

    /**
     * 回溯法计算学生与导师的最大兼容性评分和。
     *
     * @param students 学生列表
     * @param start    当前需要匹配的学生 -- 关键参数
     * @param mentors  导师列表
     * @param selected 已经匹配了学生的导师列表 -- 关键参数
     * @param score    目前学生与导师匹配的评分和
     * @return
     */
    void backtrack(int[][] students, int start, int[][] mentors, boolean[] selected, int score) {
        // System.out.printf("%d %s %d\n", start, Arrays.toString(selected), score);
        // 递归的出口
        if (start == students.length) {
            maxScore = Math.max(maxScore, score);
            return;
        }
        for (int i = 0; i < selected.length; i++) {
            // 导师被选过之后不能再被选择
            if (selected[i]) {
                continue;
            }
            selected[i] = true; // 选择该导师
            backtrack(students, start + 1, mentors, selected, score + matchScore(students[start], mentors[i]));
            selected[i] = false; // 撤销选择
        }
    }

    /**
     * 计算学生与导师的兼容性评分。
     *
     * @param student 学生
     * @param mentor  导师
     * @return 兼容性评分
     */
    int matchScore(int[] student, int[] mentor) {
        int studentAnswerLength = student.length;
        int mentorAnswerLength = mentor.length;
        if (studentAnswerLength != mentorAnswerLength) {
            throw new IllegalStateException("studentAnswerLength != mentorAnswerLength");
        }
        int score = 0;
        for (int i = 0; i < studentAnswerLength; i++) {
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
