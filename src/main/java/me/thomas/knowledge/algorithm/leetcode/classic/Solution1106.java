package me.thomas.knowledge.algorithm.leetcode.classic;

import java.util.Stack;

/**
 * 1106. 解析布尔表达式
 * 给你一个以字符串形式表述的 布尔表达式（boolean） expression，返回该式的运算结果。
 * <p>
 * 有效的表达式需遵循以下约定：
 * <p>
 * "t"，运算结果为 True
 * "f"，运算结果为 False
 * "!(expr)"，运算过程为对内部表达式 expr 进行逻辑 非的运算（NOT）
 * "&(expr1,expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 与的运算（AND）
 * "|(expr1,expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 或的运算（OR）
 *
 * @author xinsheng2.zhao
 * @version Id: Solution1106.java, v 0.1 2022/11/5 21:13 xinsheng2.zhao Exp $
 */
public class Solution1106 {

    /**
     * 学习了官网的思路，没有看官网的代码。
     * 思路：使用栈。遇到逗号，忽略；遇到右括号，弹出栈中的字符直到遇到左括号，统计弹出的f和t的数量并做运算后再入栈。
     * 关键点：遇到右括号不要放入栈中，反而是开始执行逻辑运算的触发器。
     * 此题与224. 基本计算器都属于表达式解析题。
     */
    public boolean parseBoolExpr(String expression) {
        Stack<Character> stack = new Stack<>();
        for (char c : expression.toCharArray()) {
            if (c == ',') {
                continue;
            }
            if (c == ')') {
                resolve(stack);
            } else {
                stack.push(c);
            }
        }
        return stack.pop() == 't';
    }

    void resolve(Stack<Character> stack) {
        int tCount = 0, fCount = 0;
        char c;
        while (!stack.isEmpty() && (c = stack.pop()) != '(') {
            if (c == 't') {
                tCount++;
            } else if (c == 'f') {
                fCount++;
            }
        }
        if (!stack.isEmpty()) {
            c = stack.pop(); // 弹出运算符
            if (c == '!') {
                stack.push(fCount == 1 ? 't' : 'f');
            } else if (c == '&') {
                stack.push(fCount == 0 ? 't' : 'f');
            } else if (c == '|') {
                stack.push(tCount > 0 ? 't' : 'f');
            }
        }
    }
}
