package me.thomas.knowledge.algorithm.leetcode.binarysearch;

import java.util.Arrays;

/**
 * 354. 俄罗斯套娃信封问题
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 * <p>
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * <p>
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * <p>
 * 注意：不允许旋转信封。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution354.java, v 0.1 2022/8/4 21:18 xinsheng2.zhao Exp $
 */
public class Solution354 {

    /**
     * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
     * 输出：3
     * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
     */
    public int maxEnvelopes(int[][] envelopes) {
        // 按照宽度升序、高度降序排列后可以降成一维数组，变成与300. 最长递增子序列一样的题目。
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] < b[0] ? -1 : 1;
            } else {
                return a[1] < b[1] ? 11 : -1;
            }
        });

        int[] nums = new int[envelopes.length];
        // 只收集套娃盒子的高度
        for (int i = 0; i < envelopes.length; i++) {
            nums[i] = envelopes[i][1];
        }
        /*
         * 放置牌的规则为：小牌可以压到打牌上面，如果一张牌足够大无法放到现在的牌堆上则另起一堆。
         * 如果存在多个可放置的堆则放在最左边的堆。手里没牌时，牌堆的数量就是最大递增子序列的长度。
         */
        int[] heaps = new int[nums.length];
        int heapSize = 1;
        heaps[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 二分查找小于牌堆顶数字的最右侧堆。
            int index = leftBound(heaps, heapSize, nums[i]);
            heaps[index] = nums[i]; /* 将牌覆盖到合适的牌堆上 */
            heapSize = Math.max(heapSize, index + 1);
        }
        return heapSize;
    }

    /**
     * 寻找大于target的最小数的下标
     */
    int leftBound(int[] nums, int numsSize, int target) {
        int low = 0, high = numsSize;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        Solution354 solution = new Solution354();
        System.out.println(solution.maxEnvelopes(
                new int[][] { { 15, 8 }, { 2, 20 }, { 2, 14 }, { 4, 17 }, { 8, 19 }, { 8, 9 }, { 5, 7 }, { 11, 19 }, { 8, 11 }, { 13, 11 }, { 2, 13 }, { 11, 19 }, { 8, 11 },
                              { 13, 11 }, { 2, 13 }, { 11, 19 }, { 16, 1 }, { 18, 13 }, { 14, 17 }, { 18, 19 } }));
    }
}
