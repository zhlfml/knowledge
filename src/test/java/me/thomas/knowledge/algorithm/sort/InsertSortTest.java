package me.thomas.knowledge.algorithm.sort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author zhaoxinsheng
 * @date 2019/02/13 Wednesday
 */
public class InsertSortTest {

    @Test
    public void sort_1() {
        int[] array = {21, 21, 20, 19, 18, 17, 16, 15, 14};
        InsertSort.sort(array);
        assertArrayEquals(new int[]{14, 15, 16, 17, 18, 19, 20, 21, 21}, array);
    }

    @Test
    public void sort_2() {
        int[] array = {14, 14, 15, 16, 17, 18, 19, 20, 21};
        InsertSort.sort(array);
        assertArrayEquals(new int[]{14, 14, 15, 16, 17, 18, 19, 20, 21}, array);
    }

    @Test
    public void sort_3() {
        int[] array = {21, 21, 19, 35, 14, 28, 37, 30, 26};
        InsertSort.sort(array);
        assertArrayEquals(new int[]{14, 19, 21, 21, 26, 28, 30, 35, 37}, array);
    }
}