package me.thomas.knowledge.algorithm.leetcode;

/**
 * @author zhaoxinsheng
 * @date 2019/12/25 Wednesday
 */
public class Solution2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean over = false;
        ListNode firstNode = null, prevNode = null;
        do {
            int l1Val = 0, l2Val = 0;
            if (l1 != null) {
                l1Val = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                l2Val = l2.val;
                l2 = l2.next;
            }
            int value = l1Val + l2Val;
            if (over) {
                value += 1;
                over = false;
            }
            if (value >= 10) {
                value -= 10;
                over = true;
            }
            ListNode currentNode = new ListNode(value);
            if (prevNode != null) {
                prevNode = prevNode.next = currentNode;
            }
            if (firstNode == null) {
                prevNode = firstNode = currentNode;
            }
            // TODO: 可以通过判断l1或l2为null且over为false时将结果指针指向未结束者以提前break
            if (l1 == null && l2 == null) {
                if (over) {
                    prevNode.next = new ListNode(1);
                }
                break;
            }
        } while (true);
        return firstNode;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}


