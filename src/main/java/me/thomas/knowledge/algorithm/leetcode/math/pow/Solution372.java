package me.thomas.knowledge.algorithm.leetcode.math.pow;

/**
 * 372. 超级次方
 * 你的任务是计算 a<sup>b</sup> 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution372.java, v 0.1 2022/7/17 10:11 xinsheng2.zhao Exp $
 */
public class Solution372 {

    final int MOD = 1337;

    public int superPow(int a, int[] b) {
        if (a < 0) {
            return -1;
        }
        if (a <= 1) {
            return a;
        }
        return superPow(a, b, b.length - 1);
    }

    /**
     * 思路：
     * 幂公式：a^bcd = a^d * (a^bc)^10
     * 取模公式：(a * b) mod c = ((a mod c) * (b mod c)) mod c
     *
     * @param a 底数
     * @param b 指数，由数组组成
     * @param p 指数当前处理的数，从b.length - 1到0处理
     */
    int superPow(int a, int[] b, int p) {
        if (p == 0) {
            return powmod(a, b[p]);
        }
        return powmod(a, b[p]) * powmod(superPow(a, b, p - 1), 10) % MOD;
    }

    /**
     * 快速计算指数取模运算
     *
     * @param a 底数
     * @param x 指数 范围[0, 10]
     * @return 运算结果
     */
    int powmod(int a, int x) {
        a = a % MOD;
        if (x == 0) {
            return 1;
        }
        if (x % 2 == 0) {
            return powmod(a * a, x / 2);
        } else {
            return (a * powmod(a * a, (x - 1) / 2)) % MOD; /* 由于x的取值范围为[0, 10]，所以不用担心(a * powmod(a * a, (x - 1) / 2))越界 */
        }
    }

    public static void main(String[] args) {
        Solution372 solution = new Solution372();
        System.out.println(solution.superPow(1336, new int[] { 7 }));
    }
}
