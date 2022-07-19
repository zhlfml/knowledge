package me.thomas.knowledge.algorithm.leetcode.greedy;

import java.util.Arrays;

/**
 * 1288. 删除被覆盖区间
 * 给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
 * <p>
 * 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。
 * <p>
 * 在完成所有删除操作后，请你返回列表中剩余区间的数目。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution1288.java, v 0.1 2022/7/18 22:45 xinsheng2.zhao Exp $
 */
public class Solution1288 {

    /**
     * 输入：intervals = [[1,4],[3,6],[2,8]]
     * 输出：2
     * 解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
     */
    public int removeCoveredIntervals(int[][] intervals) {
        // 按照区间的终点升序，起点降序
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] < b[1]) {
                return -1;
            }
            if (a[1] > b[1]) {
                return 1;
            }
            return b[0] - a[0];
        });
        int removed = 0;
        int last = intervals.length - 1;
        int left = intervals[last][0];
        for (int i = last - 1; i >= 0; i--) {
            if (left <= intervals[i][0]) {
                removed++;
            } else {
                left = intervals[i][0];
            }
        }
        return intervals.length - removed;
    }
}
