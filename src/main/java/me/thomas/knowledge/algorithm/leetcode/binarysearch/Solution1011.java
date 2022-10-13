package me.thomas.knowledge.algorithm.leetcode.binarysearch;

/**
 * 1011. 在 D 天内送达包裹的能力
 * 传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。
 * <p>
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 * <p>
 * 返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution1011.java, v 0.1 2022/10/13 11:30 xinsheng2.zhao Exp $
 */
public class Solution1011 {

    public int shipWithinDays(int[] weights, int days) {
        if (weights == null || weights.length == 0) {
            return 0;
        }
        int n = weights.length;
        int minWeight = 0, maxWeight = 0;
        for (int weight : weights) {
            minWeight = Math.max(minWeight, weight);
            maxWeight += weight;
        }
        // System.out.printf("minWeight = %d, maxWeight = %d\n", minWeight, maxWeight);
        // 最小运载能力必须是[minWeight, maxWeight]之间的一个数，通过左边界二分法找出这个最小数。
        int low = minWeight, high = maxWeight;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int needDays = needDays(weights, mid);
            // System.out.printf("mid = %d, needDays = %d\n", mid, needDays);
            if (needDays > days) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    int needDays(int[] weights, int weightOneDay) {
        int days = 1;
        int shipWeight = 0;
        for (int weight : weights) {
            shipWeight += weight;
            if (shipWeight > weightOneDay) {
                days++;
                shipWeight = weight;
            }
        }
        return days;
    }
}
