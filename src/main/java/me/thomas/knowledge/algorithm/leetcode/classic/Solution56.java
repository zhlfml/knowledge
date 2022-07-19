package me.thomas.knowledge.algorithm.leetcode.classic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution56.java, v 0.1 2022/7/19 15:38 xinsheng2.zhao Exp $
 */
public class Solution56 {

    /**
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     */
    public int[][] merge(int[][] intervals) {
        // 按照区间的起点升序，终点降序
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });
        List<int[]> answer = new ArrayList<>();
        int left = intervals[0][0];
        int right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            // 包含
            if (intervals[i][1] <= right) {
                continue;
            }
            // 分离
            if (intervals[i][0] > right) {
                answer.add(new int[] { left, right });
                left = intervals[i][0];
            }
            right = intervals[i][1];
        }
        // 最后一次区间不能丢了。
        answer.add(new int[] { left, right });
        return answer.toArray(new int[0][0]);
    }
}
