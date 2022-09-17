package me.thomas.knowledge.algorithm.leetcode.backtrack.subset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * <p>
 * 你可以按 任何顺序 返回答案。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution77.java, v 0.1 2022/9/17 15:08 xinsheng2.zhao Exp $
 */
public class Solution77 {

    /**
     * 输入：n = 4, k = 2
     * 输出：
     * [[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]
     */
    public List<List<Integer>> combine(int n, int k) {
        if (n < k) {
            return Collections.emptyList();
        }
        List<List<Integer>> collectors = new ArrayList<>();
        Stack<Integer> track = new Stack<>();
        backtrack(collectors, track, 1, n, k);
        return collectors;
    }

    /**
     * 思路：在[start, end]之间选择k个数，每递归一层缩小取数范围和降低取数的数量
     *
     * @param collectors 收集器
     * @param track      追踪器
     * @param start      取数起点，包含
     * @param end        取数终点，包含
     * @param k          取数的数量
     */
    void backtrack(List<List<Integer>> collectors, Stack<Integer> track, int start, int end, int k) {
        if (k == 0) {
            collectors.add(new ArrayList<>(track));
            return;
        }
        if (end - start + 1 < k) { /* 数的范围还没有取数的数量大，可以直接剪枝了 */
            return;
        }
        for (int i = start; i <= end; i++) {
            track.push(i);
            backtrack(collectors, track, i + 1, end, k - 1);
            track.pop();
        }
    }

    public static void main(String[] args) {
        Solution77 solution = new Solution77();
        System.out.println(solution.combine(4, 2));
    }
}
