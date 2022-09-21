package me.thomas.knowledge.algorithm.leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * 253. 会议室 II
 * 给你输入若干形如 [begin, end] 的区间，代表若干会议的开始时间和结束时间，请你计算至少需要申请多少间会议室。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution253.java, v 0.1 2022/9/20 22:18 xinsheng2.zhao Exp $
 */
public class Solution253 {

    /**
     * 输入: meetings = [[0,30],[5,10],[15,20]]
     * 输出: 2
     * <p>
     * 思路：本质是求解最多同一时刻最多有几个会议在举行。
     */
    public int minMeetingRooms(int[][] meetings) {
        if (meetings == null || meetings.length == 0) {
            return 0;
        }
        if (meetings.length == 1) {
            return 1;
        }
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[1])); /* 以会议结束时间排序 */
        return minMeetingRooms_linear(meetings);
    }

    /**
     * 自己想到的朴素思路，检查每个区间的end与其他几个区间相交，返回相交数最多的返回。
     */
    int minMeetingRooms_my(int[][] meetings) {
        int n = meetings.length;
        int answer = 1;
        for (int i = 0; i < n; i++) {
            int meanwhile = 1;
            for (int j = i + 1; j < n; j++) {
                if (meetings[i][1] > meetings[j][0]) { /* 只要当前i的会议在j会议开始后才结束，那么肯定要并行举行会议。 */
                    meanwhile++;
                } else
                    break;
            }
            answer = Math.max(answer, meanwhile);
        }
        return answer;
    }

    /**
     * labuladong提供的线性思路，既然每个会议时间区间的开始处表示一个会议开始，结束处表示会议结束，那么只需按照时间维度分别排序区间开始处和区间结束处。
     * 遇到开始处的位置+1，结束处的位置-1，那么处理过程中的最大数即为答案。
     * <p>
     * 具体的做法是：区间开始和区间结束分别保存到两个数组中，定义指针i遍历区间开始数组，指针j遍历区间结束数组。通过时间大小关系向后移动i或j。
     * 向后移动i时说明遇到了开始位置，计数加1，反之减1。
     */
    int minMeetingRooms_linear(int[][] meetings) {
        int n = meetings.length;
        int[] begins = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            begins[i] = meetings[i][0];
            ends[i] = meetings[i][1];
        }
        Arrays.sort(begins);
        Arrays.sort(ends);

        int answer = 0, meanwhile = 0;
        int i = 0, j = 0;
        while (i < n) { /* i没有到达n之前，j必定不会到达n，因为先后开始后有结束 */
            if (begins[i] < ends[j]) { /* 碰到一个区间的起点和另一个区间的终点相同时，先处理终点，因为先处理起点的话需要开更多的会议室。 */
                meanwhile++;
                i++;
            } else {
                meanwhile--;
                j++;
            }
            answer = Math.max(answer, meanwhile);
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution253 solution = new Solution253();
        Random random = new Random();
        int meetingCount = random.nextInt(100) + 50;
        int[][] meetings = new int[meetingCount][2];
        for (int i = 0; i < meetingCount; i++) {
            meetings[i][0] = random.nextInt(100);
            meetings[i][1] = random.nextInt(100) + 100;
        }
        int linear = solution.minMeetingRooms_linear(meetings);
        int my = solution.minMeetingRooms_my(meetings);
        System.out.println(linear == my ? "Right" : "Wrong");
    }
}
