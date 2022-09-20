package me.thomas.knowledge.algorithm.leetcode.greedy;

import java.util.Arrays;

/**
 * 452. 用最少数量的箭引爆气球
 * 有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend] 表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
 * <p>
 * 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
 * <p>
 * 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution452.java, v 0.1 2022/9/20 21:49 xinsheng2.zhao Exp $
 */
public class Solution452 {

    /**
     * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
     * 输出：2
     * 解释：气球可以用2支箭来爆破:
     * -在x = 6处射出箭，击破气球[2,8]和[1,6]。
     * -在x = 11处发射箭，击破气球[10,16]和[7,12]。
     * <p>
     * 思路：按照end升序，start升序（非必要）排序。
     */
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int n = points.length;
        if (n == 1) {
            return 1;
        }
        Arrays.sort(points, (a, b) -> {
            if (a[1] != b[1]) {
                return a[1] < b[1] ? -1 : 1;
            } else {
                return a[0] <= b[0] ? -1 : 1;
            }
        });
        return findMinArrowShots_greedy(points);
    }

    /**
     * 思路：本质是求最多不相交的区间的数量，与435.无重叠区间的思路相同。
     */
    int findMinArrowShots_greedy(int[][] points) {
        int count = 1; /* 不相交的区间的数量至少是1 */
        int end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > end) {
                end = points[i][1];
                count++;
            }
        }
        return count; /* 每个不相交的区间都需要射出一支箭 */
    }
}
