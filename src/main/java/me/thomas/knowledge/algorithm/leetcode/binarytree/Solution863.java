package me.thomas.knowledge.algorithm.leetcode.binarytree;

import me.thomas.knowledge.algorithm.leetcode.TreeNode;

import java.util.*;

/**
 * 863. 二叉树中所有距离为 K 的结点
 * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 k 。
 * <p>
 * 返回到目标结点 target 距离为 k 的所有结点的值的列表。 答案可以以 任何顺序 返回。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution863.java, v 0.1 2022/10/26 20:59 xinsheng2.zhao Exp $
 */
public class Solution863 {

    List<Integer>           answer;
    Map<TreeNode, TreeNode> parentMap;
    Set<TreeNode>           visited;

    /**
     * 思路：先找到target节点以及其父节点。再分别收集target节点的k层子孙节点，parent令一个孩子的k-2层子孙节点。但是这个想法没有考虑到父节点的父节点其距离为2。
     * 所以正确的思路只能将二叉树树转成图来遍历，如何转成图呢，借助Map<node, parent>保存。
     * 需要注意的是，需要保存遍历过的节点避免重复遍历。
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        answer = new ArrayList<>();
        parentMap = new HashMap<>();
        visited = new HashSet<>();
        findParent(root, null);
        collect(target, k);
        return answer;
    }

    void findParent(TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }
        parentMap.put(node, parent);
        findParent(node.left, node);
        findParent(node.right, node);
    }

    void collect(TreeNode node, int k) {
        if (node == null || k < 0) {
            return;
        }
        if (visited.contains(node)) {
            return;
        }
        if (k == 0) {
            answer.add(node.val);
        }
        visited.add(node);
        collect(parentMap.get(node), k - 1);
        collect(node.left, k - 1);
        collect(node.right, k - 1);
    }
}
