package me.thomas.knowledge.json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import me.thomas.knowledge.utils.FileUtil;

/**
 * @author zhaoxinsheng
 * @date 2018/7/6.
 */
public class CompareJson {

    public static void main(String[] args) throws Exception {
        JsonParser jsonParser = new JsonParser();
        JsonObject first = (JsonObject) jsonParser.parse(FileUtil.read("/Users/thomas/Desktop/node.json"));
        JsonObject second = (JsonObject) jsonParser.parse(FileUtil.read("/Users/thomas/Desktop/java.json"));
        System.out.println(first.equals(second));
    }
}
