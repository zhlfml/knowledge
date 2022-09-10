package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.game;

/**
 * 651. 4键键盘
 * 假设你有一个特殊的键盘，上面只有四个键，它们分别是：
 * A 键：在屏幕上打印一个 A。
 * Ctrl-A 键：选中整个屏幕。
 * Ctrl-C 键：复制选中的区域到缓冲区。
 * Ctrl-V 键：将缓冲区的内容输入到光标所在的屏幕上。
 * 现在要求你只能进行 N 次操作，请你计算屏幕上最多能显示多少个 A？
 *
 * @author xinsheng2.zhao
 * @version Id: Solution651.java, v 0.1 2022/9/10 08:52 xinsheng2.zhao Exp $
 */
public class Solution651 {

    /**
     * 思路：Ctrl-A,Ctrl-C,Ctrl-V三个键需要花三次按键的代码将之前的A的数量翻倍，所以需要至少有三个A后才值得拷贝复制。
     * 花两次敲击的代价Ctrl-A,Ctrl-C之后Ctrl-V可以每次翻倍，所以是划算的。
     */
    public int maxA(int n) {
        int[] dp = new int[n + 1]; /* 含义：操作i次键盘后屏幕最多能显示dp[i]个A */
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1; // 至少可以按一个A键
            for (int j = 4; j < i; j++) { /* 寻找从哪里开始拷贝复制可以达到最多数量的A，因为dp[0..2]的值分别为0、1、2所以至少从第4个字符开始拷贝才会划算，此时dp[4-2]=2。 */
                dp[i] = Math.max(dp[i], dp[j - 2] * (i - j + 1));
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Solution651 solution = new Solution651();
        System.out.println(solution.maxA(9));
    }
}
