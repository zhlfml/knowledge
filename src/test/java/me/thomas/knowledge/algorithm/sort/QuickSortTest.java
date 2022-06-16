package me.thomas.knowledge.algorithm.sort;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author zhaoxinsheng
 * @date 2019/02/13 Wednesday
 */
public class QuickSortTest {

    @Test
    public void sort_0() {
        int[] array = {7, 1, 5, 4, 8, 2};
        QuickSort.sort(array, 0, array.length - 1);
        assertArrayEquals(new int[]{1, 2, 4, 5, 7, 8}, array);
    }

    @Test
    public void sort_1() {
        int[] array = {21, 21, 20, 19, 18, 17, 16, 15, 14};
        QuickSort.sort(array, 0, array.length - 1);
        assertArrayEquals(new int[]{14, 15, 16, 17, 18, 19, 20, 21, 21}, array);
    }

    @Test
    public void sort_2() {
        int[] array = {14, 14, 15, 16, 17, 18, 19, 20, 21};
        QuickSort.sort(array, 0, array.length - 1);
        assertArrayEquals(new int[]{14, 14, 15, 16, 17, 18, 19, 20, 21}, array);
    }

    @Test
    public void sort_3() {
        int[] array = {21, 21, 19, 35, 14, 28, 37, 30, 26};
        QuickSort.sort(array, 0, array.length - 1);
        assertArrayEquals(new int[]{14, 19, 21, 21, 26, 28, 30, 35, 37}, array);
    }

}