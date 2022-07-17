package me.thomas.knowledge.algorithm.leetcode.math.prime;

import java.util.ArrayList;
import java.util.List;

/**
 * 204. 计数质数
 * 给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution204.java, v 0.1 2022/7/17 08:53 xinsheng2.zhao Exp $
 */
public class Solution204 {

    List<Integer> primes;

    public int countPrimes(int n) {
        if (n < 2) {
            return 0;
        }
        primes = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes.size();
    }

    boolean isPrime(int number) {
        for (int prime : primes) {
            if (number % prime == 0) {
                return false;
            }
        }
        return true;
    }
}
