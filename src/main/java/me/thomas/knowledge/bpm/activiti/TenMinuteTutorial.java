package me.thomas.knowledge.bpm.activiti;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TenMinuteTutorial {

    public static void main(String[] args) {

        String hr = "celine";

        // Create Activiti process engine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // Get Activiti services
        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService =  processEngine.getRuntimeService();
        TaskService taskService = processEngine.getTaskService();
        HistoryService historyService = processEngine.getHistoryService();

        // first task id
        String firstTaskId = null;
        // second task id
        String secondTaskId = null;

        // Deploy process
        String processDefinitionId = null;
        if (repositoryService.createProcessDefinitionQuery().processDefinitionKey("askForLeave").count() == 0) {
            processDefinitionId = repositoryService.createDeployment().addClasspathResource("askForLeave.bpmn").deploy().getId();
        } else {
            processDefinitionId = repositoryService.createProcessDefinitionQuery().processDefinitionKey("askForLeave").singleResult().getId();
        }

        // when start the workflow, the hr is set to "celine"
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("startUser", "thomas");
        variables.put("hr", hr);
        String processInstanceId = runtimeService.startProcessInstanceById(processDefinitionId, variables).getId();
        System.out.println("New instance under <askForLeave> => " + processInstanceId);

        // Find all instances which belong to the specific process definition key
        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().processDefinitionId(processDefinitionId).list();
        for (ProcessInstance instance : instances) {
            System.out.println("All instances under <askForLeave> => " + instance.getId());
            // here change "john" to do the second hr task
            hr = "john";
            Execution execution = runtimeService.createExecutionQuery().executionId(instance.getId()).singleResult();
            runtimeService.setVariable(execution.getId(), "hr", hr);
        }

        // Do the first task
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("manager").active().list();
        System.out.println("manager's tasks count => " + tasks.size());
        for (Task task : tasks) {
            Task task1 = taskService.createTaskQuery().taskId(task.getId()).singleResult();
            // claim it
            taskService.claim(task.getId(), "thomas");
        }
        // Verify that thomas can retrieve the task
        tasks = taskService.createTaskQuery().taskAssignee("thomas").active().list();
        System.out.println("thomas' tasks count => " + tasks.size());
        for (Task task : tasks) {
            firstTaskId = task.getId();
            System.out.println("first task.getId() => " + task.getId());
            // complete it
            variables = new HashMap<String, Object>();
            variables.put("approved", true);

            taskService.addComment(task.getId(), processInstanceId, "小伙子,干的漂亮.");
            taskService.createAttachment("application/docx", task.getId(), processInstanceId, "文案书.docx", "", "http://mybucket.s3.amazon.com/xx-swxs-csce");
            taskService.complete(task.getId(), variables);
        }

        tasks = taskService.createTaskQuery().taskAssignee("thomas").active().list();
        System.out.println("after complete tasks, thomas' tasks count = > " + tasks.size());

        // 找出当前节点
        Execution execution = runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(execution.getId());
        for (String activeActivityId : activeActivityIds) {
            System.out.println("activeActivityId => " + activeActivityId);
        }

        // Do the second task
        tasks = taskService.createTaskQuery().taskCandidateGroup("hr").active().list();
        for (Task task : tasks) {
            System.out.println("task.getId() => " + task.getId());
            // claim it
            taskService.claim(task.getId(), "celine");
        }
        // Verify that celine can retrieve the task
        tasks = taskService.createTaskQuery().taskAssignee(hr).active().list();
        System.out.println(hr + "'s tasks count => " + tasks.size());
        for (Task task : tasks) {
            secondTaskId = task.getId();
            System.out.println("second task.getId() => " + task.getId());
            // complete it
            taskService.complete(task.getId());
        }

        // 使用taskId似乎检索不出数据 -- ACT_HI_VARINST表中PROC_INST_ID_字段有数据,但是TASK_ID_字段无数据
        List<HistoricVariableInstance> firstTaskVariables = historyService.createHistoricVariableInstanceQuery().taskId(firstTaskId).list();
        for (HistoricVariableInstance variableInstance : firstTaskVariables) {
            System.out.println("first task: " +  variableInstance.getVariableName() + " = " + variableInstance.getValue());
        }

        List<HistoricVariableInstance> secondTaskVariables = historyService.createHistoricVariableInstanceQuery().taskId(secondTaskId).list();
        for (HistoricVariableInstance variableInstance : secondTaskVariables) {
            System.out.println("second task: " + variableInstance.getVariableName() + " = " + variableInstance.getValue());
        }

        // 使用processInstanceId就能查询出数据
        List<HistoricVariableInstance> historicVariableInstances = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
        for (HistoricVariableInstance variableInstance : historicVariableInstances) {
            System.out.println("process : " +  variableInstance.getVariableName() + " = " + variableInstance.getValue());
        }

    }

}
