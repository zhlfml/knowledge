package me.thomas.knowledge.algorithm.leetcode.classic;

import java.util.Stack;

/**
 * 224. 基本计算器
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * <p>
 * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution224.java, v 0.1 2022/7/20 16:57 xinsheng2.zhao Exp $
 */
public class Solution224 {

    /**
     * 输入：s = "(1+(4+5+2)-3)+(6+8)"
     * 输出：23
     */
    public int calculate(String s) {
        return innerCalc(s, 0)[0];
    }

    int[] innerCalc(String expr, int start) {
        char sign = '+';
        int number = 0;
        Stack<Integer> stack = new Stack<>();
        int i;
        for (i = start; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            }
            if (c == '(') {
                int[] subExpr = innerCalc(expr, i + 1);
                number = subExpr[0];
                i = subExpr[1] + 1; /* go to the next index */
                if (i < expr.length()) { /* read the next character if possible -- the right parentheses maybe at the end of expression. */
                    c = expr.charAt(i);
                }
            }
            if ((!Character.isDigit(c) && !Character.isSpaceChar(c)) || i >= expr.length() - 1) {
                switch (sign) {
                    case '+':
                        stack.push(number);
                        break;
                    case '-':
                        stack.push(-number);
                        break;
                    case '*':
                        stack.push(stack.pop() * number);
                        break;
                    case '/':
                        stack.push(stack.pop() / number);
                        break;
                    default:
                        System.err.println("not supported operator: " + sign);
                        break;
                }
                // 重置sign和number变量
                sign = c;
                number = 0;
            }
            if (c == ')') {
                break;
            }
        }
        int answer = 0;
        while (!stack.isEmpty()) {
            answer += stack.pop();
        }
        return new int[] { answer, i };
    }

    public static void main(String[] args) {
        Solution224 solution = new Solution224();
        System.out.println(solution.calculate("3 * (4 - 5 / 2) - 6"));
        System.out.println(solution.calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(solution.calculate("((3 + 5) * 6 + (12 - 7) * 3) / 7"));
    }

}
