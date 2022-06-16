package me.thomas.knowledge.ref;

import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhaoxinsheng
 * @date 2020/03/09 Monday
 */
public class WeakReferenceTutorial {

    public static void main(String[] args) {
        ReferenceQueue<Garbage> queue = new ReferenceQueue<>();

        Set<MyWeakReference> weakGarbageList = new HashSet<>(1000);

        int i = 0;
        while (true) {
            Garbage garbage = new Garbage(i++);
            weakGarbageList.add(new MyWeakReference(garbage, queue));
//            int before = weakGarbageList.size();
//            boolean removed = false;
//            MyWeakReference o;
//            while ((o = (MyWeakReference) queue.poll()) != null) {
//                removed = true;
//                System.out.println("Remove garbage " + o.getGarbageId());
//                weakGarbageList.remove(o);
//                o.cleanUp();
//            }
//            int after = weakGarbageList.size();
//            if (removed) {
//                System.out.println("before " + before);
//                System.out.println("after " + after);
//            }
        }
    }
}
