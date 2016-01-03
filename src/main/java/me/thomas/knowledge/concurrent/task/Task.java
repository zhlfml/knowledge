package me.thomas.knowledge.concurrent.task;

/**
 * Task接口，用于定义可执行的任务。
 *
 * Created by Thomas on 2016/1/3.
 */
public interface Task {

    /**
     * 任务的执行逻辑
     *
     * @return 执行成功返回true，失败则返回false。
     */
    boolean execute();
}
