package me.thomas.knowledge.algorithm.leetcode.design.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 460.
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 * <p>
 * 实现 LFUCache 类：
 * <p>
 * LFUCache(int capacity) - 用数据结构的容量capacity 初始化对象
 * int get(int key)- 如果键key 存在于缓存中，则获取键的值，否则返回 -1 。
 * void put(int key, int value)- 如果键key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 * <p>
 * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 * <p>
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 *
 * @author xinsheng2.zhao
 * @version Id: LFUCache.java, v 0.1 2022/6/27 20:36 xinsheng2.zhao Exp $
 */
public class LFUCache {

    private final Map<Integer, Node> map;
    private final Heap               heap;
    private final int                capacity;

    public LFUCache(int capacity) {
        this.map = new HashMap<>((int) (capacity / .75 + 1));
        this.heap = new Heap(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        Node cache = map.get(key);
        if (cache == null) {
            return -1;
        }
        access(cache);
        return cache.value;
    }

    public void put(int key, int value) {
        Node cache = map.get(key);
        if (cache != null) {
            cache.value = value;
            access(cache);
            return;
        }
        if (heap.size() == capacity) {
            deleteLeastHitCache();
        }
        addNewCache(new Node(key, value));
    }

    private void access(Node node) {
        node.hitCount++;
        node.accessTime = System.currentTimeMillis();
        heap.siftDown(node.index);
    }

    /**
     * 易错点：heap和map的状态必须一致，只有缓存正确放入heap后，才能设置map
     */
    private void addNewCache(Node cache) {
        if (heap.add(cache)) {
            map.put(cache.key, cache);
        }
    }

    private void deleteLeastHitCache() {
        Node oldestCache = heap.removeRoot();
        /*
         * capacity为0时，oldestCache为null
         */
        if (oldestCache != null) {
            map.remove(oldestCache.key);
        }
    }

    /**
     * 缓存的基本单位 -- node
     */
    static class Node implements Comparable<Node> {
        /**
         * 缓存的key
         */
        int  key;
        /**
         * 缓存的value
         */
        int  value;
        /**
         * 命中的次数
         */
        int  hitCount;
        /**
         * 最后一次访问时间
         */
        long accessTime;
        /**
         * 位于堆中的下标
         */
        int  index;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.hitCount = 1;
            this.accessTime = System.currentTimeMillis();
            this.index = -1;
        }

        /**
         * 排序规则：先命中次数升序后访问时间升序
         *
         * @param another the object to be compared.
         */
        @Override public int compareTo(Node another) {
            if (this.hitCount < another.hitCount) {
                return -1;
            } else if (this.hitCount > another.hitCount) {
                return 1;
            } else {
                return Long.compare(this.accessTime, another.accessTime);
            }
        }
    }

    /**
     * 最小堆数据结构，根节点的下标从0开始。
     */
    static class Heap {
        Node[] nodes;

        int size;

        public Heap(int capacity) {
            this.nodes = new Node[capacity];
        }

        public Node get(int index) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("index out range of size.");
            }
            return nodes[index];
        }

        /**
         * 向堆中添加数据
         */
        public boolean add(Node node) {
            if (size == nodes.length) {
                return false;
            }
            nodes[size] = node;
            nodes[size].index = size;
            size++;
            siftUp(size - 1);
            return true;
        }

        /**
         * 从堆中删除根节点
         */
        public Node removeRoot() {
            if (size == 0) {
                return null;
            }
            Node node = nodes[0];
            swap(0, --size);
            siftDown(0);
            return node;
        }

        public int size() {
            return size;
        }

        /**
         * 当节点小于父节点时与父节点交换，直到根节点。
         * 场景：新增加的数据向上冒泡到合适的位置
         */
        private void siftUp(int child) {
            if (child >= size) {
                throw new IllegalArgumentException("child out range of size.");
            }
            while (child > 0) {
                int parent = parent(child);
                if (nodes[child].compareTo(nodes[parent]) >= 0) {
                    break;
                }
                swap(child, parent);
                child = parent;
            }
        }

        /**
         * 场景：缓存命中增加访问次数后，下沉到合适的位置 -- heapify
         */
        private void siftDown(int parent) {
            if (parent < 0 || parent >= size) {
                return;
            }
            int left = leftChild(parent), right = rightChild(parent);
            int min = parent;
            if (left < size && nodes[left].compareTo(nodes[min]) <= 0) {
                min = left;
            }
            if (right < size && nodes[right].compareTo(nodes[min]) <= 0) {
                min = right;
            }
            if (min != parent) {
                swap(parent, min);
                siftDown(min);
            }
        }

        /**
         * 返回堆中子节点的父节点下标
         *
         * @param index 堆中子节点的下标
         * @return 父节点下标
         */
        private int parent(int index) {
            return (index - 1) >> 1;
        }

        /**
         * 返回堆中父节点的左孩子下标
         *
         * @param index 堆中父节点的下标
         * @return 左孩子下标
         */
        private int leftChild(int index) {
            return (index << 1) + 1;
        }

        /**
         * 返回堆中父节点的右孩子下标
         *
         * @param index 堆中父节点的下标
         * @return 右孩子下标
         */
        private int rightChild(int index) {
            return (index << 1) + 2;
        }

        private void swap(int i, int j) {
            Node node = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = node;
            nodes[i].index = i;
            nodes[j].index = j;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LFUCache cache = new LFUCache(0);
        cache.put(0, 0);
        System.out.println(cache.get(0));
    }

}
