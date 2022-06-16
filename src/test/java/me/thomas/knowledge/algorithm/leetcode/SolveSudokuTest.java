package me.thomas.knowledge.algorithm.leetcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * LY.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
public class SolveSudokuTest {

    SolveSudoku sudoku;

    @Before
    public void setUp() throws Exception {
        sudoku = new SolveSudoku();
    }

    @Test
    public void solveSudoku() {
        char board[][] = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        sudoku.solveSudoku(board);
    }
}