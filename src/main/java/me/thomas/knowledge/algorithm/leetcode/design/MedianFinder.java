package me.thomas.knowledge.algorithm.leetcode.design;

import java.util.PriorityQueue;

/**
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * <p>
 * 例如，
 * <p>
 * [2,3,4]的中位数是 3
 * <p>
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * <p>
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 *
 * @author xinsheng2.zhao
 * @version Id: MedianFinder.java, v 0.1 2022/6/30 20:46 xinsheng2.zhao Exp $
 */
public class MedianFinder {

    private final PriorityQueue<Integer> minHeap;
    private final PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
    }

    /**
     * 思路：维护一个最小堆一个最大堆，确保最小堆和最大堆的数量差小于等于1（最大堆多1个元素）且最大堆（存放前半部分小数字）的根节点小于等于最小堆（存放后半部分大数字）的根节点。
     * 以放入100以内的数为例：最大堆的根节点是49，最小堆的根节点是50。
     * 逻辑：先根据num与最大堆的大小比较结果放入合适的堆，再保持堆的平衡。
     */
    public void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            }
        } else {
            minHeap.offer(num);
            if (maxHeap.size() < minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }
    }

    /**
     * 思路：
     * 1. 堆中没有放入任何元素，返回0
     * 2. 两个堆中的元素一样多，返回两堆根节点的平均数
     * 3. 返回最大堆的根元素
     */
    public double findMedian() {
        if (maxHeap.isEmpty()) {
            return 0;
        }
        if (minHeap.size() == maxHeap.size()) {
            return ((double) minHeap.peek() + maxHeap.peek()) / 2;
        }
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(5);
        medianFinder.addNum(4);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(1);
        System.out.println(medianFinder.findMedian());
    }
}
