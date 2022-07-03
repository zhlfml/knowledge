package me.thomas.knowledge.algorithm.leetcode;

/**
 * ListNode
 *
 * @author xinsheng2.zhao
 * @version Id: ListNode.java, v 0.1 2022/6/29 23:10 xinsheng2.zhao Exp $
 */
public class ListNode {

    public int      val;
    public ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
