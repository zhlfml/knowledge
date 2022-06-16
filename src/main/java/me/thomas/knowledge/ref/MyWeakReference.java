package me.thomas.knowledge.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author zhaoxinsheng
 * @date 2020/03/09 Monday
 */
public class MyWeakReference extends WeakReference<Garbage> {

    private int garbageId;

    public MyWeakReference(Garbage referent) {
        super(referent);
        this.garbageId = referent.getId();
    }

    public MyWeakReference(Garbage referent, ReferenceQueue<Garbage> q) {
        super(referent, q);
        this.garbageId = referent.getId();
    }

    public void cleanUp() {
        this.garbageId = 0;
    }

    public int getGarbageId() {
        return garbageId;
    }
}
