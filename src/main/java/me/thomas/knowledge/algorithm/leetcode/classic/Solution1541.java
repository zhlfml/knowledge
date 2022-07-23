package me.thomas.knowledge.algorithm.leetcode.classic;

/**
 * 1541. 平衡括号字符串的最少插入次数
 * 给你一个括号字符串 s ，它只包含字符 '(' 和 ')' 。一个括号字符串被称为平衡的当它满足：
 * <p>
 * 任何左括号 '(' 必须对应两个连续的右括号 '))' 。
 * 左括号 '(' 必须在对应的连续两个右括号 '))' 之前。
 * 比方说 "())"， "())(())))" 和 "(())())))" 都是平衡的， ")()"， "()))" 和 "(()))" 都是不平衡的。
 * <p>
 * 你可以在任意位置插入字符 '(' 和 ')' 使字符串平衡。
 * <p>
 * 请你返回让 s 平衡的最少插入次数。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution1541.java, v 0.1 2022/7/23 14:04 xinsheng2.zhao Exp $
 */
public class Solution1541 {

    /**
     * 思路：
     * insert: 已经插入的括号的数量（左右括号都有可能）
     * need: 还需要的右括号的数量
     */
    public int minInsertions(String s) {
        int insert = 0, need = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                need += 2;
                // 难点：遇到左括号之后，之前欠的账必须完结
                if (need % 2 == 1) {
                    insert += 1; // 插入一个右括号
                    need--; // 对右括号的需求减一
                }
            } else if (c == ')') {
                need--;
                if (need < 0) {
                    insert += 1; // 插入一个左括号
                    need = 1; // 需要另外一个右括号
                }
            }
        }
        return insert + need;
    }

    public static void main(String[] args) {
        Solution1541 solution = new Solution1541();
        System.out.println(solution.minInsertions(")()(")); // 5
        System.out.println(solution.minInsertions(")()))")); // 4
        System.out.println(solution.minInsertions("))()()))")); // 4
    }
}
