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
     * <p>
     * 思路：位置i处能接到的水由其左边最高和右边最高的柱子（包含自身）中相对较矮的那一个决定的。
     * 所以只需要计算i处，左边最高的柱子和右边最高的柱子后，用相对较矮的那根柱子的高度减去i处柱子的高度即可。
     * 公式：Water[i] = min(max_left[i], max_right[i]) - height[i]
     */
    public int trap(int[] height) {
        if (height == null || height.length < 3) { /* 没有3根柱子接不了雨水 */
            return 0;
        }
        return trap_easy(height);
    }

    /**
     * 符合常识的朴素算法
     * 思路：事前计算好max_left、max_right数组，max_left[i]表示i左侧最高柱子的高度，max_right[i]表示i右侧最高柱子的高度。
     */
    int trap_easy(int[] height) {
        int n = height.length;
        int[] maxLeft = new int[n];
        maxLeft[0] = height[0];
        for (int i = 1; i < n; i++) { /* 左侧最高的柱子要么是当前这一根，要么存储在了maxLeft[i - 1]上了 */
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
        }
        int[] maxRight = new int[n];
        maxRight[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) { /* 理由同上 */
            maxRight[i] = Math.max(maxRight[i + 1], height[i]);
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer += Math.min(maxLeft[i], maxRight[i]) - height[i]; /* 完全套用公式 */
        }
        return answer;
    }

    /**
     * 双指针算法
     * 思路：哪边柱子矮，哪边向中间前进一步。
     * 朴素算法中i处能接的雨水由i左边最高和i右边最高的柱子中较矮的那根决定的。
     * 双指针解法中i处能接的水由[0..left]和max[right..n-1]中最矮的那根决定的，那根矮就先计算谁。
     * 假设left处的柱子的高度小于right处的柱子的高度，那么此时计算left处能接的雨水的量是确定的。
     * 证明：设left处的下标为i，right处的小标为j。
     * 根据公式`Water[i] = min(max_left[i], max_right[i]) - height[i]`
     * 由于max_left[i] < max_right[j]，且右侧j向i移动的过程中，必定有max_right[j] <= max_right[i]
     * 所以min(max_left[i], max_right[i]) == min(max_left[i], max_right[j])
     * 所以此时left处能接的雨水的量是确定的。
     */
    int trap_doublePointer(int[] height) {
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
