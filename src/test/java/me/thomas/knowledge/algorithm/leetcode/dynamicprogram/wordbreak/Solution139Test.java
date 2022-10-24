package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.wordbreak;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class Solution139Test {

    Solution139 solution139;

    @Before public void setUp() throws Exception {
        solution139 = new Solution139();
    }

    @Test public void wordBreak() {
        assertTrue(solution139.wordBreak("mycat", Arrays.asList("my", "cat")));
    }
}