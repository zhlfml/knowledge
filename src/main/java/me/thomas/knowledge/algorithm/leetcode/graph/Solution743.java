package me.thomas.knowledge.algorithm.leetcode.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 有 n 个网络节点，标记为1到 n。
 * <p>
 * 给你一个列表times，表示信号经过 有向 边的传递时间。times[i] = (ui, vi, wi)，其中ui是源节点，vi是目标节点， wi是一个信号从源节点传递到目标节点的时间。
 * <p>
 * 现在，从某个节点K发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回-1 。
 * <p>
 *
 * @author xinsheng2.zhao
 * @version Id: Solution743.java, v 0.1 2022/6/24 21:58 xinsheng2.zhao Exp $
 */
public class Solution743 {

    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] graph = new int[n][n];
        /*
         * 延迟允许为0，所以默认值设置为-1
         */
        for (int[] row : graph) {
            Arrays.fill(row, -1);
        }
        for (int[] time : times) {
            graph[time[0] - 1][time[1] - 1] = time[2];
        }

        /*
         * 避免节点重复访问
         */
        boolean[] visited = new boolean[n];
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        // 优先级队列，按照延迟时长升序排列
        PriorityQueue<Delay> priorityQueue = new PriorityQueue<>(Comparator.comparing(a -> a.time));
        priorityQueue.offer(new Delay(k - 1, 0));
        while (!priorityQueue.isEmpty()) {
            Delay v = priorityQueue.poll();
            if (visited[v.vertex]) {
                continue;
            }
            visited[v.vertex] = true;
            answer[v.vertex] = v.time;
            for (int i = 0; i < n; i++) {
                if (graph[v.vertex][i] >= 0) {
                    priorityQueue.offer(new Delay(i, v.time + graph[v.vertex][i]));
                }
            }
        }

        int maxDelay = -1;
        for (int i = 0; i < n; i++) {
            if (answer[i] < 0) {
                return -1;
            }
            maxDelay = Math.max(maxDelay, answer[i]);
        }
        return maxDelay;
    }

    class Delay {
        int vertex;
        int time;

        public Delay(int vertex, int time) {
            this.vertex = vertex;
            this.time = time;
        }
    }
}
