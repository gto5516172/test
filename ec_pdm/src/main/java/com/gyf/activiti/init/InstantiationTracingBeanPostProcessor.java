package com.gyf.activiti.init;

import java.util.List;
import javax.annotation.Resource;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.gyf.activiti.utils.ActivitiConstant;

public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired  
    RepositoryService repositoryService;
	
	@Resource
    RuntimeService runtimeService;
    
    @Resource
    TaskService taskService;
    
    @Resource
    HistoryService historyServices;
    
    @Resource
    ProcessEngine processEngine;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if(event.getApplicationContext().getParent() == null){
			//部署流程定义
	    	Deployment deployment = repositoryService.createDeployment().addClasspathResource("activiti/commodity.bpmn").deploy();
	    	ActivitiConstant.deploymentMap.put("deploymentMy", deployment);
	        
	    	//查询流程定义
	      	/*ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
	      	Long businessKey=new Double(1000000*Math.random()).longValue();
			//启动流程
			runtimeService.startProcessInstanceById(processDefinition.getId(),businessKey.toString());
			//查询任务实例
			List<Task> taskList=taskService.createTaskQuery().processDefinitionId(processDefinition.getId()).list();
			for(Task task:taskList){
				System.out.println("task name is " + task.getName() + " ,task key is " + task.getTaskDefinitionKey());
			}*/
		}
	}

}
