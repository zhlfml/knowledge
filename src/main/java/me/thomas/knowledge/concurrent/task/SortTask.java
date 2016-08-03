package me.thomas.knowledge.concurrent.task;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * @author zhaoxinsheng
 * @date 7/26/16.
 * @refer http://www.ibm.com/developerworks/cn/java/j-lo-forkjoin/index.html
 */
public class SortTask extends RecursiveAction {

    private final long[] array;
    private final int lo;
    private final int hi;
    private int THRESHOLD = 0;

    public SortTask(long[] array) {
        this.array = array;
        this.lo = 0;
        this.hi = array.length - 1;
    }

    public SortTask(long[] array, int lo, int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }

    @Override
    protected void compute() {
        if (hi - lo < THRESHOLD) {
            sequentiallySort(array, lo, hi);
        }
        else {
            int pivot = partition(array, lo, hi);
            System.out.println("pivot = " + pivot + ", low = " + lo + ", high = " + hi);
            System.out.println("array" + Arrays.toString(array));
            invokeAll(new SortTask(array, lo, pivot - 1), new SortTask(array, pivot + 1, hi));
        }
    }

    private int partition(long[] array, int lo, int hi) {
        long x = array[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            if (array[j] <= x) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, hi);
        return i + 1;
    }

    private void swap(long[] array, int i, int j) {
        if (i != j) {
            long temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private void sequentiallySort(long[] array, int lo, int hi) {
        Arrays.sort(array, lo, hi + 1);
    }
}
