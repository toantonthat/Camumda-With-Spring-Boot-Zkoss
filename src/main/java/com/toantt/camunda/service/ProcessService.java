package com.toantt.camunda.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.zkoss.bind.annotation.Command;

import com.toantt.camunda.ProcessConfiguration;

public class ProcessService {
	private double diemToan;
	private double diemHoa;
	private double diemLy;
	
	public String businessKey() {
		return getClass().getName() + "@" + Math.random();
	}

	public double getDiemToan() {
		return diemToan;
	}

	public void setDiemToan(double diemToan) {
		this.diemToan = diemToan;
	}

	public double getDiemHoa() {
		return diemHoa;
	}

	public void setDiemHoa(double diemHoa) {
		this.diemHoa = diemHoa;
	}

	public double getDiemLy() {
		return diemLy;
	}

	public void setDiemLy(double diemLy) {
		this.diemLy = diemLy;
	}
	
	public ProcessConfiguration srv() {
		return ProcessConfiguration.instance;
	}

	public Task getCurrentTask() {
		List<Task> listPage = srv().getProcess().getTaskService().createTaskQuery().processInstanceBusinessKey(businessKey())
				.orderByTaskCreateTime().desc().listPage(0, 1);
		return listPage.isEmpty() ? null : listPage.get(0);
	}

	@Command
	public void doAction() {
		System.out.println("Toan : " + diemToan);
		System.out.println("businessKey " + businessKey());
		System.out.println("srv " + srv());
		System.out.println("getProcess " + srv().getProcess());

		Task task = null;
		if (getCurrentTask() == null) {
			Map<String, Object> variables = new HashMap<>();
			variables.put("user", "ToanTT");
			ProcessInstance processInstance = srv().getProcess()
					.getRuntimeService()
					.startProcessInstanceByKey("StudentFinalExamination", businessKey(), variables);
			System.out.println("processInstance " + processInstance);
			
			task = srv().getProcess().getTaskService().createTaskQuery().processInstanceId(processInstance.getId())
					.singleResult();
			
			System.out.println("task " + task);
		}
	}
}
