package me.thomas.knowledge.algorithm.leetcode.doublepointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * <p>
 * 你返回所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution15.java, v 0.1 2022/10/9 21:19 xinsheng2.zhao Exp $
 */
public class Solution15 {

    /**
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * <p>
     * 思路：首先将数组排序，定义三个指针，i,j,k。在外层循环中k--，内层使用双指针i = 0, j = k - 1遍历符合条件的三元组。
     * 时间复杂度：O(n) = n^2
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        List<List<Integer>> answer = new ArrayList<>();
        int k = nums.length - 1;
        while (k >= 0) {
            if (nums[k] < 0) { /* 左边全是负数，已经无解，可以提前退出 */
                return answer;
            }
            int i = 0, j = k - 1;
            while (i < j) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum < 0) {
                    do {
                        i++;
                    } while (i < j && nums[i - 1] == nums[i]);
                } else if (sum > 0) {
                    do {
                        j--;
                    } while (i < j && nums[j + 1] == nums[j]);
                } else { // 找到了一个答案
                    answer.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    // 继续查找，需要同时向左滑动i，向右滑动j
                    do {
                        i++;
                    } while (i < j && nums[i - 1] == nums[i]);
                    do {
                        j--;
                    } while (i < j && nums[j + 1] == nums[j]);
                }
            }
            do {
                k--;
            } while (k >= 0 && nums[k + 1] == nums[k]);
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution15 solution15 = new Solution15();
        System.out.println(solution15.threeSum(new int[] { 0, 0, 0, 0, 0 }).toString());
    }
}
