package me.thomas.knowledge.algorithm.leetcode.backtrack.subset;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution46.java, v 0.1 2022/9/17 17:39 xinsheng2.zhao Exp $
 */
public class Solution46 {

    /**
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> collectors = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        boolean[] selected = new boolean[nums.length];
        backtrack(collectors, stack, nums, selected);
        return collectors;
    }

    void backtrack(List<List<Integer>> collectors, Stack<Integer> track, int[] nums, boolean[] selected) {
        if (track.size() == nums.length) {
            collectors.add(new ArrayList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (selected[i]) {
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
        Solution46 solution = new Solution46();
        System.out.println(solution.permute(new int[] { 1, 2, 3 }));
    }
}
