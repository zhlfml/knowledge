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
        if (a <= 0) {
            return -1;
        }
        if (a == 1) {
            return 1;
        }
        int length = b.length;
        int x = b[length - 1];
        if (length == 1) {
            return powmod(a, x);
        }
        int[] nb = new int[length - 1];
        System.arraycopy(b, 0, nb, 0, nb.length);
        return powmod(a, x) * powmod(superPow(a, nb), 10) % MOD;
    }

    int powmod(int a, int x) {
        if (x < 0) {
            return -1;
        }
        if (x == 0) {
            return 1;
        }
        a %= MOD;
        if (x == 1) {
            return a;
        }
        if (x % 2 == 0) {
            return powmod(a * a, x / 2) % MOD;
        } else {
            return a * powmod(a * a, (x - 1) / 2) % MOD;
        }
    }

    public static void main(String[] args) {
        Solution372 solution = new Solution372();
        System.out.println(solution.superPow(5577, new int[] { 5, 3, 4, 7, 3 }));
    }
}
