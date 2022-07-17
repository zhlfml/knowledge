package me.thomas.knowledge.algorithm.leetcode.math.prime;

import java.util.Arrays;

/**
 * 204. 计数质数
 * 给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution204.java, v 0.1 2022/7/17 08:53 xinsheng2.zhao Exp $
 */
public class Solution204 {

    public int countPrimes(int n) {
        boolean[] primes = new boolean[n];
        Arrays.fill(primes, true);

        int answer = 0;
        for (int i = 2; i < n; i++) {
            if (!primes[i]) {
                continue;
            }
            answer++;
            for (long j = (long) i * i; j < n; j += i) {
                primes[(int) j] = false;
            }
        }
        return answer;
    }

}
