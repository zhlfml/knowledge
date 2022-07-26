package me.thomas.knowledge.algorithm.leetcode.classic;

import java.util.HashSet;
import java.util.Set;

/**
 * 391. 完美矩形
 * 给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi] 表示一个坐标轴平行的矩形。这个矩形的左下顶点是 (xi, yi) ，右上顶点是 (ai, bi) 。
 * <p>
 * 如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ；否则，返回 false 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution391.java, v 0.1 2022/7/25 21:54 xinsheng2.zhao Exp $
 */
public class Solution391 {

    /**
     * 思路：依次检测一下问题：
     * 1. 检查面积是否一致。
     * 2. 合并后的顶点的数量是不是仅剩4个。
     * 3. 仅剩的4个顶点是不是之前预测的4个。
     * 如果回答都是true则返回true，否则返回false。
     */
    public boolean isRectangleCover(int[][] rectangles) {
        // 第一步：遍历所有的矩形，计算每个矩形的面积之和，并找出最左下顶点和最右上顶点。
        int actualArea = 0;
        int minXi = Integer.MAX_VALUE, minYi = Integer.MAX_VALUE;
        int maxAi = Integer.MIN_VALUE, maxBi = Integer.MIN_VALUE;
        for (int[] rectangle : rectangles) {
            actualArea += (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);
            minXi = Math.min(minXi, rectangle[0]);
            minYi = Math.min(minYi, rectangle[1]);
            maxAi = Math.max(maxAi, rectangle[2]);
            maxBi = Math.max(maxBi, rectangle[3]);
        }
        int expectedArea = (maxAi - minXi) * (maxBi - minYi);
        // 面积不同，肯定不是完美矩形
        if (expectedArea != actualArea) {
            return false;
        }

        // 检查顶点的数量：偶数个顶点需要同时消除，只能保留奇数个顶点在集合中
        Set<String> vertexes = new HashSet<>();
        for (int[] rectangle : rectangles) {
            for (String vertex : new String[] { rectangle[0] + "." + rectangle[1], rectangle[0] + "." + rectangle[3], rectangle[2] + "." + rectangle[3],
                                                rectangle[2] + "." + rectangle[1] }) {
                if (vertexes.contains(vertex)) {
                    vertexes.remove(vertex);
                } else {
                    vertexes.add(vertex);
                }
            }
        }
        // 剩余的顶点数量不是4个，肯定不是完美矩形
        if (vertexes.size() != 4) {
            return false;
        }
        // 检查仅剩的4个顶点是不是之前预测的4个
        for (String vertex : new String[] { minXi + "." + minYi, minXi + "." + maxBi, maxAi + "." + maxBi, maxAi + "." + minYi }) {
            if (!vertexes.contains(vertex)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution391 solution = new Solution391();
        System.out.println(solution.isRectangleCover(new int[][] { { 1, 1, 3, 3 }, { 3, 1, 4, 2 }, { 3, 2, 4, 4 }, { 1, 3, 2, 4 }, { 2, 3, 3, 4 } }));
    }
}
