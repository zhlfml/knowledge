package me.thomas.knowledge.algorithm.leetcode.divideconquer;

import java.util.ArrayList;
import java.util.List;

/**
 * 241. 为运算表达式设计优先级
 * 给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
 * <p>
 * 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution241.java, v 0.1 2022/10/13 16:30 xinsheng2.zhao Exp $
 */
public class Solution241 {

    public List<Integer> diffWaysToCompute(String expression) {
        return diffWaysToCompute(expression, 0, expression.length());
    }

    /**
     * @param expression
     * @param start      include
     * @param end        exclude
     */
    List<Integer> diffWaysToCompute(String expression, int start, int end) {
        // System.out.printf("invoke start = %d, end = %d\n", start, end);
        List<Integer> answer = new ArrayList<>();
        for (int i = start; i < end; i++) {
            // 难点在于怎么优雅地发现表达式中只有纯数字？
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> leftSubAnswer = diffWaysToCompute(expression, start, i);
                List<Integer> rightSubAnswer = diffWaysToCompute(expression, i + 1, end);
                // System.out.printf("leftSubAnswer = %s, rightSubAnswer = %s\n", leftSubAnswer, rightSubAnswer);
                for (int leftValue : leftSubAnswer) {
                    for (int rightValue : rightSubAnswer) {
                        switch (c) {
                            case '+':
                                answer.add(leftValue + rightValue);
                                break;
                            case '-':
                                answer.add(leftValue - rightValue);
                                break;
                            case '*':
                                answer.add(leftValue * rightValue);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
        // 这个方案算是挺优雅的了，还是自己发现的，得意中。。。
        if (answer.isEmpty()) {
            answer.add(Integer.parseInt(expression.substring(start, end)));
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution241 solution241 = new Solution241();
        System.out.println(solution241.diffWaysToCompute("2*3-4*5"));
    }
}
