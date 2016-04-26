package me.thomas.knowledge.collection.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thomas on 4/26/16.
 */
public class MapMerge {

    public static void main(String[] args) {
        // 测试value是简单类型的场景
        Map<String, String> map1 = new HashMap<String, String>() {{
            put("a", "a1");
        }};

        Map<String, String> map2 = new HashMap<String, String>() {{
            put("a", "a2");
            put("b", "b2");
        }};

        map2.putAll(map1);
        System.out.println(map2); // print {a=a1, b=b2}

        // 测试value也是一个map的场景
        Map<String, String> map3 = new HashMap<String, String>() {{
            put("a", "a1");
        }};

        Map<String, String> map4 = new HashMap<String, String>() {{
            put("a", "a2");
            put("b", "b2");
        }};

        Map<String, Map<String, String>> map5 = new HashMap<>();
        map5.put("map", map3);

        Map<String, Map<String, String>> map6 = new HashMap<>();
        map6.put("map", map4);

        map6.putAll(map5);
        System.out.println(map6); // print {map={a=a1}} 而不是{map={{a=a1, b=b2}}}, 也就是说map的合并是同健覆盖原则
    }
}
