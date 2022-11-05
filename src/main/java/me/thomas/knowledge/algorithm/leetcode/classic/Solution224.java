package me.thomas.knowledge.algorithm.leetcode.classic;

import java.util.Deque;
import java.util.LinkedList;
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
     * 此题与1106. 解析布尔表达式都属于表达式解析题。
     */
    public int calculate(String s) {
        Deque<Character> deque = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            deque.offerLast(c);
        }
        return innerCalc(deque);
    }

    /**
     * 使用队列的好处有二：
     * 1. 不用处理字符串索引的问题，反正每次都取下一个。
     * 2. 便于递归调用。
     */
    int innerCalc(Deque<Character> deque) {
        char op = '+';
        int number = 0;
        Stack<Integer> stack = new Stack<>();
        while (!deque.isEmpty()) {
            char c = deque.pollFirst();
            if (c >= '0' && c <= '9') {
                number = number * 10 + (c - '0');
            }
            if (c == '(') {
                number = innerCalc(deque);
            }
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == ')' || deque.isEmpty()) { /* 不是数字字符 + 表达式结束都需要触发计算 */
                switch (op) {
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
                        System.err.println("not supported operator: " + op);
                        break;
                }
                // 重置sign和number变量
                op = c;
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
        return answer;
    }

    public static void main(String[] args) {
        Solution224 solution = new Solution224();
        System.out.println(solution.calculate("3 * (4 - 5 / 2) - 6")); // 0
        System.out.println(solution.calculate("(1+(4+5+2)-3)+(6+8)")); // 23
        System.out.println(solution.calculate("((3 + 5) * 6 + (12 - 7) * 3) / 7")); // 9
    }

}
