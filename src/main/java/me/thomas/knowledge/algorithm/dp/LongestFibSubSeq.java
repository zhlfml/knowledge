/**
 * LY.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package me.thomas.knowledge.algorithm.dp;

import java.util.HashMap;

/**
 * LongestFibSubSeq
 *
 * @author xinsheng2.zhao
 * @version Id: LongestFibSubSeq.java, v 0.1 3/6/22 3:41 PM xinsheng2.zhao Exp $
 */
public class LongestFibSubSeq {

    public int lenLongestFibSubseq(int[] arr) {
        HashMap<Integer, Integer> valueIndex = new HashMap<>((int) (arr.length / 0.75) + 1);
        for (int i = 0; i < arr.length; i++) {
            valueIndex.put(arr[i], i);
        }

        int longest = 2;
        int[][] dp = new int[arr.length][arr.length];
        for (int k = 1; k < arr.length; k++) {
            for (int j = k - 1; j >= 0; j--) {
                if (j == 0) {
                    dp[j][k] = 2;
                    continue;
                }
                int i = valueIndex.getOrDefault(arr[k] - arr[j], -1);
                dp[j][k] = i > -1 && i < j ? dp[i][j] + 1 : 2;
                if (longest < dp[j][k]) {
                    longest = dp[j][k];
                }
            }
        }

        return longest > 2 ? longest : 0;
    }

}
