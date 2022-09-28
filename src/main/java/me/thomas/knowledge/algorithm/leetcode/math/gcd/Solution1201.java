package me.thomas.knowledge.algorithm.leetcode.math.gcd;

/**
 * 1201. 丑数 III
 * 给你四个整数：n 、a 、b 、c ，请你设计一个算法来找出第 n 个丑数。
 * <p>
 * 丑数是可以被 a 或 b 或 c 整除的 正整数 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution1201.java, v 0.1 2022/7/17 11:00 xinsheng2.zhao Exp $
 */
public class Solution1201 {

    final int LEFT_BOUND  = 1;
    final int RIGHT_BOUND = 2 * 1000_000_000;

    /**
     * 输入：n = 4, a = 2, b = 3, c = 4
     * 输出：6
     * 解释：丑数序列为 2, 3, 4, 6, 8, 9, 10, 12... 其中第 4 个是 6。
     * <p>
     * 思路：第一步需要解决这样一个问题：k以内a，b，c的倍数的数有多少个？
     * 设 m = k / a, 即k以内数字a倍数的数量，
     * 设 n = k / b, 即k以内数字b倍数的数量，
     * 设 l = k / c, 即k以内数字c倍数的数量，
     * 那么答案 q 等于 m + n + l - a,b倍数的交集 - b,c倍数的交集 - a,c倍数的交集 + a,b,c倍数的交集。
     * 使用三个圆作图可以很明显的看出 m, n, l之间的这种关系。
     * 如果q != n，则可以使用查找左边界的二分查找法找出k的边界。
     * 为什么使用查找左边界的二分查找算法呢？举个例子：示例中的第4个丑数为6，但是使用数字7代入公式计算出来的丑数的数量也是4个，但是7明显不是他们的丑数，这是由于公式中除法的误差导致。
     */
    public int nthUglyNumber(int n, int a, int b, int c) {
        int low = LEFT_BOUND, high = RIGHT_BOUND;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = (int) count(mid, a, b, c);
            if (count < n) {
                low = mid + 1;
            } else { /* 左边界二分查找，当count == n时设置high = mid迫使继续向左查找更小的值 */
                high = mid;
            }
        }
        return low; /* 因为向左移动的过程中`high = mid`，所以high始终指向合法的值，当low == high退出时，直接返回low或high即可。*/
    }

    /**
     * Greatest Common Divisor
     * 辗转相除法求a,b的最大公约数
     */
    long gcd(long a, long b) {
        if (a < b) {
            return gcd(b, a);
        }
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    /**
     * Least Common Multiple
     * 求a,b最大公倍数
     */
    long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    /**
     * 求k以内a，b，c的倍数的数量
     */
    long count(int k, int a, int b, int c) {
        return k / a + k / b + k / c - k / lcm(a, b) - k / lcm(a, c) - k / lcm(b, c) + k / lcm(a, lcm(b, c));
    }

    public static void main(String[] args) {
        Solution1201 solution1201 = new Solution1201();
        System.out.println(solution1201.nthUglyNumber(4, 2, 3, 4));
    }
}
