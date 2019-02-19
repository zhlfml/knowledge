package me.thomas.knowledge.algorithm.epic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author zhaoxinsheng
 * @date 2019/02/19 Tuesday
 */
public class Exercise3Test {

    @Test
    public void walk() {
        int[][] matrix = new int[][]{{2, 3, 4, 8}, {5, 7, 9, 12}, {1, 0, 6, 10}};
        assertEquals("2,3,4,8,12,10,6,0,1,5,7,9", Exercise3.walk(matrix));
    }
}