package me.thomas.knowledge.algorithm.epic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author zhaoxinsheng
 * @date 2019/02/19 Tuesday
 */
public class Exercise3Test {

    @Test(expected = IllegalArgumentException.class)
    public void traverse_null() {
        Exercise3.traverse(null);
    }

    @Test
    public void traverse_00() {
        int[][] matrix = new int[0][0];
        assertEquals("", Exercise3.traverse(matrix));
    }

    @Test
    public void traverse_11() {
        int[][] matrix = new int[][]{
                {2}
        };
        assertEquals("2", Exercise3.traverse(matrix));
    }

    @Test
    public void traverse_21() {
        int[][] matrix = new int[][]{
                {2},
                {5}
        };
        assertEquals("2,5", Exercise3.traverse(matrix));
    }

    @Test
    public void traverse_23() {
        int[][] matrix = new int[][]{
                {2, 3, 4},
                {5, 7, 9}
        };
        assertEquals("2,3,4,9,7,5", Exercise3.traverse(matrix));
    }

    @Test
    public void traverse_24() {
        int[][] matrix = new int[][]{
                {2, 3, 4, 8},
                {5, 7, 9, 12}
        };
        assertEquals("2,3,4,8,12,9,7,5", Exercise3.traverse(matrix));
    }

    @Test
    public void traverse_33() {
        int[][] matrix = new int[][]{
                {2, 3, 4},
                {5, 7, 9},
                {1, 0, 6}
        };
        assertEquals("2,3,4,9,6,0,1,5,7", Exercise3.traverse(matrix));
    }

    @Test
    public void traverse_34() {
        int[][] matrix = new int[][]{
                {2, 3, 4, 8},
                {5, 7, 9, 12},
                {1, 0, 6, 10}
        };
        assertEquals("2,3,4,8,12,10,6,0,1,5,7,9", Exercise3.traverse(matrix));
    }

    @Test
    public void traverse_44() {
        int[][] matrix = new int[][]{
                {2, 3, 4, 8},
                {5, 7, 9, 12},
                {1, 0, 6, 10},
                {11, 13, 16, 17}
        };
        assertEquals("2,3,4,8,12,10,17,16,13,11,1,5,7,9,6,0", Exercise3.traverse(matrix));
    }

    @Test
    public void traverse_54() {
        int[][] matrix = new int[][]{
                {2, 3, 4, 8},
                {5, 7, 9, 12},
                {1, 0, 6, 10},
                {11, 13, 16, 17},
                {14, 15, 18, 19}
        };
        assertEquals("2,3,4,8,12,10,17,19,18,15,14,11,1,5,7,9,6,16,13,0", Exercise3.traverse(matrix));
    }
}