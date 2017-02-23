package me.thomas.knowledge.designpatterns.observer.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoxinsheng
 * @date 8/26/16.
 */
public class EventEmitter {

    private final Map<String, List<Listener>> events = new HashMap<>();
    private final Map<String, List<Listener>> onceEvents = new HashMap<>();

    public void on(String eventType, Listener listener) {
        if (!events.containsKey(eventType)) {
            events.put(eventType, new ArrayList<>());
        }
        events.get(eventType).add(listener);
    }

    public void one(String eventType, Listener listener) {
        if (!onceEvents.containsKey(eventType)) {
            onceEvents.put(eventType, new ArrayList<>());
        }
        onceEvents.get(eventType).add(listener);
    }

    public void emit(String eventType) {
        List<Listener> listeners = events.get(eventType);
        if (listeners != null && !listeners.isEmpty()) {
            listeners.forEach(listener -> listener.apply(null));
        }
        List<Listener> onceListeners = onceEvents.get(eventType);
        if (listeners != null && !listeners.isEmpty()) {
            onceListeners.forEach(listener -> listener.apply(null));
            onceListeners.clear();
        }
    }

    public void emit(String eventType, String parameter) {
        List<Listener> listeners = events.get(eventType);
        if (listeners != null && !listeners.isEmpty()) {
            listeners.forEach(listener -> listener.apply(parameter));
        }
        List<Listener> onceListeners = onceEvents.get(eventType);
        if (listeners != null && !listeners.isEmpty()) {
            onceListeners.forEach(listener -> listener.apply(parameter));
            onceListeners.clear();
        }
    }

    public static void main(String[] args) {
        EventEmitter emitter = new EventEmitter();
        // 函数接口，可以将函数作为参数传递。
        emitter.on("someEvent", arg1 ->  System.out.println("listener1 " + arg1));

        emitter.on("someEvent", arg1 -> System.out.println("listener2 " + arg1));
        emitter.one("someEvent", arg1 -> System.out.println("listener3 " + arg1));
        emitter.emit("someEvent");
        emitter.emit("someEvent", "triggered");
        emitter.emit("someEvent", "triggered second time");
    }
}
