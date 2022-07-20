package me.thomas.knowledge.algorithm.leetcode.classic;

import java.util.Arrays;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution46.java, v 0.1 2022/7/19 21:57 xinsheng2.zhao Exp $
 */
public class Solution43 {

    /**
     * 输入: num1 = "123", num2 = "456"
     * 输出: "56088"
     */
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        char[] firstNum = num1.toCharArray();
        char[] secondNum = num2.toCharArray();
        // 积的最长位数是两乘数位数之和
        char[] answer = new char[firstNum.length + secondNum.length];
        Arrays.fill(answer, '0');
        // 模拟手算乘法
        for (int i = firstNum.length - 1; i >= 0; i--) {
            for (int j = secondNum.length - 1; j >= 0; j--) {
                // 计算两对应位上数字的乘积
                int product = (firstNum[i] - '0') * (secondNum[j] - '0');
                // 分解出高位和低位对应的数字 -- 与while循环体中保持一致
                int lowVal = product % 10, highVal = product / 10;
                // 写入的个十百千位
                int position = i + j + 1; /* (answer.length - 1) - ((firstNum.length - 1 - i) + (secondNum.length - 1 - j)): answer.length = firstNum.length + secondNum.length */
                do {
                    // 和
                    int summer = (answer[position] - '0') + lowVal;
                    // 写入当前位
                    answer[position] = (char) (summer % 10 + '0');
                    highVal += summer / 10;
                    // 没有进位则停止按位加法
                    if (highVal == 0) {
                        break;
                    }
                    // 进位处理，也是分解成高位和低位
                    lowVal = highVal;
                    highVal = 0;
                    position--;
                } while (true);
            }
        }
        // 检查第一位是否为0
        int start = answer[0] == '0' ? 1 : 0;
        return new String(answer, start, answer.length - start);
    }

    public static void main(String[] args) {
        Solution43 solution = new Solution43();
        System.out.println(solution.multiply("123", "456"));
    }
}
