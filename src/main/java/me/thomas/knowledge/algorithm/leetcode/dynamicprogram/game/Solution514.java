package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 514. 自由之路
 * 电子游戏“辐射4”中，任务 “通向自由” 要求玩家到达名为 “Freedom Trail Ring” 的金属表盘，并使用表盘拼写特定关键词才能开门。
 * <p>
 * 给定一个字符串 ring ，表示刻在外环上的编码；给定另一个字符串 key ，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
 * <p>
 * 最初，ring 的第一个字符与 12:00 方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
 * <p>
 * 旋转 ring 拼出 key 字符 key[i] 的阶段中：
 * <p>
 * 您可以将 ring 顺时针或逆时针旋转 一个位置 ，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
 * 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution514.java, v 0.1 2022/8/17 22:28 xinsheng2.zhao Exp $
 */
public class Solution514 {

    /**
     * 输入: ring = "godding", key = "gd"
     * 输出: 4
     * 思路：记录key中每走一个字符在ring中所有位置的最小值。
     * dp[key..][ring..]
     * 拼写key的首字母g时，有两种选择: 0,6。最小的代价分别是dp[0][0] = 1（直接按下中心按钮）, dp[0][6] = 2（逆时针波动一格再按下中心按钮）.
     * 拼写key的第二个字母d时，也有两种选择: 2,3。这两种选择只能有上一步的dp[0][0], dp[0][6]走过来。
     * 从0走到2需顺时针移动2格，再敲击按钮共3步。所以从dp[0][0]到dp[1][2]共需要4步，即dp[1][2] = 4。
     * 从0走到3需顺时针移动3格，再敲击按钮共4步。所以从dp[0][0]到dp[1][3]共需要5步，即dp[1][3] = 5。
     * 从6走到2需顺时针移动3格，再敲击按钮共4步。所以从dp[0][0]到dp[1][3]共需要6步，即dp[1][2] = 6。
     * 从6走到3需逆时针移动3格，再敲击按钮共4步。所以从dp[0][0]到dp[1][3]共需要5步，即dp[1][3] = 6。
     * 综上所述：dp[1][2]最小值为4，dp[1][3]最小值为5。所以答案为最小值中的最小值4。
     */
    public int findRotateSteps(String ring, String key) {
        if (ring == null || ring.length() == 0) {
            return 0;
        }
        if (key == null || key.length() == 0) {
            return 0;
        }

        int m = key.length(), n = ring.length();
        List<Integer>[] pos = new List[26];
        for (int i = 0; i < 26; i++) {
            pos[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            pos[ring.charAt(i) - 'a'].add(i);
        }

        int[][] dp = new int[m][n]; /* 含义：敲击出key[i]，指向ring中第j个字符（key[i]==ring[j]）需要的最小步骤为dp[i][j] */
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], 0x3f3f3f3f);
        }
        // base case: 单独处理key的第一个字符，可以免去if操作
        for (int j : pos[key.charAt(0) - 'a']) {
            dp[0][j] = Math.min(j, n - j) + 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j : pos[key.charAt(i) - 'a']) {
                for (int k : pos[key.charAt(i - 1) - 'a']) { // key的第i个字符由前一个字符推导而来。
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.min(Math.abs(j - k), n - Math.abs(j - k)) + 1);
                }
            }
        }
        return Arrays.stream(dp[m - 1]).min().orElse(-1);
    }

    public static void main(String[] args) {
        Solution514 solution = new Solution514();
        System.out.println(solution.findRotateSteps("godding", "gd"));
    }
}
