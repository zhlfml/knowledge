package me.thomas.knowledge.algorithm.leetcode.design;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 855. 考场就座
 * 在考场里，一排有 N 个座位，分别编号为 0, 1, 2, ..., N-1 。
 * <p>
 * 当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。如果有多个这样的座位，他会坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 0 号座位上。)
 * <p>
 * 返回 ExamRoom(int N) 类，它有两个公开的函数：其中，函数 ExamRoom.seat() 会返回一个 int （整型数据），代表学生坐的位置；函数 ExamRoom.leave(int p) 代表坐在座位 p 上的学生现在离开了考场。每次调用 ExamRoom.leave(p) 时都保证有学生坐在座位 p 上。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution.java, v 0.1 2022/7/25 21:53 xinsheng2.zhao Exp $
 */
public class ExamRoom {

    int                   n;
    TreeSet<Segment>      treeSet;
    Map<Integer, Segment> startMap;
    Map<Integer, Segment> endMap;

    public ExamRoom(int n) {
        this.n = n;
        this.treeSet = new TreeSet<>(Comparator.comparingInt(Segment::distanceFromCenter).reversed().thenComparingInt(Segment::getStart));
        this.startMap = new HashMap<>();
        this.endMap = new HashMap<>();
        // 初始化最长的线段
        addSegment(new Segment(0, n - 1));
    }

    public int seat() {
        Segment segment = removeFirstSegment();
        if (segment == null) {
            return -1;
        }
        if (segment.getStart() == 0) {
            addSegment(new Segment(segment.getStart() + 1, segment.getEnd()));
            return 0;
        }
        if (segment.getEnd() == n - 1) {
            addSegment(new Segment(segment.getStart(), segment.getEnd() - 1));
            return n - 1;
        }
        int mid = segment.getMiddle();
        addSegment(new Segment(segment.getStart(), mid - 1));
        addSegment(new Segment(mid + 1, segment.getEnd()));
        return mid;
    }

    public void leave(int p) {
        Segment rightSegment = startMap.get(p + 1);
        removeSegment(rightSegment);
        Segment leftSegment = endMap.get(p - 1);
        removeSegment(leftSegment);
        addSegment(new Segment(leftSegment == null ? p : leftSegment.getStart(), rightSegment == null ? p : rightSegment.getEnd()));
    }

    void addSegment(Segment segment) {
        if (segment.getStart() > segment.getEnd()) {
            return;
        }
        treeSet.add(segment);
        startMap.put(segment.getStart(), segment);
        endMap.put(segment.getEnd(), segment);
    }

    void removeSegment(Segment segment) {
        if (segment == null) {
            return;
        }
        treeSet.remove(segment);
        startMap.remove(segment.getStart());
        endMap.remove(segment.getEnd());
    }

    Segment removeFirstSegment() {
        Segment segment = treeSet.first();
        removeSegment(segment);
        return segment;
    }

    /**
     * 可入座的连续的位置组成的段
     */
    class Segment {
        /**
         * 可入座的起始位置，包含
         */
        int start;
        /**
         * 可入座的终点位置，包含
         */
        int end;

        public Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        /**
         * 获取中心点位置
         */
        public int getMiddle() {
            return start + (end - start) / 2;
        }

        /**
         * 中心点到左侧的距离
         */
        public int distanceFromCenter() {
            if (start == 0 || end == n - 1) {
                return end - start;
            }
            return (end - start) / 2;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }

    public static void main(String[] args) {
        ExamRoom examRoom = new ExamRoom(10);
        System.out.println(examRoom.seat()); // 0
        System.out.println(examRoom.seat()); // 9
        System.out.println(examRoom.seat()); // 4
        System.out.println(examRoom.seat()); // 2
        examRoom.leave(0);
        System.out.println(examRoom.seat()); // 0, 0-2隔了一个座位，6-4也是隔了一个座位，但是0的座位号更小，所以选0。

        ExamRoom examRoom2 = new ExamRoom(4);
        System.out.println(examRoom2.seat()); // 0
        System.out.println(examRoom2.seat()); // 3
        System.out.println(examRoom2.seat()); // 1
        System.out.println(examRoom2.seat()); // 2
        examRoom2.leave(1);
        examRoom2.leave(3);
        System.out.println(examRoom2.seat()); // 1, 1小于3所以选1。
    }
}
