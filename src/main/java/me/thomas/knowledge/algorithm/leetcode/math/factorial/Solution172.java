package me.thomas.knowledge.algorithm.leetcode.math.factorial;

/**
 * 172. 阶乘后的零
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 * <p>
 * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 *
 * @author xinsheng2.zhao
 * @version Id: Solution172.java, v 0.1 2022/7/17 08:22 xinsheng2.zhao Exp $
 */
public class Solution172 {

    /**
     * 思路：将所有5的倍数的数量加起来
     */
    public int trailingZeroes(int n) {
        int answer = 0;
        while (n > 0) {
            n = n / 5;
            answer += n;
        }
        return answer;
    }
}
