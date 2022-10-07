package me.thomas.knowledge.algorithm.leetcode.math.map;

import java.util.Arrays;

/**
 * 645. 错误的集合
 * 集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。
 * <p>
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
 * <p>
 * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution645.java, v 0.1 2022/7/17 21:45 xinsheng2.zhao Exp $
 */
public class Solution645 {

    /**
     * 对于这种数组问题，关键点在于元素和索引是成对儿出现的，常用的方法是排序、异或、映射。
     * 映射的思路就是我们刚才的分析，将每个索引和元素映射起来，通过正负号记录某个元素是否被映射。(本题的解法)
     * 排序的方法也很好理解，对于这个问题，可以想象如果元素都被从小到大排序，如果发现索引对应的元素如果不相符，就可以找到重复和缺失的元素。
     * 异或运算也是常用的，因为异或性质 a ^ a = 0, a ^ 0 = a，如果将索引和元素同时异或，就可以消除成对儿的索引和元素，留下的就是重复或者缺失的元素。可以看看前文 常用的位运算，介绍过这种方法。
     */
    public int[] findErrorNums(int[] nums) {
        int[] answer = new int[2];
        /*
           通过映射找到重复的数字
         */
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                answer[0] = index + 1; /* 找到的重复的数 */
            } else {
                nums[index] = -nums[index]; /* 设置为负数 */
            }
        }
        /*
          遍历所有数字，哪个数字是正数，则对应的下标就是缺失的数字
         */
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                answer[1] = i + 1;
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution645 solution645 = new Solution645();
        System.out.println(Arrays.toString(solution645.findErrorNums(new int[] { 1, 2, 4, 5, 5, 6, 7 })));
    }
}
