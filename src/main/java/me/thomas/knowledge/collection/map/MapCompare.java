package me.thomas.knowledge.collection.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * http://www.cnblogs.com/hubingxu/archive/2012/02/21/2361281.html
 *
 * Created by zhaoxs on 16-4-7.
 */
public class MapCompare {

    public static void main(String[] args) {
        /**
         * Hashmap 是一个最常用的Map,它根据键的HashCode值存储数据,根据键可以直接获取它的值，
         * 具有很快的访问速度，遍历时，取得数据的顺序是完全随机的。 HashMap最多只允许一条记录的键为Null;
         * 允许多条记录的值为 Null;HashMap不支持线程的同步，即任一时刻可以有多个线程同时写HashMap;
         * 可能会导致数据的不一致。如果需要同步，可以用 Collections的synchronizedMap方法使HashMap具有同步的能力，
         * 或者使用ConcurrentHashMap。
         */
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("p", 100);
        hashMap.put("q", 300);
        hashMap.put("b", 200);
        System.out.println("hashMap = " + hashMap.toString());

        /**
         * TreeMap实现SortMap接口，能够把它保存的记录根据键排序,默认是按键值的升序排序，也可以指定排序的比较器，
         * 当用Iterator 遍历TreeMap时，得到的记录是排过序的。
         */
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("p", 100);
        treeMap.put("q", 300);
        treeMap.put("b", 200);
        System.out.println("treeMap = " + treeMap.toString());

        /**
         * LinkedHashMap 是HashMap的一个子类，保存了记录的插入顺序，在用Iterator遍历LinkedHashMap时，
         * 先得到的记录肯定是先插入的.也可以在构造时用带参数，按照应用次数排序。
         */
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("p", 100);
        linkedHashMap.put("q", 300);
        linkedHashMap.put("b", 200);
        System.out.println("linkedHashMap = " + linkedHashMap.toString());
    }
}
