package me.thomas.knowledge.algorithm.leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * 229. 多数元素 II
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution229.java, v 0.1 2022/10/14 17:58 xinsheng2.zhao Exp $
 */
public class Solution229 {

    /**
     * 摩尔投票法，先投票后验票.
     * <p>
     * 思路：
     * 难点1：不知道前两个数是什么数，如何计数，需要借助map结构吗？
     * 难点2：如何知道已经出现了第三个数字？出现第三个数字后，如何同时消除前两个数字出现的次数。
     * 这两个问题可以通过定义firstNum, firstVotes, secondNum, secondVotes这4个简单的变量巧妙解决。
     * <p>
     * 难点3：消除后如果剩余的两个数字出现的投票数相同如何解决？
     * 只要投票数大于0，都是候选项，都需要检查，不存在谁的剩余投票数大谁的可能性就大。
     * <p>
     * 易错点：必须先比较firstNum，secondNum是否等于nums[i]，否则会造成firstNum，secondNum都指向同一个数。
     */
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("nums is null");
        }
        int n = nums.length;
        int firstNum = 0, secondNum = 0; /* 另一个思路：只有在对应的投票数量大于0时，firstNum和secondNum才有效 */
        int firstVotes = 0, secondVotes = 0;
        for (int i = 0; i < n; i++) {
            if (firstVotes > 0 && firstNum == nums[i]) {
                firstVotes++;
            } else if (secondVotes > 0 && secondNum == nums[i]) {
                secondVotes++;
            } else if (firstVotes == 0) {
                firstNum = nums[i];
                firstVotes++;
            } else if (secondVotes == 0) {
                secondNum = nums[i];
                secondVotes++;
            } else { /* 出现的第三个不同的数 */
                firstVotes--;
                secondVotes--;
            }
            // System.out.printf("firstNum = %d, firstVotes = %d, secondNum = %d, secondVotes = %d\n", firstNum, firstVotes, secondNum, secondVotes);
        }

        // 检查候选数字是否满足要求
        int firstCount = 0, secondCount = 0;
        for (int i = 0; i < n; i++) {
            if (firstVotes > 0 && firstNum == nums[i]) {
                firstCount++;
            } else if (secondVotes > 0 && secondNum == nums[i]) {
                secondCount++;
            }
        }

        int oneThird = n / 3;
        List<Integer> answer = new ArrayList<>();
        if (firstCount > oneThird) {
            answer.add(firstNum);
        }
        if (secondCount > oneThird) {
            answer.add(secondNum);
        }
        return answer;
    }
}
