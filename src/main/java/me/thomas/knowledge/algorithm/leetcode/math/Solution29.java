package me.thomas.knowledge.algorithm.leetcode.math;

/**
 * 29. 两数相除
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * <p>
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * <p>
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 *
 * @author xinsheng2.zhao
 * @version Id: Solution29.java, v 0.1 2022/10/14 11:34 xinsheng2.zhao Exp $
 */
public class Solution29 {

    /**
     * 由于负数的区间比正数的区间大1，所以除法结果溢出的情况只存在一种：-2^31 / -1，这种情况需要特殊处理。
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("divisor == 0");
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (divisor == 1) { /* 这个base case逃避不了，因为`Integer.MIN_VALUE / 1`时由于都取成负数相除，结果一定是正数，但答案溢出了。*/
            return dividend;
        }
        if (divisor == -1) { /* 这个base case比必要，但可以加速运算。*/
            return -dividend;
        }
        boolean positive = (dividend ^ divisor) >= 0;/* 检测符号位是否相同 */
        // 再次由于负数的区间大，所以都转成负数后再做除法
        if (dividend > 0) {
            dividend = -dividend;
        }
        if (divisor > 0) {
            divisor = -divisor;
        }
        // 存储divisor的倍率表，后者在前者的基础上 ✖️2，所以最多只需32格子的数组
        int[] table = new int[32];
        int i = -1; /* 陷进：得从-1开始，如果从0开始那么得将`i++`写到`table[i] = divisor << i`之后，会造成while条件不满足条件时i多自增了1从而导致答案错误 */
        do {
            i++;
            table[i] = divisor << i;
        } while (dividend - table[i] <= table[i]); /* 剩余的数小于半数时停止循环 */
        // 通过倍率表得出商
        int answer = 0; /* 商 */
        int left = dividend; /* 余数 */
        for (; i >= 0; i--) {
            if (left <= table[i]) {
                answer += (1 << i);
                left -= table[i];
            }
        }
        return positive ? answer : -answer;
    }

    public static void main(String[] args) {
        Solution29 solution29 = new Solution29();
        System.out.println(solution29.divide(Integer.MIN_VALUE, 2));
    }
}
