package me.thomas.knowledge.algorithm.leetcode.binarytree;

import me.thomas.knowledge.algorithm.leetcode.TreeNode;

import java.util.*;

/**
 * 637. 二叉树的层平均值
 * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10^-5 以内的答案可以被接受。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution637.java, v 0.1 2022/10/13 11:44 xinsheng2.zhao Exp $
 */
public class Solution637 {

    /**
     * 思路：通过queue实现二叉树的层序遍历
     */
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Double> answer = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0.0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            answer.add(sum / size);
        }
        return answer;
    }

}
