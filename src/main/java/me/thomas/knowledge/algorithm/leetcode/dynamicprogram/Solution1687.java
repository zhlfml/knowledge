package me.thomas.knowledge.algorithm.leetcode.dynamicprogram;

/**
 * 1687. 从仓库到码头运输箱子
 * 你有一辆货运卡车，你需要用这一辆车把一些箱子从仓库运送到码头。这辆卡车每次运输有 箱子数目的限制 和 总重量的限制 。
 * <p>
 * 给你一个箱子数组 boxes 和三个整数 portsCount, maxBoxes 和 maxWeight ，其中 boxes[i] = [portsi, weighti] 。
 * <p>
 * portsi 表示第 i 个箱子需要送达的码头， weightsi 是第 i 个箱子的重量。
 * portsCount 是码头的数目。
 * maxBoxes 和 maxWeight 分别是卡车每趟运输箱子数目和重量的限制。
 * 箱子需要按照 数组顺序 运输，同时每次运输需要遵循以下步骤：
 * <p>
 * 卡车从 boxes 队列中按顺序取出若干个箱子，但不能违反 maxBoxes 和 maxWeight 限制。
 * 对于在卡车上的箱子，我们需要 按顺序 处理它们，卡车会通过 一趟行程 将最前面的箱子送到目的地码头并卸货。如果卡车已经在对应的码头，那么不需要 额外行程 ，箱子也会立马被卸货。
 * 卡车上所有箱子都被卸货后，卡车需要 一趟行程 回到仓库，从箱子队列里再取出一些箱子。
 * 卡车在将所有箱子运输并卸货后，最后必须回到仓库。
 * <p>
 * 请你返回将所有箱子送到相应码头的 最少行程 次数。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution1687.java, v 0.1 2022/12/6 23:30 xinsheng2.zhao Exp $
 */
public class Solution1687 {

    /**
     * 思路：前缀和 + 动态数组 + 二分法
     */
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + boxes[i - 1][1];
        }

        int[] prefixContinue = new int[n + 1];
        prefixContinue[0] = 0;
        prefixContinue[1] = 1;
        for (int i = 2; i <= n; i++) {
            prefixContinue[i] = prefixContinue[i - 1] + (boxes[i - 2][0] == boxes[i - 1][0] ? 0 : 1);
        }

        int[] dp = new int[n + 1]; // 定义：将[0, i]区间的箱子运送到码头最低需要dp[i]次行程。
        dp[1] = 2; /* base case: 送第一个箱子需要2趟行程：从仓库运送箱子到码头x，再回到仓库 */
        for (int i = 2; i <= n; i++) {
            // 回头查找从哪里开始一起运送比较省行程
            int from = leftBound(prefixSum, Math.max(i - maxBoxes, 1), i, maxWeight); /* i是从1开始的 */
            // 中间有连续码头相同的箱子算作一次行程
            int delivers = 2 + (prefixContinue[i] - prefixContinue[from]); // 一起运送的箱子至少有去和回两个行程。
            dp[i] = dp[from - 1] + delivers;
        }
        return dp[n];
    }

    int leftBound(int[] prefix, int from, int to, int target) {
        int low = from, high = to;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (prefix[to] - prefix[mid - 1] <= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        Solution1687 solution1687 = new Solution1687();
        System.out.println(solution1687.boxDelivering(new int[][] { { 1, 4 }, { 1, 2 }, { 2, 1 }, { 2, 1 }, { 3, 2 }, { 3, 4 } }, 3, 6, 7));
        System.out.println(solution1687.boxDelivering(new int[][] { { 2, 4 }, { 2, 5 }, { 3, 1 }, { 3, 2 }, { 3, 7 }, { 3, 1 }, { 4, 4 }, { 1, 3 }, { 5, 2 } }, 5, 5, 7));
    }
}
