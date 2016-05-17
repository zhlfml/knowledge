package me.thomas.knowledge.bpm.activiti.handler;

import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.bpmn.model.ImplementationType;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.bpmn.parser.handler.UserTaskParseHandler;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.task.TaskDefinition;

/**
 * @author zhaoxinsheng
 * @date 5/17/16.
 */
public class MyUserTaskParseHandler extends UserTaskParseHandler {

    @Override
    public TaskDefinition parseTaskDefinition(BpmnParse bpmnParse, UserTask userTask, String taskDefinitionKey, ProcessDefinitionEntity processDefinition) {
        TaskDefinition taskDefinition =  super.parseTaskDefinition(bpmnParse, userTask, taskDefinitionKey, processDefinition);

        ActivitiListener taskCreateListener = new ActivitiListener();
        taskCreateListener.setEvent("create");
        taskCreateListener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
        taskCreateListener.setImplementation("me.thomas.knowledge.bpm.activiti.listener.TaskCreateListener");
        taskDefinition.addTaskListener(taskCreateListener.getEvent(), createTaskListener(bpmnParse, taskCreateListener, userTask.getId()));

        ActivitiListener taskCompleteListener = new ActivitiListener();
        taskCompleteListener.setEvent("complete");
        taskCompleteListener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
        taskCompleteListener.setImplementation("me.thomas.knowledge.bpm.activiti.listener.TaskCompleteListener");
        taskDefinition.addTaskListener(taskCompleteListener.getEvent(), createTaskListener(bpmnParse, taskCompleteListener, userTask.getId()));

        return taskDefinition;
    }
}
