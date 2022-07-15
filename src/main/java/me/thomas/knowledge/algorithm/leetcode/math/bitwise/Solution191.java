package me.thomas.knowledge.algorithm.leetcode.math.bitwise;

/**
 * 191. 位1的个数
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution191.java, v 0.1 2022/7/15 21:08 xinsheng2.zhao Exp $
 */
public class Solution191 {

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int answer = 0;
        while (n != 0) {
            n = n & (n - 1);
            answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution191 solution = new Solution191();
        System.out.println(solution.hammingWeight(Integer.MAX_VALUE));
        System.out.println(solution.hammingWeight(Integer.MIN_VALUE));
        System.out.println(Integer.MIN_VALUE == Integer.MAX_VALUE + 1);
    }
}
