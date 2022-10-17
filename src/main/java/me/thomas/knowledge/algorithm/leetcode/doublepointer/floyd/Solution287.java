package me.thomas.knowledge.algorithm.leetcode.doublepointer.floyd;

/**
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 * <p>
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 * <p>
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution287.java, v 0.1 2022/10/17 23:25 xinsheng2.zhao Exp $
 */
public class Solution287 {

    /**
     * 思路：弗洛伊德探圆法 -- 快慢指针
     */
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            //System.out.printf("nums[%d] = %d, nums[nums[%d]] = %d\n", slow, nums[slow], fast, nums[nums[fast]]);
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
