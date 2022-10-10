package me.thomas.knowledge.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 08.12. 八皇后
 * 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
 * <p>
 * 注意：本题相对原题做了扩展
 *
 * @author xinsheng2.zhao
 * @version Id: Solution0812.java, v 0.1 2022/10/10 22:43 xinsheng2.zhao Exp $
 */
public class Solution0812 {

    int[]              selected; // selected[0] = 2 表示第1行的第3列放置了皇后，这是解决此问题非常重要的数据结构。
    List<List<String>> answer;

    public List<List<String>> solveNQueens(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }
        selected = new int[n];
        answer = new ArrayList<>();
        backtrack(n, 0);
        return answer;
    }

    void backtrack(int n, int row) {
        if (row == n) { // 说明放成功了
            List<String> item = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                item.add(output(n, selected[i]));
            }
            answer.add(item);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (check(row, col)) { /* 通过了约束才能放置皇后 */
                selected[row] = col;
                backtrack(n, row + 1);
                // 因为select[row]会被重置，所以此处无需撤销操作。
            }
        }
    }

    /**
     * 检查当前格子(row, col)是或违反了条件约束
     */
    boolean check(int row, int col) {
        for (int i = row - 1; i >= 0; i--) { /* 逐行向上检查已放置的行 */
            if (selected[i] == col + (row - i)) { /* 左斜对角线不能放置 */
                return false;
            }
            if (selected[i] == col) { /* 同列不能放置 */
                return false;
            }
            if (selected[i] == col - (row - i)) { /* 右斜对角线不能放置 */
                return false;
            }
        }
        return true;
    }

    String output(int n, int pos) {
        char[] str = new char[n];
        for (int i = 0; i < n; i++) {
            str[i] = i != pos ? '.' : 'Q';
        }
        return new String(str);
    }
}
