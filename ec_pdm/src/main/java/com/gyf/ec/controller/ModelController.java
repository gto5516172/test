package com.gyf.ec.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gohuinuo.web.sys.utils.SysUserUtils;
import com.gyf.activiti.utils.ActivitiConstant;

	/** 
 * 流程模型控制器 
 * 
 * @author henryyan 
 */  
@Controller  
@RequestMapping(value = "/model")  
public class ModelController {  
  
    protected Logger logger = LoggerFactory.getLogger(getClass());  
  
    @Autowired  
    RepositoryService repositoryService;
    
    @Resource
    RuntimeService runtimeService;
    
    @Resource
    TaskService taskService;
    
    @Resource
    HistoryService historyService;
    
    @Resource
    ProcessEngine processEngine;
    
    @Resource
    IdentityService identityService;
    
    
    /** 
     * 模型列表 
     */
    /*
    @ResponseBody  
    @RequestMapping("list")  
    public DataGrid<Model> list() {  
        List<Model> list = repositoryService.createModelQuery().list();  
        DataGrid<Model> dg = new DataGrid<Model>((long)list.size(),list);  
        return dg;  
    } */ 
  
    /** 
     * 创建模型 
     */  
    @RequestMapping("create")  
    public void create(String name,String key, String description, HttpServletRequest request, HttpServletResponse response) {  
        try {  
            ObjectMapper objectMapper = new ObjectMapper();  
            ObjectNode editorNode = objectMapper.createObjectNode();  
            editorNode.put("id", "canvas");  
            editorNode.put("resourceId", "canvas");  
            ObjectNode stencilSetNode = objectMapper.createObjectNode();  
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");  
            editorNode.put("stencilset", stencilSetNode);  
            Model modelData = repositoryService.newModel();  
  
            ObjectNode modelObjectNode = objectMapper.createObjectNode();  
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);  
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);  
            description = StringUtils.defaultString(description);  
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);  
            modelData.setMetaInfo(modelObjectNode.toString());  
            modelData.setName(name);  
            modelData.setKey(StringUtils.defaultString(key));  
  
            //保存模型  
            repositoryService.saveModel(modelData);  
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));  
              
            response.sendRedirect(request.getContextPath() + "/service/editor?id=" + modelData.getId());  
        } catch (Exception e) {  
            logger.error("创建模型失败：", e);  
        }  
    }  
  
    /** 
     * 根据Model部署流程 
     */ 
    /*
    @ResponseBody  
    @RequestMapping("deploy")  
    public Json deploy(String modelId) {  
        Json json = new Json();  
        try {  
            Model modelData = repositoryService.getModel(modelId);  
            ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));  
            byte[] bpmnBytes = null;  
  
            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);  
            bpmnBytes = new BpmnXMLConverter().convertToXML(model);  
  
            String processName = modelData.getName() + ".bpmn20.xml";  
            Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes)).deploy();  
            json.setMsg("部署成功，部署ID=" + deployment.getId());  
            json.setSuccess(true);  
        } catch (Exception e) {  
            logger.error("根据模型部署流程失败：modelId={}", modelId, e);  
            json.setMsg("模型部署流程失败");  
        }  
        return json;  
    } */ 
  
    /** 
     * 导出model的xml文件 
     */  
    @RequestMapping("export")  
    public void export(String modelId, HttpServletResponse response) {  
        try {  
            Model modelData = repositoryService.getModel(modelId);  
            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();  
            JsonNode editorNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));  
            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);  
            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();  
            byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);  
  
            ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);  
            IOUtils.copy(in, response.getOutputStream());  
            String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";  
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);  
            response.flushBuffer();  
        } catch (Exception e) {  
            logger.error("导出model的xml文件失败：modelId={}", modelId, e);  
        }  
    }  
  
    @RequestMapping(value = "delete/{modelId}")  
    public String delete(@PathVariable("modelId") String modelId) {  
        repositoryService.deleteModel(modelId);  
        return "redirect:/workflow/model/list";  
    }
    
    /** 
     * 部署流程并启动流程
     */  
    @RequestMapping("mytest")  
    public void mytest() {  
    	//部署流程定义
    	Deployment deployment = repositoryService.createDeployment().addClasspathResource("activiti/holidayRequest.bpmn").deploy();
        //查询流程定义
      	ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
      	Long businessKey=new Double(1000000*Math.random()).longValue();
		//启动流程
		runtimeService.startProcessInstanceById(processDefinition.getId(),businessKey.toString());
		//查询任务实例
		List<Task> taskList=taskService.createTaskQuery().processDefinitionId(processDefinition.getId()).list();
		for(Task task:taskList){
			System.out.println("task name is " + task.getName() + " ,task key is " + task.getTaskDefinitionKey());
		}
    }
    
    /**
     * 查询历史流程实例
     */
    @RequestMapping("findHistoryProcessInstance")
    public void findHistoryProcessInstance() {
    	String processInstanceId="10005";  
        HistoricProcessInstance hpi = processEngine.getHistoryService()  
                .createHistoricProcessInstanceQuery()  
                .processInstanceId(processInstanceId)  
                .singleResult();  
        if(hpi != null) {
        	System.out.println(hpi.getId() +"    "+hpi.getProcessDefinitionId()+"   "+ hpi.getStartTime()+"   "+hpi.getDurationInMillis());
        }
    }
    
    /**
     * 任务办理
     */
    @RequestMapping("claimMyPersonTask")
    public void claimMyPersonTask() {
    	List<Task> list = processEngine.getTaskService().createTaskQuery().list();
    	if(list != null && list.size() > 0) {
    			for(Task task : list) {
    				taskService.claim(task.getId(), "34");
    			}
    	}
    }
    
    /**
     * 任务完成
     */
    @RequestMapping("completeMyPersonTask")
    public void completeMyPersonTask(String taskId) {
    	List<Task> list = processEngine.getTaskService().createTaskQuery().list();
    	if(list != null && list.size() > 0) {
    			for(Task task : list) {
    				taskService.complete(task.getId());
    			}
    	}
    }
    
    
    @RequestMapping("findMyPersonTask")
    public void findMyPersonTask() {
    	String assignee = "张三"; 
    	List<Task> list = processEngine.getTaskService()// 与正在执行的任务管理相关的service 
    		.createTaskQuery()// 创建任务查询对象 
    		// 查询条件  
           // .taskAssignee(assignee)// 指定个人任务查询，指定办理人  
            // .taskCandidateGroup("")//组任务的办理人查询  
            // .processDefinitionId("")//使用流程定义ID查询  
            // .processInstanceId("")//使用流程实例ID查询  
            // .executionId(executionId)//使用执行对象ID查询  
            /** 排序 */  
            .orderByTaskCreateTime().asc()// 使用创建时间的升序排列  
            // 返回结果集  
            // .singleResult() //返回唯一的结果集  
            // .count()//返回结果集的数量  
            // .listPage(firstResult, maxResults)//分页查询  
            .list();// 返回列表  
    	if (list != null && list.size() > 0) {  
            for (Task task : list) {  
                System.out.println("任务ID：" + task.getId());  
                System.out.println("任务名称:" + task.getName());  
                System.out.println("任务的创建时间:" + task.getCreateTime());  
                System.out.println("任务的办理人:" + task.getAssignee());  
                System.out.println("流程实例ID:" + task.getProcessInstanceId());  
                System.out.println("执行对象ID:" + task.getExecutionId());  
                System.out.println("流程定义ID:" + task.getProcessDefinitionId());  
                System.out  
                        .println("##################################################");  
            } 
    	}
    }
    
    /**
     * 部署实例
     */
    @RequestMapping("deploymentProcessDefinition_inputStream")
    public void deploymentProcessDefinition_inputStream() {  
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();  
      
        InputStream inputStreamBpmn = this.getClass().getResourceAsStream(  
                "/activiti/processVariables.bpmn");  
        InputStream inputStreamPng = this.getClass().getResourceAsStream(  
                "/activiti/processVariables.png");  
        Deployment deployment = processEngine.getRepositoryService()// 与流程定义和部署对象相关的Service  
                .createDeployment()// 创建一个部署对象  
                .name("流程定义")// 添加部署名称  
                .addInputStream("processVariables.bpmn", inputStreamBpmn)// 使用资源文件的名称（要求:与资源文件的名称要一致），和输入流完成部署  
                .addInputStream("processVariables.png", inputStreamPng)// 使用资源文件的名称(要求:与资源文件的名称要一致)，和输入流完成部署  
                .deploy();// 完成部署  
        System.out.println("部署ID：" + deployment.getId());  
        System.out.println("部署名称：" + deployment.getName());  
    }  
    
    @RequestMapping("startProcessInstance")
    public void startProcessInstance() {  
        // 流程定义的key  
        String processDefinitionKey = "processVariables";  
        ProcessInstance pi = processEngine.getRuntimeService()// 与正在执行的流程实例和执行对象相关的service  
                .startProcessInstanceByKey(processDefinitionKey);// 使用流程定义的key启动流程实例，key对应processVariables文件中的id的属性值，使用key值启动，默认是按照最新版本进行启动  
  
        System.out.println("流程实例ID：" + pi.getId());  
        System.out.println("流程定义ID：" + pi.getProcessDefinitionId());  
        System.out.println("流程实例ID" + pi.getProcessInstanceId());  
  
    }  
    
    /** 
     * 查询任务通过流程实例id 
     */  
    @RequestMapping("findTask")
    public void findTask(String processInstanceId){  
        List<HistoricTaskInstance> list = processEngine.getHistoryService()//与历史数据（历史表）相关的service  
                .createHistoricTaskInstanceQuery()//创建历史任务实例查询  
                .processInstanceId(processInstanceId)  
                .list();  
        if(list!=null && list.size()>0){  
            for(HistoricTaskInstance hti:list){  
                System.out.println(hti.getId()+"    "+hti.getName()+"    "+hti.getProcessInstanceId()+"   "+hti.getStartTime()+"   "+hti.getEndTime()+"   "+hti.getDurationInMillis());  
                System.out.println("################################");  
            }  
        }     
    }  
    
    /** 
     * 设置流程变量 
     */  
    @RequestMapping("setVariables")
    public void setVariables(String taskId) {  
        // 与任务相关的service,正在执行的service  
        TaskService taskService = processEngine.getTaskService();
        // 1.设置流程变量，使用基本数据类型  
        taskService.setVariable(taskId, "请假天数", 7);// 与任务ID邦德  
        taskService.setVariable(taskId, "请假日期", new Date());  
        taskService.setVariableLocal(taskId, "请假原因", "回去探亲，一起吃个饭123");  
        System.out.println("设置流程变量成功！");  
    }  
    
    /** 
     * 获取流程变量 
     */  
   @ RequestMapping("getVariables")
    public void getVariables(String taskId) {  
        // 与任务（正在执行的service）  
        TaskService taskService = processEngine.getTaskService();  
        // 任务Id  
        // 1.获取流程变量，使用基本数据类型  
        Integer days = (Integer) taskService.getVariable(taskId, "请假天数");  
        Date date = (Date) taskService.getVariable(taskId, "请假日期");  
        String reason = (String) taskService.getVariable(taskId, "请假原因");  
        System.out.println("请假天数：" + days);  
        System.out.println("请假日期：" + date);  
        System.out.println("请假原因：" + reason);  
    }
   
   @RequestMapping("findHistoryProcessVariables")
   public void findHistoryProcessVariables(){  
	    List<HistoricVariableInstance> list = processEngine.getHistoryService()  
	            .createHistoricVariableInstanceQuery()//创建一个历史的流程变量查询对象  
	            .variableName("请假原因")  
	            .list();  
	    if (list!=null &&list.size()>0) {  
	        for (HistoricVariableInstance hvi : list) {  
	            System.out.println(hvi.getId()+"     "+hvi.getProcessInstanceId()+"   "+hvi.getVariableName()  
	                    +"   "+hvi.getVariableTypeName()+"    "+hvi.getValue());  
	            System.out.println("########################################");  
	        }  
	    }  
   }
   
   
   @RequestMapping("startActiviti")
   public void startActiviti() {
	   Deployment dep = ActivitiConstant.deploymentMap.get("deploymentMy");
	   Map<String, Object> variables = new HashMap<String, Object>();  
	   long userId = SysUserUtils.getCacheLoginUser().getId();
       variables.put("userID", userId);
       variables.put("ecTask", "做下测试");
	   ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("myProcess" , variables);
	   identityService.setAuthenticatedUserId(userId + "");
	   System.out.println("流程实例ID:" + pi.getId());  
	   System.out.println("流程定义ID:" + pi.getProcessDefinitionId()); 
   }
   
   @RequestMapping("queryActivitiTask")
   public void queryActivitiTask() {
	   long userId = SysUserUtils.getCacheLoginUser().getId();
	   List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId + "").list();
	   for(int i = 0; i < tasks.size(); i++) {
		   Task task = tasks.get(i);
		   Map<String,Object> vars = taskService.getVariables(task.getId());
		   
		   System.out.println("执行任务ID：" + task.getId() + ";任务名称:" + task.getName() + ";流程变量:" + vars.get("ecTask"));
	   }
   }
   
   @RequestMapping("completeTask")
   public void completeTask(String taskId, String input) {
	   Map<String, Object> variables = new HashMap<String, Object>();
	   variables.put("input", input);
	   taskService.complete(taskId, variables);
	   System.out.println("完成任务");
   }
   
   @RequestMapping("gaipai")
   public void gaipai(String taskId, String userId) {
	   processEngine.getTaskService().setAssignee(taskId, userId);
	   List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId + "").list();
	   System.out.println("改派成功");
	   for(int i = 0; i < tasks.size(); i++) {
		   Task task = tasks.get(i);
		   System.out.println("执行任务ID：" + task.getId() + ";任务名称:" + task.getName());
	   }
   }
   
   @RequestMapping("queryActivitiTaskByUserId")
   public void queryActivitiTaskByUserId(String userId) {
	   List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId + "").list();
	   for(int i = 0; i < tasks.size(); i++) {
		   Task task = tasks.get(i);
		   System.out.println("执行任务ID：" + task.getId() + ";任务名称:" + task.getName());
	   }
   }
   
   
    
}  
