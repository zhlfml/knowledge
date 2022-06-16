package me.thomas.knowledge.algorithm.dp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * LY.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
public class LongestFibSubSeqTest {

    private LongestFibSubSeq longestFibSubSeq;

    @Before
    public void setUp() {
        longestFibSubSeq = new LongestFibSubSeq();
    }

    @Test
    public void lenLongestFibSubseq() {
        int[] nums = {2,4,7,8,9,10,14,15,18,23,32,50};
        assertEquals(5, longestFibSubSeq.lenLongestFibSubseq(nums));
    }
}