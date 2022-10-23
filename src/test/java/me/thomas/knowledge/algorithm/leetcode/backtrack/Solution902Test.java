package me.thomas.knowledge.algorithm.leetcode.backtrack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Solution902Test {

    Solution902 solution902;

    @Before public void setUp() throws Exception {
        solution902 = new Solution902();
    }

    @Test public void atMostNGivenDigitSet() {
        assertEquals(29523, solution902.atMostNGivenDigitSet(new String[] { "1", "4", "9" }, 1000_000_000));
        assertEquals(153421211, solution902.atMostNGivenDigitSet(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }, 940860624));
    }
}