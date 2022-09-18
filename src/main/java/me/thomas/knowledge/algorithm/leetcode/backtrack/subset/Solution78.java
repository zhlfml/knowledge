package me.thomas.knowledge.algorithm.leetcode.backtrack.subset;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution78.java, v 0.1 2022/9/17 18:12 xinsheng2.zhao Exp $
 */
public class Solution78 {

    /**
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> collectors = new ArrayList<>();
        Stack<Integer> track = new Stack<>();
        subsets_backtrack(collectors, track, nums, 0);
        return collectors;
        // return subsets_bitState(nums);
    }

    /**
     * 思路：与78.组合是在递归的前序位置收集了固定层次的节点，子集的思路与组合相同，不同之处是每层都必须收集。
     */
    void subsets_backtrack(List<List<Integer>> collectors, Stack<Integer> track, int[] nums, int start) {
        collectors.add(new ArrayList<>(track)); // 搜集不限定层次
        for (int i = start; i < nums.length; i++) {
            track.push(nums[i]);
            subsets_backtrack(collectors, track, nums, i + 1);
            track.pop();
        }
    }

    /**
     * 思路：状态压缩 -- 定义二进制比特位与nums的长度n一致，得到最大数2^n，遍历[0, 2^n)之间的数，检查bit位，如果是1选中对应位置的数。
     */
    List<List<Integer>> subsets_bitState(int[] nums) {
        int n = (int) Math.pow(2, nums.length);
        List<List<Integer>> collectors = new ArrayList<>(n - 1);
        for (int i = 0; i < n; i++) {
            List<Integer> collector = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) > 0) { /* 收集对应bit位为1的数 */
                    collector.add(nums[j]);
                }
            }
            collectors.add(collector);
        }
        return collectors;
    }

    public static void main(String[] args) {
        Solution78 solution = new Solution78();
        System.out.println(solution.subsets(new int[] { 1, 2, 3 }));
    }
}
