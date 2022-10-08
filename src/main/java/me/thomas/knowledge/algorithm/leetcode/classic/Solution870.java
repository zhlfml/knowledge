package me.thomas.knowledge.algorithm.leetcode.classic;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 870. 优势洗牌
 * 给定两个大小相等的数组 nums1 和 nums2，nums1 相对于 nums2 的优势可以用满足 nums1[i] > nums2[i] 的索引 i 的数目来描述。
 * <p>
 * 返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution870.java, v 0.1 2022/10/8 21:01 xinsheng2.zhao Exp $
 */
public class Solution870 {

    /**
     * 输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
     * 输出：[2,11,7,15]
     * <p>
     * 思路：将nums2，按照从小到大的顺序排序，为了维护nums2原数组的顺序，需要将nums2扩展成二维数组后再排序。
     * nums2排序之后，通过二维
     */
    public int[] advantageCount(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            throw new IllegalArgumentException("neither nums1 nor nums2 is null");
        }
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 != n2) {
            throw new IllegalArgumentException("n1 != n2");
        }

        Arrays.sort(nums1);
        int[][] newNums2 = new int[n2][3];
        for (int i = 0; i < nums2.length; i++) {
            newNums2[i][0] = i;
            newNums2[i][1] = nums2[i];
            newNums2[i][2] = -1; /* 表示未设置过值，非必要 */
        }
        Arrays.sort(newNums2, Comparator.comparingInt(a -> a[1])); /* 按照原始值排序 */

        // 依次遍历排序后的nums1中的元素，如果大于将要放置的newNums2的元素，则放入，否则放入newNums2的末尾。
        int head = 0, tail = n2 - 1; /* 定义两个指针指明newNums2中前后可以安排的当前位置 */
        for (int num : nums1) {
            if (num > newNums2[head][1]) {
                newNums2[head++][2] = num;
            } else {
                newNums2[tail--][2] = num;
            }
        }
        // 还原nums2的原来的排序
        Arrays.sort(newNums2, Comparator.comparingInt(a -> a[0])); /* 按照下标排序 */
        return Arrays.stream(newNums2).mapToInt(a -> a[2]).toArray();
    }
}
