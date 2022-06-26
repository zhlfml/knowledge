package me.thomas.knowledge.algorithm.leetcode.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一组n人（编号为1, 2, ..., n），我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。
 * <p>
 * 给定整数 n和数组 dislikes，其中dislikes[i] = [ai, bi]，表示不允许将编号为 ai和bi的人归入同一组。当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution886.java, v 0.1 2022/6/26 09:45 xinsheng2.zhao Exp $
 */
public class Solution886 {

    boolean success = true;

    boolean color[];

    boolean visited[];

    public boolean possibleBipartition(int n, int[][] dislikes) {
        // 构件图
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] dislike : dislikes) {
            // 忽略点：这里是无向图
            graph[dislike[0] - 1].add(dislike[1] - 1);
            graph[dislike[1] - 1].add(dislike[0] - 1);
        }

        color = new boolean[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(graph, i);
            }
        }

        return success;
    }

    private void dfs(List<Integer>[] graph, int start) {
        visited[start] = true;

        for (int neighbor : graph[start]) {
            if (visited[neighbor]) {
                if (color[start] == color[neighbor]) {
                    success = false;
                    return;
                }
                continue;
            }
            color[neighbor] = !color[start];
            dfs(graph, neighbor);
        }
    }
}
