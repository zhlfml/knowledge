package me.thomas.knowledge.algorithm.leetcode.dynamicprogram;

import java.util.Arrays;

/**
 * 354. 俄罗斯套娃信封问题
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 * <p>
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * <p>
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * <p>
 * 注意：不允许旋转信封。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution354.java, v 0.1 2022/8/4 21:18 xinsheng2.zhao Exp $
 */
public class Solution354 {

    /**
     * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
     * 输出：3
     * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
     */
    public int maxEnvelopes(int[][] envelopes) {
        // 按照宽度和高度升序排列
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] < b[0] ? -1 : 1;
            } else {
                return a[1] < b[1] ? -1 : 1;
            }
        });

        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);

        int answer = 0;
        for (int i = 0; i < envelopes.length; i++) {
            int[] last = envelopes[i];
            for (int j = i - 1; j >= 0; j--) {
                int[] before = envelopes[j];
                if (before[0] < last[0] && before[1] < last[1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            answer = Math.max(answer, dp[i]);
        }
        return answer;
    }
}
