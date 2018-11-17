package me.thomas.knowledge.gc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author zhaoxinsheng
 * @date 2018/9/16.
 */
public class MyArrayList<E> extends ArrayList<E> {

    private String name;

    public MyArrayList(String name) {
        super();
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("gc " + name);
    }

    public static void main(String[] args) throws Exception {
        List<String> myArrayList = new MyArrayList<>("first");
        myArrayList.add("1");
        myArrayList.add("2");
        myArrayList.add("1");
//        myArrayList = null;
        myArrayList = myArrayList.stream().distinct().collect(Collectors.toList());
        System.gc();

        System.out.println("wait for gc...");
        TimeUnit.SECONDS.sleep(3);
    }
}
