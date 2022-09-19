package me.thomas.knowledge.algorithm.leetcode.greedy;

import java.util.Arrays;

/**
 * 435. 无重叠区间
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution435.java, v 0.1 2022/9/19 22:06 xinsheng2.zhao Exp $
 */
public class Solution435 {

    /**
     * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
     * 输出: 1
     * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
     * <p>
     * 思路：按照终点升序，起点升序排列数组。
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        // base case
        if (intervals == null || intervals.length <= 1) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });
        return eraseOverlapIntervals_greedy(intervals);
    }

    /**
     * 思路：取出数组中end最早结束的区间，依次取后面的区间与之相比较，如果不相交则取下一个区间的end替换原来的end，并且不相交数量加1。
     * 注：该实现方式与1288.删除被覆盖区间的思路一致，便于记忆和理解。
     */
    int eraseOverlapIntervals_greedy(int[][] intervals) {
        int least = 1; /* 至少有一个区间与其他区间不相交 */
        int end = intervals[0][1]; /* 取出数组中end最早结束的线段 */
        for (int i = 1; i < intervals.length; i++) {
            if (end <= intervals[i][0]) { /* 因为已按照终点升序排序，所以只要前一个数组的终点小于等于下一个区间数组的起点，那么必定无交集。 */
                // 找到与之不相交的下一个的区间了，取下一个区间的end替换原来的end，并且不相交数量加1
                least++;
                end = intervals[i][1];
            }
        }
        // 总区间数量 减去 最多的不相交的区间数量 等于 至少需要删除的区间数量
        return intervals.length - least;
    }

    int eraseOverlapIntervals_dp(int[][] intervals) {
        return 0;
    }

    public static void main(String[] args) {
        Solution435 solution = new Solution435();
        System.out.println(solution.eraseOverlapIntervals(new int[][] { { 0, 2 }, { 1, 3 }, { 2, 4 }, { 3, 5 }, { 4, 6 } }));
    }
}
