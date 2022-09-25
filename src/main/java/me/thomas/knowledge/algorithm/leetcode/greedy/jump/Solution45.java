package me.thomas.knowledge.algorithm.leetcode.greedy.jump;

/**
 * 45. 跳跃游戏 II
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 假设你总是可以到达数组的最后一个位置。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution45.java, v 0.1 2022/9/25 16:46 xinsheng2.zhao Exp $
 */
public class Solution45 {

    /**
     * 输入: nums = [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     * 从下标为 0 跳到下标为 1 的位置，跳1步，然后跳3步到达数组的最后一个位置。
     * <p>
     */
    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        return jump_labuladong(nums);
    }

    /**
     * 思路：模拟跳跃过程求解。
     */
    int jump_mine(int[] nums) {
        int n = nums.length;
        int prev = 0, next = 0; /* 当前在哪，需要跳跃到哪 */
        int jumps = 0; /* 跳跃的次数 */
        while (prev < n - 1) {
            if (prev + nums[prev] >= n - 1) { /* 最后一跳能跳到末尾就没有必要选择应该跳到哪里了 */
                break;
            }
            int far = 0; /* 下下一跳能跳跃的最远的距离 */
            for (int i = 1; i <= nums[prev] && prev + i < n; i++) { /* 当前所在的位置能跳跃到的地方 */
                if (prev + i + nums[prev + i] >= far) { /* 尽量跳跃到后面的节点上去，所以用了大于等于 */
                    next = prev + i; /* 下一跳准备跳到这 */
                    far = prev + i + nums[prev + i];
                }
            }
            if (next == prev) {
                return -1; /* 无法跳跃到最后个位置 */
            }
            jumps++; /* 跳了一步 */
            prev = next;
        }
        return jumps + 1;
    }

    /**
     * 使用局部的最优解更新全局的最优解，如果走出了右边界，则步数+1
     */
    int jump_labuladong(int[] nums) {
        int mostFar = 0;
        int rightmost = 0; /* 第一次的右边界是0，第二次的右边界是nums[0]，第三次的右边界是1到nums[0]之间最远的距离 */
        int jumps = 0;
        for (int i = 0; i < nums.length; i++) { /* 在[0, nums.length - 1)之间都需要跳跃 */
            if (i > rightmost) { /* 跳出了右边界，步数+1 */
                rightmost = mostFar;
                jumps++;
            }
            mostFar = Math.max(mostFar, i + nums[i]);
            if (mostFar <= i) {
                return -1;
            }
        }
        return jumps;
    }

    public static void main(String[] args) {
        Solution45 solution45 = new Solution45();
        System.out.println(solution45.jump_labuladong(new int[] { 3, 2, 1 }));
    }
}
