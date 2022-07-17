package me.thomas.knowledge.algorithm.leetcode.math.factorial;

/**
 * 793. 阶乘函数后 K 个零
 * f(x) 是 x! 末尾是 0 的数量。回想一下 x! = 1 * 2 * 3 * ... * x，且 0! = 1 。
 * <p>
 * 例如， f(3) = 0 ，因为 3! = 6 的末尾没有 0 ；而 f(11) = 2 ，因为 11!= 39916800 末端有 2 个 0 。
 * 给定 k，找出返回能满足 f(x) = k 的非负整数 x 的数量。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution793.java, v 0.1 2022/7/17 08:31 xinsheng2.zhao Exp $
 */
public class Solution793 {

    /**
     * 思路：二分查找
     *
     * @param k
     * @return
     */
    public int preimageSizeFZF(int k) {
        int left = leftBound(k), right = rightBound(k);
        return right - left + 1;
    }

    int leftBound(int target) {
        long low = 0, high = Long.MAX_VALUE;
        while (low < high) {
            long mid = ((high - low) >> 1) + low;
            long zeroes = trailingZeroes(mid);
            if (zeroes < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return (int) low;
    }

    int rightBound(int target) {
        long low = 0, high = Long.MAX_VALUE;
        while (low < high) {
            long mid = ((high - low) >> 1) + low;
            long zeroes = trailingZeroes(mid);
            if (zeroes <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return (int) low - 1;
    }

    /**
     * 计算n的阶乘后有几个0
     * 思路：将所有5的倍数的数量加起来
     * 注意：使用long类型，否则会溢出
     */
    long trailingZeroes(long n) {
        long answer = 0;
        while (n > 0) {
            n = n / 5;
            answer += n;
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution793 solution = new Solution793();
        System.out.println(solution.preimageSizeFZF(5));
    }
}
