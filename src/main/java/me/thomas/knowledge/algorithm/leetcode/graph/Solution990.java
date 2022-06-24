package me.thomas.knowledge.algorithm.leetcode.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或"a!=b"。
 * 在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
 * <p>
 * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回true，否则返回 false。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution990.java, v 0.1 2022/6/24 14:44 xinsheng2.zhao Exp $
 */
public class Solution990 {

    public boolean equationsPossible(String[] equations) {
        if (equations == null || equations.length == 0) {
            return true;
        }

        // 假设需要判断的表达式最多占一半
        List<String> judges = new ArrayList<>((equations.length + 1) / 2);

        UnionFind unionFind = new UnionFind(26);
        // 先设置联通的变量
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                unionFind.union(offset(equation.charAt(0)), offset(equation.charAt(3)));
            } else {
                judges.add(equation);
            }
        }
        // 最后判断非联通的变量是否成立
        for (String judge : judges) {
            if (unionFind.connected(offset(judge.charAt(0)), offset(judge.charAt(3)))) {
                return false;
            }
        }
        return true;
    }

    private int offset(char c) {
        return c - 'a';
    }

    private class UnionFind {
        int[] parent;

        UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        void union(int x, int y) {
            parent[findRoot(y)] = findRoot(x);
        }

        boolean connected(int x, int y) {
            return findRoot(x) == findRoot(y);
        }

        int findRoot(int x) {
            if (parent[x] == x) {
                return x;
            }
            return parent[x] = findRoot(parent[x]);
        }

    }
}

