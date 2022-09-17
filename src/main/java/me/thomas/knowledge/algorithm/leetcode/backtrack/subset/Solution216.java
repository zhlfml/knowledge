package me.thomas.knowledge.algorithm.leetcode.backtrack.subset;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 * <p>
 * 只使用数字1到9
 * 每个数字 最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution216.java, v 0.1 2022/9/17 14:41 xinsheng2.zhao Exp $
 */
public class Solution216 {

    /**
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     * 解释:
     * 1 + 2 + 6 = 9
     * 1 + 3 + 5 = 9
     * 2 + 3 + 4 = 9
     * 没有其他符合的组合了。
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> collects = new ArrayList<>();
        Stack<Integer> track = new Stack<>();
        backtrack(collects, track, k, 1, n);
        return collects;
    }

    /**
     * 思路：从[start, 9]，取k个数使得k数之和为left。每递归一次将问题的规模缩小一点。
     *
     * @param k     取数的数量
     * @param start 取数范围：[start, 9]
     * @param left  拼凑出left
     */
    void backtrack(List<List<Integer>> collects, Stack<Integer> track, int k, int start, int left) {
        if (k == 0 && left == 0) { /* 递归出口 */
            collects.add(new ArrayList<>(track));
            return;
        }
        if (start > left) { /* 剪枝 */
            return;
        }
        for (int i = start; i <= 9; i++) {
            track.push(i);
            backtrack(collects, track, k - 1, i + 1, left - i);
            track.pop();
        }
    }

    public static void main(String[] args) {
        Solution216 solution = new Solution216();
        System.out.println(solution.combinationSum3(3, 9));
    }
}
