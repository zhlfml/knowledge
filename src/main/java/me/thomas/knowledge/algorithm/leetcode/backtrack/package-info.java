package me.thomas.knowledge.algorithm.leetcode.backtrack;

/**
 * 回溯算法框架
 *
 * <pre>
 * backtrack(..) {
 *     递归出口
 *     剪枝
 *     for (选项列表) {
 *         做选择
 *         backtrack(..) // 进入下一层
 *         撤销选择
 *     }
 * }
 * </pre>
 * <p>
 * 易错及优化点：
 * 1. 通过start控制访问的起点时，进入下一层时，一定不能写成`start + 1`, 而是通过外层循环的`i + 1`。
 * 2. 对于组合和子集存在相同数字的问题，通过i = start来控制起始访问位置时，剪枝可以优化成`i > start && num[i - 1] == nums[i]`，可以避免定义selected数组。
 */