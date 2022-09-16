package me.thomas.knowledge.algorithm.leetcode.backtrack.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 40. 组合总和 II
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * <p>
 * 注意：解集不能包含重复的组合。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution40.java, v 0.1 2022/9/15 21:30 xinsheng2.zhao Exp $
 */
public class Solution40 {

    /**
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 输出: [[1,2,2],[5]]
     * <p>
     * 思路：相同的数，前面一个没有选择的情况下，后一个不能选择。
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> collects = new ArrayList<>();
        Stack<Integer> track = new Stack<>();
        Arrays.sort(candidates); /* 先排序，方便后续处理相同的数字 */
        boolean[] selected = new boolean[candidates.length];
        backtrack(collects, track, candidates, 0, selected, target);
        return collects;
    }

    void backtrack(List<List<Integer>> collects, Stack<Integer> track, int[] candidates, int start, boolean[] selected, int left) {
        if (left == 0) {
            collects.add(new ArrayList<>(track));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > 0 && candidates[i - 1] == candidates[i] && !selected[i - 1]) {
                // 要点：相同的数，前面一个没有选择的情况下，后一个不能选择。
                continue;
            }
            if (left - candidates[i] < 0) { /* 优化：因为已经排序了，当前数都超出了target后面的数字就更不可能了，所以提前退出。 */
                break;
            }
            track.push(candidates[i]);
            selected[i] = true;
            backtrack(collects, track, candidates, start + 1, selected, left - candidates[i]);
            track.pop();
            selected[i] = false;
        }
    }

    public static void main(String[] args) {
        Solution40 solution = new Solution40();
        System.out.println(solution.combinationSum2(new int[] { 10, 1, 2, 7, 6, 1, 5 }, 8));
    }
}
