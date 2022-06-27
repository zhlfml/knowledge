package me.thomas.knowledge.algorithm.leetcode.design.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 146.
 * 请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。如果插入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 *
 * @author xinsheng2.zhao
 * @version Id: LRUCache.java, v 0.1 2022/6/27 20:35 xinsheng2.zhao Exp $
 */
public class LRUCache {

    /**
     * map.value存放的是Node对象而非int类型的值
     * 可以避免在从doubleLinkedList中删除Node对象时需要遍历O(n)次查找时间
     */
    private final Map<Integer, Node> map;
    /**
     * 维护head和tail两个虚Node对象：越靠近头部越长时间未访问，越靠近尾部越近期访问
     */
    private final DoubleLinkedList   doubleLinkedList;
    private final int                capacity;
    /**
     * 存放的缓存的条目数量
     */
    private       int                size;

    public LRUCache(int capacity) {
        this.map = new HashMap<>((int) (capacity / .75) + 1);
        this.doubleLinkedList = new DoubleLinkedList();
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        makeRecently(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node exist = map.get(key);
        if (exist != null) {
            delete(exist);
        } else if (size == capacity) {
            delete(doubleLinkedList.head.next);
        } else {
            size++;
        }
        addRecently(new Node(key, value));
    }

    /**
     * 查 - 设置为最近使用
     */
    private void makeRecently(Node node) {
        doubleLinkedList.deleteNode(node);
        doubleLinkedList.addTail(node);
    }

    /**
     * 增，改
     */
    private void addRecently(Node node) {
        map.put(node.key, node);
        doubleLinkedList.addTail(node);
    }

    /**
     * 删
     */
    private void delete(Node node) {
        map.remove(node.key);
        doubleLinkedList.deleteNode(node);
    }

    static class Node {
        int  key;
        int  value;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    static class DoubleLinkedList {
        Node head;
        Node tail;

        public DoubleLinkedList() {
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            this.head.next = this.tail;
            this.tail.prev = this.head;
        }

        /**
         * 从双链表中删除节点
         */
        void deleteNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = node.next = null;
        }

        /**
         * 向双链表尾部插入节点
         */
        void addTail(Node node) {
            node.prev = this.tail.prev;
            node.next = this.tail;
            node.prev.next = node;
            node.next.prev = node;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
