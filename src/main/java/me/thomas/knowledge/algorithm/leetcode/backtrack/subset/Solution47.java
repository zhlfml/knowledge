package me.thomas.knowledge.algorithm.leetcode.backtrack.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution47.java, v 0.1 2022/9/17 17:55 xinsheng2.zhao Exp $
 */
public class Solution47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> collectors = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        boolean[] selected = new boolean[nums.length];
        Arrays.sort(nums); /* 排序后方便处理相同的数字，前面一个数字没有选择的情况下后一个数字不可以选择。 */
        backtrack(collectors, stack, nums, selected);
        return collectors;
    }

    /**
     * 输入：nums = [1,1,2]
     * 输出：
     * [[1,1,2], [1,2,1], [2,1,1]]
     */
    void backtrack(List<List<Integer>> collectors, Stack<Integer> track, int[] nums, boolean[] selected) {
        if (track.size() == nums.length) {
            collectors.add(new ArrayList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (selected[i]) { /* 与排列1相同，最基本的case -- 已被选择的元素不能再次被选择 */
                continue;
            }
            if (i > 0 && nums[i - 1] == nums[i] && !selected[i - 1]) { /* 重复元素的特殊case -- 与前面一个数相同时，前面的数只有再被选择时才能选中后面的数 */
                continue;
            }
            track.push(nums[i]);
            selected[i] = true;
            backtrack(collectors, track, nums, selected);
            track.pop();
            selected[i] = false;
        }
    }

    public static void main(String[] args) {
        Solution47 solution = new Solution47();
        System.out.println(solution.permuteUnique(new int[] { 1, 1, 2 }));
    }
}
