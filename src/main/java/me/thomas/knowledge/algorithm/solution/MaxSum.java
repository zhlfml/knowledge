package me.thomas.knowledge.algorithm.solution;

/**
 * 问题：输入一个具有n个整数的向量x，输出向量的任何连续子向量中的最大和。
 * 例如：输入为31,-41,59,26,-53,58,97,-93,-23,84，则输出为x[2..6]的总和，即187。
 *
 * Created by Thomas on 2016/1/13.
 */
public class MaxSum {

    public static int solve(int[] array) {
        int maxSoFar = 0, maxEndingHere = 0;
        for (int i = 0; i < array.length; i++) {
            maxEndingHere = max(maxEndingHere + array[i], 0);
            maxSoFar = max(maxEndingHere, maxSoFar);
        }
        return maxSoFar;
    }

    public static int max(int a, int b) {
        return a > b ? a : b;
    }

    public static void main(String[] args) {
        int[] array = new int[] {31,-41,59,26,-53,58,97,-93,-23,84};
        System.out.println(solve(array));
    }
}
