package me.thomas.knowledge.gc;

import java.util.concurrent.TimeUnit;

/**
 * @author zhaoxinsheng
 * @date 2018/9/16.
 */
public class GCObject {

    private String name;

    public GCObject(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("gc " + name);
    }

    public static void main(String[] args) throws Exception {
        GCObject pointer = new GCObject("first");
        System.out.println(pointer.name);
        pointer = new GCObject("second");
        System.out.println(pointer.name);
        System.gc();
        System.out.println("wait for gc...");
        TimeUnit.SECONDS.sleep(3);
    }
}
