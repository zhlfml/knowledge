package me.thomas.knowledge.algorithm.leetcode.math;

import me.thomas.knowledge.algorithm.leetcode.ListNode;

import java.util.Random;

/**
 * 给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。
 * <p>
 * 实现 Solution 类：
 * <p>
 * Solution(ListNode head) 使用整数数组初始化对象。
 * int getRandom() 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution382.java, v 0.1 2022/6/29 23:09 xinsheng2.zhao Exp $
 */
public class Solution382 {

    private final ListNode head;

    private final Random random;

    public Solution382(ListNode head) {
        this.head = head;
        this.random = new Random(System.nanoTime());
    }

    public int getRandom() {
        int answer = head.val;
        int index = 0;
        ListNode cur = head;
        while (cur != null) {
            int randomVal = random.nextInt(++index);
            if (randomVal == 0) {
                answer = cur.val;
            }
            cur = cur.next;
        }
        return answer;
    }
}
