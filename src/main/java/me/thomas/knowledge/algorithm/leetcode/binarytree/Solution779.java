package me.thomas.knowledge.algorithm.leetcode.binarytree;

/**
 * 779. 第K个语法符号
 * 我们构建了一个包含 n 行( 索引从 1  开始 )的表。首先在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
 * <p>
 * 例如，对于 n = 3 ，第 1 行是 0 ，第 2 行是 01 ，第3行是 0110 。
 * 给定行数 n 和序数 k，返回第 n 行中第 k 个字符。（ k 从索引 1 开始）
 *
 * @author xinsheng2.zhao
 * @version Id: Solution779.java, v 0.1 2022/10/20 21:09 xinsheng2.zhao Exp $
 */
public class Solution779 {

    /**
     * 思路：计算二叉树从(0,0) -> (n - 1, k - 1)的路径，如果父节点是0，向下走一步如果是向左，那么子节点是0，否则是1。如果父节点是1则反之。
     */
    public int kthGrammar(int n, int k) {
        if (n < 1 || k < 1) {
            throw new IllegalArgumentException("n >= 1 and k >= 1 required");
        }
        // 转化为传统的索引从0开始
        n = n - 1;
        k = k - 1;
        int answer = 0;
        while (n > 0) {
            answer = down(answer, (k & (1 << (n - 1))) == 0);
            n--;
        }
        return answer;
    }

    /**
     * 根据当前值和向下的左右方向决定返回值
     * 如果父节点是0，向下走一步如果是向左，那么子节点是0，否则是1。如果父节点是1则反之。
     */
    int down(int bit, boolean left) {
        return left ? bit : 1 - bit;
    }

    public static void main(String[] args) {
        Solution779 solution779 = new Solution779();
        System.out.println(solution779.kthGrammar(2, 2));
    }
}
