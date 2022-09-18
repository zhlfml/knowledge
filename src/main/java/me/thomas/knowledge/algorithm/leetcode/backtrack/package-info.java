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
 */