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
     * 思路：每个单位宽度能装的水的容量由其两侧最高的柱子中较矮的那根决定。
     * 输入：height = [4,2,0,3,2,5]
     * 输出：9
     */
    public int trap(int[] height) {
        int answer = 0;
        int maxLeftHeight = 0, maxRightHeight = 0; /* 左右两侧的最高的柱子的高度 */
        int left = 0, right = height.length - 1;
        while (left < right) {
            // 哪边柱子矮，哪边就前进一步。
            int leftHeight = height[left], rightHeight = height[right];
            maxLeftHeight = Math.max(maxLeftHeight, leftHeight);
            maxRightHeight = Math.max(maxRightHeight, rightHeight);
            if (leftHeight < rightHeight) {
                answer += maxLeftHeight - leftHeight;
                left++;
            } else {
                answer += maxRightHeight - rightHeight;
                right--;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution42 solution = new Solution42();
        System.out.println(solution.trap(new int[] { 4, 2, 0, 3, 2, 5 })); // 9
        System.out.println(solution.trap(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 })); // 6
    }
}
