package me.thomas.knowledge.bpm.activiti.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author zhaoxinsheng
 * @date 5/17/16.
 */
public class TaskCreateListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("delegateTask.getEventName(): " + delegateTask.getEventName());
        System.out.println("delegateTask.getProcessDefinitionId(): " + delegateTask.getProcessDefinitionId());
        System.out.println("delegateTask.getTaskDefinitionKey(): " + delegateTask.getTaskDefinitionKey());
        System.out.println("delegateTask.getAssignee(): " + delegateTask.getAssignee());
    }

}
