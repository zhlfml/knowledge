package me.thomas.knowledge.algorithm.leetcode.classic;

import java.util.Stack;

/**
 * 921. 使括号有效的最少添加
 * 只有满足下面几点之一，括号字符串才是有效的：
 * <p>
 * 它是一个空字符串，或者
 * 它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
 * 它可以被写作 (A)，其中 A 是有效字符串。
 * 给定一个括号字符串 s ，移动N次，你就可以在字符串的任何位置插入一个括号。
 * <p>
 * 例如，如果 s = "()))" ，你可以插入一个开始括号为 "(()))" 或结束括号为 "())))" 。
 * 返回 为使结果字符串 s 有效而必须添加的最少括号数。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution921.java, v 0.1 2022/7/23 11:42 xinsheng2.zhao Exp $
 */
public class Solution921 {

    /**
     * 思路：使用两个栈，分别保存没能匹配上左括号和右括号的另一半括号。
     * 如果是左括号，直接放入leftStack。
     * 如果是右括号，先尝试匹配leftStack，匹配不上放入rightStack。
     */
    public int minAddToMakeValid(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Stack<Character> left = new Stack<>(); // 放入不能匹配右括号的左括号
        Stack<Character> right = new Stack<>(); // 放入不能匹配左括号的右括号
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(') {
                left.push(c);
            } else if (c == ')') {
                if (left.isEmpty()) {
                    right.push(c);
                } else {
                    left.pop();
                }
            }
        }
        return left.size() + right.size();
    }
}
