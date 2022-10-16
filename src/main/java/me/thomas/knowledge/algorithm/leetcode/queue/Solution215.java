package me.thomas.knowledge.algorithm.leetcode.queue;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution219.java, v 0.1 2022/10/16 17:19 xinsheng2.zhao Exp $
 */
public class Solution215 {

    final Random random = new Random();

    public int findKthLargest(int[] nums, int k) {
        return findKthLargest_pq(nums, k);
    }

    /**
     * 思路：构建一个大小为k的最小堆。
     * 循环数组，将数组中的每个元素放入最小堆中，只要放入后堆的大小超出了k，就移除堆顶的元素。
     */
    int findKthLargest_pq(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    /**
     * 思路：借鉴快速排序的思路。
     */
    int findKthLargest_quickSelect(int[] nums, int k) {
        int kOffset = k - 1;
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int pos = partition(nums, low, high);
            if (pos < kOffset) {
                low = pos + 1;
            } else if (pos > kOffset) {
                high = pos - 1;
            } else {
                return nums[pos];
            }
        }
        return -1;
    }

    /**
     * 优化方案可以参考c语言版本的quick.c
     */
    int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int low = left + 1, high = right;
        while (low <= high) { /* 为什么循环条件是`<=`？考虑[1,2]本来就有序的列子，如果循环条件是`<`那么循环体不会执行，直接跳转到`swap(nums, left, high)`处，数组会变成[2,1]，即本来有序执行后却无序了。*/
            while (low <= high && nums[low] >= pivot) {
                low++;
            }
            while (low <= high && nums[high] <= pivot) {
                high--;
            }
            if (low < high) {
                // swap low， high
                swap(nums, low, high);
                low++; // 交换之后必定符合条件，所以直接比较下一个数据
                high--;
            }
        }
        swap(nums, left, high); /* 为什么与high交换呢？因为循环停止时`nums[low] < pivot && nums[high] > pivot`，这里又是逆序排序，所以需要将大的数与left处的数调换。 */
        return high;
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        Solution215 solution215 = new Solution215();
        System.out.println(solution215.findKthLargest_quickSelect(new int[] { 1, 4, 3, 2, 5, 6 }, 2));
    }
}
