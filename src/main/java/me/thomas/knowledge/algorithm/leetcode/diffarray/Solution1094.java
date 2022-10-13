package me.thomas.knowledge.algorithm.leetcode.diffarray;

import java.util.Arrays;

/**
 * 1094. 拼车
 * 车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）
 * <p>
 * 给定整数 capacity 和一个数组 trips ,  trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有 numPassengersi 乘客，接他们和放他们的位置分别是 fromi 和 toi 。这些位置是从汽车的初始位置向东的公里数。
 * <p>
 * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution1094.java, v 0.1 2022/10/13 15:32 xinsheng2.zhao Exp $
 */
public class Solution1094 {

    public boolean carPooling(int[][] trips, int capacity) {
        if (trips == null || trips.length == 0) {
            return true;
        }
        if (capacity == 0) {
            return false;
        }
        int maxTo = Arrays.stream(trips).mapToInt(a -> a[2]).max().getAsInt();
        int[] diffarray = new int[maxTo + 1]; /* 存储的是后一个数减去前一个数的差值 */
        for (int i = 0; i < trips.length; i++) {
            diffarray[trips[i][1]] += trips[i][0];
            diffarray[trips[i][2]] -= trips[i][0];
        }
        int travels = 0;
        for (int i = 0; i <= maxTo; i++) {
            travels += diffarray[i];
            if (travels > capacity) {
                return false;
            }
        }
        return true;
    }
}
