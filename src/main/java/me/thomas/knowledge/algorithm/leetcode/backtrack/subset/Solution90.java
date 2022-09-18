package me.thomas.knowledge.algorithm.leetcode.backtrack.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 90. 子集 II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution90.java, v 0.1 2022/9/18 11:40 xinsheng2.zhao Exp $
 */
public class Solution90 {

    /**
     * 输入：nums = [1,2,2]
     * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> collectors = new ArrayList<>();
        Stack<Integer> track = new Stack<>();
        Arrays.sort(nums); /* 比78.子集多了排序 */
        backtrack(collectors, track, nums, 0);
        return collectors;
    }

    /**
     * 思路：与78.子集相比，这里多了重复的数字，只需额外控制下存在相同的数时，前一个未选择的前提下后一个不可以选择就可以，因此需要额外引入selected数组。
     */
    void backtrack(List<List<Integer>> collectors, Stack<Integer> track, int[] nums, int start) {
        collectors.add(new ArrayList<>(track));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i - 1] == nums[i]) { /* 比78.子集多了这一步 */
                continue;
            }
            track.push(nums[i]);
            backtrack(collectors, track, nums, i + 1);
            track.pop();
        }
    }

    public static void main(String[] args) {
        Solution90 solution = new Solution90();
        System.out.println(solution.subsetsWithDup(new int[] { 1, 2, 2 }));
    }
}
