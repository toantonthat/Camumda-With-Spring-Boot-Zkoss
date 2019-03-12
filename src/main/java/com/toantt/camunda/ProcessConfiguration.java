package com.toantt.camunda;

import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ProcessConfiguration {
	public static ProcessConfiguration instance;

	@Autowired
	@Lazy
	ProcessEngine processEngine;

	public final ProcessEngine getProcess() {
		return processEngine;
	}

	public ProcessConfiguration() {
		instance = this;
	}
}
