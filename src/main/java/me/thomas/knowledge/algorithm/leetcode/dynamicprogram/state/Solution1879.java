package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.state;

import java.util.Arrays;

/**
 * 1879. 两个数组最小的异或值之和
 * 给你两个整数数组 nums1 和 nums2 ，它们长度都为 n 。
 * <p>
 * 两个数组的 异或值之和 为 (nums1[0] XOR nums2[0]) + (nums1[1] XOR nums2[1]) + ... + (nums1[n - 1] XOR nums2[n - 1]) （下标从 0 开始）。
 * <p>
 * 比方说，[1,2,3] 和 [3,2,1] 的 异或值之和 等于 (1 XOR 3) + (2 XOR 2) + (3 XOR 1) = 2 + 0 + 2 = 4 。
 * 请你将 nums2 中的元素重新排列，使得 异或值之和 最小 。
 * <p>
 * 请你返回重新排列之后的 异或值之和 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution1879.java, v 0.1 2022/8/22 22:14 xinsheng2.zhao Exp $
 */
public class Solution1879 {

    /**
     * 输入：nums1 = [1,0,3], nums2 = [5,3,4]
     * 输出：8
     * 解释：将 nums2 重新排列得到 [5,4,3] 。
     * 异或值之和为 (1 XOR 5) + (0 XOR 4) + (3 XOR 3) = 4 + 4 + 0 = 8 。
     * <p>
     * 思路：状态压缩动态规划
     * 最容易想到的思路是暴力回溯法，
     * 第一轮：nums1的第一个数字先和num2的第一个数字异或，...，nums1的最后一个数字先和num2的最后一个数字异或。依次将其相加得到一个数值。
     * ...
     * 最后一轮:nums1的第一个数字先和num2的最后一个数字异或，...，nums1的最后一个数字先和num2的倒数第二个数字异或。依次将其相加得到一个数值。
     * 这些轮次的最小值即为答案，但是时间复杂度是n!，很明显14（数组长度范围）的阶乘为8*10的11次方，会执行超时。
     * 所以需要思考时间复杂度为2^n次方的算法，2的14次方为16384。
     * 于是状态压缩动态规划算法应运而生。
     * 开辟dp[2^n]大小的数组，dp[index]的index的2进制中bit为1的值说明num2中对应位置的数已经与num1的前x（index的二进制bit中1的数量）位做过异或操作，且最小值保存在dp[index]中。
     * 以输入：nums1 = [1,0,3], nums2 = [5,3,4]为例讲解计算过程。
     * 需开辟dp[8]的数组。
     * dp[0] = 0, 因为此时没有做过异或计算。
     * dp[1] = 4，因为1的二进制数为001, 二进制状态001只能由000转化而来，所以只能是dp[0] + num2[0] XOR num1[0] = 0 + 5 XOR 1 = 4。
     * dp[2] = 2，因为2的二进制数为010, 二进制状态010只能由000转化而来，所以只能是dp[0] + num2[1] XOR num1[0] = 0 + 3 XOR 1 = 2。
     * dp[3]的计算比较复杂，因为3的二进制数为011, 二进制状态011可以由001或010转化而来，需要选这两种case里的小值。
     * * 先计算case 1，由001转化到011，此时说明num1[0] XOR num2[0]，所以剩下只能nums1[1] XOR nums2[1] 即 0 XOR 3 = 3，其和为dp[1] + 3 = 7。
     * * 再计算case 2，由010转化到011，此时说明num1[0] XOR num2[1]，所以剩下只能nums1[1] XOR nums2[0] 即 0 XOR 5 = 5，其和为dp[2] + 5 = 8。
     * * 取小，所以dp[3] = 7。
     * dp[4] = 5，因为4的二进制数为100, 二进制状态100只能由000转化而来，所以只能是dp[0] + num2[2] XOR num1[0] = 0 + 4 XOR 1 = 5。
     * dp[5]计算过程同dp[3]，由于5的二进制为101，101可以由100或则001转化而来。
     * * 先计算case 1，由001转化到101，此时说明已num1[0] XOR num2[0]，所以剩下只能nums1[1] XOR nums2[2] 即 0 XOR 4 = 4，其和为dp[1] + 4 = 8。
     * * 再计算case 2，由100转化到101，此时说明已num1[0] XOR num2[2]，所以剩下只能nums1[1] XOR nums2[0] 即 0 XOR 5 = 5，其和为dp[4] + 5 = 10。
     * * 取小，所以dp[5] = 8。
     * dp[6] = 6（计算过程同dp[3]，dp[5]，略）。
     * dp[7] = 8，7的二进制为111，可以由011（dp[3] + num1[2] XOR num2[2] = 7 + 6 = 13），101（dp[5] + num1[2] XOR num2[1] = 8 + 0 = 8），110（dp[6] + num1[2] XOR num2[0] = 6 + 7 = 13）转化而来，取小值为8。
     */
    public int minimumXORSum(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            throw new IllegalArgumentException("nums1 is empty");
        }
        if (nums2 == null || nums2.length == 0) {
            throw new IllegalArgumentException("nums2 is empty");
        }
        if (nums1.length != nums2.length) {
            throw new IllegalArgumentException("nums1.length not equals nums2.length");
        }
        int n = 1 << nums1.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 0x3f3f3f3f); /* 默认为无穷大 */
        dp[0] = 0; /* base case */
        for (int i = 1; i < n; i++) { /* 每个i都是一个压缩状态，二进制位为1说明num2中对应的数字已与num1中前x的数异或计算 */
            for (int j = 0; j < nums1.length; j++) {
                if ((i & (1 << j)) > 0) { /* 说明数值i的第（j + 1）位的bit值为1 */
                    dp[i] = Math.min(dp[i],
                            dp[(i ^ (1 << j))] /* num2的第j个数字未选 */ + (nums1[bitCount(i) - 1] ^ nums2[j] /* 选择num2的第j个数与num1中的第bitCount(i)-1个数异或 */));
                }
            }
        }
        return dp[n - 1];
    }

    int bitCount(int n) {
        int ans = 0;
        while (n > 0) {
            ans++;
            n = n & (n - 1); /* 消除最后一个为1的bit位 */
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution1879 solution = new Solution1879();
        System.out.println(solution.minimumXORSum(new int[] { 1, 0, 3 }, new int[] { 5, 3, 4 }));
    }
}
