package me.thomas.knowledge.algorithm.leetcode.graph;

import java.util.*;

/**
 * 现在你总共有 numCourses 门课需要选，记为0到numCourses - 1。给你一个数组prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修bi 。
 * <p>
 * 例如，想要学习课程 0 ，你需要先完成课程1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
 *
 * @author xinsheng2.zhao
 * @version Id: CanFinish207.java, v 0.1 2022/6/22 23:38 xinsheng2.zhao Exp $
 */
public class Solution210 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
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

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;
        int[] answer = new int[numCourses];
        while (!queue.isEmpty()) {
            int course = queue.poll();
            answer[count++] = course;
            for (int another : graph.get(course)) {
                if (--indegrees[another] == 0) {
                    queue.offer(another);
                }
            }
        }
        return count == numCourses ? answer : new int[0];
    }

}
