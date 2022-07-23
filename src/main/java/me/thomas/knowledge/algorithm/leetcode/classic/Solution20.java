package me.thomas.knowledge.algorithm.leetcode.classic;

import java.util.Stack;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution20.java, v 0.1 2022/7/23 11:20 xinsheng2.zhao Exp $
 */
public class Solution20 {

    /**
     * 思路：通过栈来判断是否正确闭合
     */
    public boolean isValid(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        // 必须是双数
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            // 左边的括号，直接放入stack中，右边的括号需要弹出栈与之验证是否匹配。
            if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                boolean match = false;
                char left = stack.pop();
                switch (c) {
                    case ')':
                        match = left == '(';
                        break;
                    case ']':
                        match = left == '[';
                        break;
                    case '}':
                        match = left == '{';
                        break;
                    default:
                        System.err.println("not support: " + c);
                }
                if (!match) {
                    return false;
                }
                continue;
            }
            stack.push(c);
        }
        // 只有stack空了才能说明全部匹配上了。
        return stack.isEmpty();
    }
}
