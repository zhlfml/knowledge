package me.thomas.knowledge.algorithm.sort;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by thomas on 6/10/14.
 */
public class MergeSortTest {

    int a[] = new int[] {1,3,5,7,9,11,13,15};
    int b[] = new int[] {0,2,4,6,8};
    int mass[] = new int[] {4,3,6,9,1,15,11,0,2,5,8,13,7};

    MergeSort sort = null;

    @Before
    public void setUp() throws Exception {
        sort = new MergeSort();
    }

    @Test
    public void testMergeSort() throws Exception {
        int[] result = sort.sort(mass);

        assertArrayEquals(new int[] {0,1,2,3,4,5,6,7,8,9,11,13,15}, result);
    }

    @Test
    public void testMergeArray() throws Exception {
        int[] result = sort.mergeArray(a, b);

        assertArrayEquals(new int[] {0,1,2,3,4,5,6,7,8,9,11,13,15}, result);
    }
}
