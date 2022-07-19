package me.thomas.knowledge.algorithm.leetcode.doublepointer;

import java.util.ArrayList;
import java.util.List;

/**
 * 986. 区间列表的交集
 * 给定两个由一些 闭区间 组成的列表，firstList 和 secondList ，其中 firstList[i] = [starti, endi] 而 secondList[j] = [startj, endj] 。每个区间列表都是成对 不相交 的，并且 已经排序 。
 * <p>
 * 返回这 两个区间列表的交集 。
 * <p>
 * 形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b 。
 * <p>
 * 两个闭区间的 交集 是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3] 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution986.java, v 0.1 2022/7/19 16:03 xinsheng2.zhao Exp $
 */
public class Solution986 {

    /**
     * 思路：两个区间是否存在交集：取起点的大值，终点的小值。如果起点值小于终点值，则存在交集。
     * 输入：firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
     * 输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> answer = new ArrayList<>();
        // 定义两个指针，分别指向firstList和secondList的索引。
        int first = 0, second = 0;
        while (first < firstList.length && second < secondList.length) {
            int[] firstRange = firstList[first];
            int[] secondRange = secondList[second];
            int left = Math.max(firstRange[0], secondRange[0]);
            int right = Math.min(firstRange[1], secondRange[1]);
            if (left <= right) {
                answer.add(new int[] { left, right });
            }
            // 谁的右边界小，谁向后移动
            if (firstRange[1] == right) {
                first++;
            } else {
                second++;
            }
        }
        // 剩余的区间不存在交集，所以无需遍历那个没有遍历结束的集合。
        return answer.toArray(new int[0][0]);
    }
}
