package me.thomas.knowledge.algorithm.leetcode.doublepointer;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution42.java, v 0.1 2022/7/22 16:05 xinsheng2.zhao Exp $
 */
public class Solution42 {

    /**
     * 输入：height = [4,2,0,3,2,5]
     * 输出：9
     */
    public int trap(int[] height) {
        int answer = 0;
        int left = 0, right = 0;
        // left最多只能寻找到倒数第二个槽位
        while (left < height.length - 1) {
            // 找到下一根柱子
            if (height[left] > 0) {
                right = left + 1;
                int maxHeightIndex = right;
                // 向右找到第一根比height[left]高的柱子，不存在的情况下找到右侧最高的柱子
                while (right < height.length) {
                    if (height[maxHeightIndex] < height[right]) {
                        maxHeightIndex = right;
                    }
                    if (height[left] < height[right]) {
                        break;
                    }
                    right++;
                }
                right = maxHeightIndex;
                // 计算[left, right]区间内存储的雨水量
                int lowWaterLine = Math.min(height[left], height[right]);
                for (int i = left + 1; i < right; i++) {
                    answer += lowWaterLine - height[i];
                }
                left = right - 1;
            }
            left++;
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution42 solution = new Solution42();
        System.out.println(solution.trap(new int[] { 4, 2, 0, 3, 2, 5 }));
        System.out.println(solution.trap(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 }));
    }
}
