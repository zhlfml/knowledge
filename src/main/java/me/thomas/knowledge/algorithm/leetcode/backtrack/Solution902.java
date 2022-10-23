package me.thomas.knowledge.algorithm.leetcode.backtrack;

/**
 * 902. 最大为 N 的数字组合
 * 给定一个按 非递减顺序 排列的数字数组 digits 。你可以用任意次数 digits[i] 来写的数字。例如，如果 digits = ['1','3','5']，我们可以写数字，如 '13', '551', 和 '1351315'。
 * <p>
 * 返回 可以生成的小于或等于给定整数 n 的正整数的个数 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution902.java, v 0.1 2022/10/23 13:16 xinsheng2.zhao Exp $
 */
public class Solution902 {

    long track  = 0; /* 考虑数值会超过Integer.MAX_VALUE */
    int  answer = 0;

    public int atMostNGivenDigitSet(String[] digits, int n) {
        return atMostNGivenDigitSet_backtrack(digits, n);
    }

    /**
     * 回溯法 -- 算法正确，但会超时
     * 难点：1. 回溯过程中字符与数值的转换 2. 如何利用“非递减顺序”条件剪枝？
     * 易错点：1.忽略了数字会超过Integer.MAX_VALUE
     */
    int atMostNGivenDigitSet_backtrack(String[] digits, int n) {
        backtrack(digits, n);
        return answer;
    }

    void backtrack(String[] digits, int n) {
        for (int i = 0; i < digits.length; i++) {
            track = track * 10 + Integer.parseInt(digits[i]);
            if (track <= n) {
                answer++;
            } else {
                track = track / 10; /* break时也需要清理track */
                break;
            }
            backtrack(digits, n);
            track = track / 10;
        }
    }
}
