package me.thomas.knowledge.bpm.activiti.parse;

import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;

import java.util.*;

/**
 * @author zhaoxinsheng
 * @date 5/30/16.
 */
public class ParseActivitiFlow {

    public static void main(String[] args) {
        // Create Activiti process engine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // Get Activiti services
        RepositoryService repositoryService = processEngine.getRepositoryService();

        // Deploy process
        String processDefinitionId = null;
        if (repositoryService.createProcessDefinitionQuery().processDefinitionKey("demoForParse").count() == 0) {
            processDefinitionId = repositoryService.createDeployment().addClasspathResource("demoForParse.bpmn").deploy().getId();
        } else {
            processDefinitionId = repositoryService.createProcessDefinitionQuery().processDefinitionKey("demoForParse").singleResult().getId();
        }

        List<Process> processes = repositoryService.getBpmnModel(processDefinitionId).getProcesses();

        if (processes == null && processes.isEmpty()) {
            return;
        }

        Process process = processes.get(0);

        Collection<FlowElement> flowElements = process.getFlowElements();

        // 遍历节点
        Iterator<FlowElement> iterator = flowElements.iterator();

        Map<String, FlowElement> nodeMap = new HashMap<>();
        // 把流程节点设置到map中
        while (iterator.hasNext()) {
            FlowElement element = iterator.next();
            if (element instanceof StartEvent) {
                nodeMap.put("startEvent", element);
            } else {
                nodeMap.put(element.getId(), element);
            }
        }

        Map<String, Map<String, Integer>> relationMap = new HashMap<>();

        // 从start节点开始
        setFlowNodeRel("", "startEvent", nodeMap, relationMap);

        System.out.println(relationMap);
    }

    // 根据节点ID设置关系
    public static void setFlowNodeRel(String nodeFlowPath, String nodeId, Map<String, FlowElement> nodeMap, Map<String, Map<String, Integer>> relationMap) {
        FlowElement element = nodeMap.get(nodeId);

        if (!(element instanceof FlowNode)) {
            return;
        }

        List<SequenceFlow> outFlows = ((FlowNode) element).getOutgoingFlows();
        if (outFlows.isEmpty()) {
            setRelationMap(nodeFlowPath, nodeMap, relationMap);
            return;
        }

        // 采用深度优先遍历
        for (int i = 0; i < outFlows.size(); i++) {
            SequenceFlow sequenceFlow = outFlows.get(i);
            String target = sequenceFlow.getTargetRef();

            // 忽略流程跳转到之前的节点的场景
            if (nodeFlowPath.contains("#" + target + "#")) {
                setRelationMap(nodeFlowPath, nodeMap, relationMap);
                return;
            }

            // 递归找下一个任务的关系
            setFlowNodeRel(nodeFlowPath + "#" + target, target, nodeMap, relationMap);
        }
    }

    public static void setRelationMap(String nodeFlowPath, Map<String, FlowElement> nodeMap, Map<String, Map<String, Integer>> relationMap) {
        System.out.println("output: " + nodeFlowPath);
        List<String> userTaskIds = new ArrayList<>();
        String[] nodeIds = nodeFlowPath.split("#");
        // the first node is startEvent, so i begin from 1 here.
        for (int i = 1; i < nodeIds.length; i++) {
            FlowElement node = nodeMap.get(nodeIds[i]);
            if (node instanceof UserTask) {
                userTaskIds.add(nodeIds[i]);
            }
        }

        for (int i = 0; i < userTaskIds.size(); i++) {
            Map<String, Integer> map = new HashMap<>();
            for (int j = i + 1; j < userTaskIds.size(); j++) {
                map.put(userTaskIds.get(j), j - i);
            }
            relationMap.put(userTaskIds.get(i), map);
        }
    }

}
