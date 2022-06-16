package me.thomas.knowledge.algorithm.leetcode;

import java.util.*;

/**
 * RandomizedSet
 *
 * @author xinsheng2.zhao
 * @version Id: RandomizedSet.java, v 0.1 2022/6/16 20:43 xinsheng2.zhao Exp $
 */
public class RandomizedSet {

    private List<Integer>         list;
    private Map<Integer, Integer> map;
    private Random                random;

    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        Integer pos = map.get(val);
        if (Objects.nonNull(pos)) {
            return false;
        }
        // 将数字插入数组末尾
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        Integer pos = map.get(val);
        if (Objects.isNull(pos)) {
            return false;
        }
        int last = list.size() - 1;
        Integer lastVal = list.remove(last);
        /*
         * 只有当删除的不是最后一个元素时，对调最后一个元素和被删除的元素的位置，记得要同步更新map哦。
         */
        if (pos != last) {
            list.set(pos, lastVal);
            map.put(lastVal, pos);
        }
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
