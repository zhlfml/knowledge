package me.thomas.knowledge.algorithm.leetcode.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 773. 滑动谜题
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示。一次 移动 定义为选择 0 与一个相邻的数字（上下左右）进行交换.
 * <p>
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 * <p>
 * 给出一个谜板的初始状态 board ，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution773.java, v 0.1 2022/7/12 21:08 xinsheng2.zhao Exp $
 */
public class Solution773 {

    /**
     * 需要达成的局面
     */
    final String FINAL_PHASE = "123450";

    /**
     * 白板所在的位置与可移动的位置的映射
     */
    final int[][] neighbors = { { 1, 3 }, { 0, 2, 4 }, { 1, 5 }, { 0, 4 }, { 1, 3, 5 }, { 2, 4 } };

    /**
     * 思路：广度优先搜索
     *
     * @param board 棋盘
     * @return 达成目标的最小步骤
     */
    public int slidingPuzzle(int[][] board) {
        if (board == null || board.length == 0) {
            return 0;
        }
        /*
         * 初始局面
         */
        StringBuilder initialPhase = new StringBuilder(6);
        for (int[] row : board) {
            for (int item : row) {
                initialPhase.append(item);
            }
        }

        int step = 0;
        // 搜寻过的局面
        Set<String> searchedPhases = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(initialPhase.toString());
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String phase = queue.poll();
                if (searchedPhases.contains(phase)) {
                    continue;
                }
                if (FINAL_PHASE.equals(phase)) {
                    return step;
                }
                searchedPhases.add(phase);
                // 寻找0所在的位置
                assert phase != null;
                int position = phase.indexOf('0');
                for (int neighbor : neighbors[position]) {
                    char[] copy = phase.toCharArray().clone(); // clone char[] 竟然允许
                    copy[position] = copy[neighbor];
                    copy[neighbor] = '0';
                    queue.offer(new String(copy));
                }
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution773 solution = new Solution773();
        System.out.println(solution.slidingPuzzle(new int[][] { { 1, 2, 3 }, { 5, 4, 0 } }));
    }
}
