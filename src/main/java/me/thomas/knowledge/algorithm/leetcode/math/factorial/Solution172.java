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
     * 比如125!后面有多少个零呢？
     * 5,10,15,20,25,30...120,125都可以分解出一个5，这样的数有25个。
     * 25,50,75,100,125又可以分解出第二个5，这样的数有5个。
     * 125还可以再分解出第三个5，这样的数有1个。
     * 所以一共可以分解出31个5，即后面跟了31个零。
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
