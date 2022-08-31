package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.game;

import me.thomas.knowledge.algorithm.leetcode.TreeNode;

/**
 * 337. 打家劫舍 III
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * <p>
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * <p>
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution337.java, v 0.1 2022/8/31 21:04 xinsheng2.zhao Exp $
 */
public class Solution337 {

    public int rob(TreeNode root) {
        Benefit benefit = dfs(root);
        return Math.max(benefit.steal, benefit.giveUp);
    }

    /**
     * 思路：大前提后序遍历，因为当前节点是否盗取取决于子节点的结果。
     * 遍历过程中对每个节点做选择：盗取 or 不盗取
     * 如果选择盗取那么收益的计算比较简单：拿当前节点的值加上左右子节点不盗取的收益就是盗取当前节点的收益。
     * 如果选择不盗取当前节点，那么怎么计算收益呢？其实也很简单 -- 分别判断左右两个子节点盗取和不盗取的收益，选择收益大的加起来即是不盗取当前节点的收益。
     * 分析下来的结论是：当前节点是否盗取只和当前节点和左右子节点有关，只需计算比较盗取和不盗取的最大收益即可。
     */
    Benefit dfs(TreeNode node) {
        if (node == null) {
            return new Benefit(0, 0);
        }
        Benefit left = dfs(node.left);
        Benefit right = dfs(node.right);
        return new Benefit(node.val + left.giveUp + right.giveUp, Math.max(left.steal, left.giveUp) + Math.max(right.steal, right.giveUp));
    }

    static class Benefit {
        int steal; /* 偷当前节点的收益 */
        int giveUp; /* 不偷当前节点的收益 */

        public Benefit(int steal, int giveUp) {
            this.steal = steal;
            this.giveUp = giveUp;
        }
    }
}
