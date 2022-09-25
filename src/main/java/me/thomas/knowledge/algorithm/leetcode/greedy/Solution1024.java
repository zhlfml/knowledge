package me.thomas.knowledge.algorithm.leetcode.greedy;

import java.util.Arrays;

/**
 * 1024. 视频拼接
 * 你将会获得一系列视频片段，这些片段来自于一项持续时长为 time 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
 * <p>
 * 使用数组 clips 描述所有的视频片段，其中 clips[i] = [starti, endi] 表示：某个视频片段开始于 starti 并于 endi 结束。
 * <p>
 * 甚至可以对这些片段自由地再剪辑：
 * <p>
 * 例如，片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
 * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, time]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution1024.java, v 0.1 2022/9/25 11:02 xinsheng2.zhao Exp $
 */
public class Solution1024 {

    /**
     * 输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], time = 10
     * 输出：3
     * 解释：
     * 选中 [0,2], [8,10], [1,9] 这三个片段。
     * 然后，按下面的方案重制比赛片段：
     * 将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
     * 现在手上的片段为 [0,2] + [2,8] + [8,10]，而这些覆盖了整场比赛 [0, 10]。
     * <p>
     * 思路：按照起始时间升序，结束时间降序排序，如果后一个时间片段与前一个有交集（起始时间小于等于前一个的结束时间）则用之。
     */
    public int videoStitching(int[][] clips, int time) {
        if (time == 0) {
            return 0;
        }
        if (clips == null || clips.length == 0) {
            return -1;
        }
        Arrays.sort(clips, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        }); /* 按起始时间升序，结束时间降序排序 */

        if (clips[0][0] > 0) { /* 检查第一个片段是否从时间0开始, 如果不是则无法拼出[0, time] */
            return -1;
        }
        int n = clips.length;
        int least = 1; /* 至少需要一个片段 */
        int i = 0;
        while (i < n) {
            /*
               内循环选择播放到最远的距离
             */
            int next = i; /* 下一个播放的最远距离的数组下标 */
            int j = i + 1;
            while (j < n) {
                if (clips[j][1] <= clips[i][1]) {
                    j++;
                    continue;
                }
                if (clips[j][0] <= clips[i][1] && clips[j][1] > clips[i][1]) {
                    if (clips[next][1] < clips[j][1]) {
                        next = j;
                    }
                    j++;
                } else
                    break;
            }
            least++;
            if (clips[next][1] >= time) {
                return least;
            }
            if (next == i) {
                return -1;
            }
            i = next;
        }
        return least;
    }

    public static void main(String[] args) {
        Solution1024 solution = new Solution1024();
        System.out.println(solution.videoStitching(
                new int[][] { { 0, 1 }, { 6, 8 }, { 0, 2 }, { 5, 6 }, { 0, 4 }, { 0, 3 }, { 6, 7 }, { 1, 3 }, { 4, 7 }, { 1, 4 }, { 2, 5 }, { 2, 6 }, { 3, 4 }, { 4, 5 }, { 5, 7 },
                              { 6, 9 } }, 9));
    }
}
