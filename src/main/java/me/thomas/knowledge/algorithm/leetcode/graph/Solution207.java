package me.thomas.knowledge.algorithm.leetcode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组prerequisites 给出，其中prerequisites[i] = [ai, bi] ，表示如果要学习课程ai 则 必须 先学习课程 bi 。
 * <p>
 * 例如，先修课程对[0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution207.java, v 0.1 2022/6/24 09:28 xinsheng2.zhao Exp $
 */
public class Solution207 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegrees = new int[numCourses];
        // 计算依赖和入度
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
            indegrees[prerequisite[0]]++;
        }

        // 如何获得入度最小的节点？-- 通过queue，精华之处在于只有当入度等于0时才放入queue中。
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0; /* 精华之处二：统计入度为0的课程的数量 */
        while (!queue.isEmpty()) {
            count++;
            int course = queue.poll();
            for (int another : graph.get(course)) {
                if (--indegrees[another] == 0) {
                    queue.offer(another);
                }
            }
        }
        return count == numCourses;
    }
}
