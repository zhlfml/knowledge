package me.thomas.knowledge.algorithm.leetcode.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution130.java, v 0.1 2022/6/24 13:33 xinsheng2.zhao Exp $
 */
public class Solution130 {

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int row = board.length, col = board[0].length;
        List<Element> elements = new ArrayList<>(row * col);
        // 哑节点：与之相连的节点的O都不能被替换。
        Element dummy = new Element(-1, -1, 'O');

        UnionFind unionFind = new UnionFind();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Element element = new Element(i, j, board[i][j]);
                elements.add(element);
                if (element.isValueO()) {
                    // 如果O在边界上，则与dummy节点联通
                    if ((i == 0 || i == row - 1) || (j == 0 || j == col - 1)) {
                        unionFind.union(dummy, element);
                    }
                }
            }
        }

        // 相邻的O元素联通起来, 每个节点只负责检查下面和右边是否存在相邻的O元素。
        for (int i = 0; i < row - 1; i++) { // row - 1的目的是为了不出界，col - 1同理。
            for (int j = 0; j < col - 1; j++) {
                if (board[i][j] == 'O') {
                    // 检查右侧
                    if (board[i][j + 1] == 'O') {
                        unionFind.union(elements.get(i * col + j), elements.get(i * col + (j + 1)));
                    }
                    // 检查下方
                    if (board[i + 1][j] == 'O') {
                        unionFind.union(elements.get(i * col + j), elements.get((i + 1) * col + j));
                    }
                }
            }
        }

        // 检查所有的O元素，如果不与dummy联通则设置为X
        for (Element element : elements) {
            if (element.isValueO() && !unionFind.connected(dummy, element)) {
                board[element.row][element.col] = 'X';
            }
        }
    }

    class Element {
        int  row;
        int  col;
        char value;

        Element parent;

        public Element(int row, int col, char value) {
            this.row = row;
            this.col = col;
            this.value = value;
            this.parent = this;
        }

        boolean isValueO() {
            return 'O' == value;
        }
    }

    private class UnionFind {

        void union(Element one, Element another) {
            findRoot(another).parent = findRoot(one);
        }

        boolean connected(Element one, Element another) {
            return findRoot(one) == findRoot(another);
        }

        Element findRoot(Element element) {
            if (element.parent == element) {
                return element;
            }
            return (element.parent = findRoot(element.parent));
        }
    }
}