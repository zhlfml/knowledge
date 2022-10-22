package me.thomas.knowledge.algorithm.leetcode.monotonicstack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Solution316Test {

    Solution316 solution316;

    @Before public void setUp() throws Exception {
        solution316 = new Solution316();
    }

    @Test public void removeDuplicateLetters() {
        assertEquals("eacb", solution316.removeDuplicateLetters("ecbacba"));
        assertEquals("adbc", solution316.removeDuplicateLetters("cdadabcc"));
        assertEquals("bac", solution316.removeDuplicateLetters("bbcaac"));
        assertEquals("abc", solution316.removeDuplicateLetters("abacb"));
    }
}