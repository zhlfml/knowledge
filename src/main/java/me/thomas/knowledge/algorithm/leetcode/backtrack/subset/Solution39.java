package me.thomas.knowledge.algorithm.leetcode.backtrack.subset;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 39. 组合总和
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * <p>
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * <p>
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution39.java, v 0.1 2022/9/15 21:30 xinsheng2.zhao Exp $
 */
public class Solution39 {

    /**
     * 输入：candidates = [2,3,6,7], target = 7
     * 输出：[[2,2,3],[7]]
     * 解释：
     * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
     * 7 也是一个候选， 7 = 7 。
     * 仅有这两种组合。
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> collects = new ArrayList<>(150);
        Stack<Integer> track = new Stack<>();
        backtrack(collects, track, candidates, 0, target);
        return collects;
    }

    /**
     * 思路：collect用于收集数字，每次都收集当前的数字，直到满足条件时放入collects或剩下的数小于当前数时回溯一步再继续收集数字。
     *
     * @param collects   用于收集所有合法的结果
     * @param track      收集某一次的结果，当符合条件时（left == 0）放入collects
     * @param candidates 所有数字候选项
     * @param start      当前取数的位置
     * @param left       满足要求的剩余的数
     */
    void backtrack(List<List<Integer>> collects, Stack<Integer> track, int[] candidates, int start, int left) {
        if (left <= 0) {
            if (left == 0) {
                collects.add(new ArrayList<>(track));
            }
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // 做选择
            track.push(candidates[i]);
            System.out.println("push: " + candidates[i]);
            backtrack(collects, track, candidates, i, left - candidates[i]);
            // 撤销选择
            System.out.println("pop: " + track.pop());
        }
    }

    public static void main(String[] args) {
        Solution39 solution = new Solution39();
        List<List<Integer>> result = solution.combinationSum(new int[] { 2, 3, 6, 7 }, 7);
        System.out.println(result.toString());
    }
}

