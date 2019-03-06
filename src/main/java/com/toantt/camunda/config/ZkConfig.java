package com.toantt.camunda.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zkoss.zk.au.http.DHtmlUpdateServlet;
import org.zkoss.zk.ui.http.DHtmlLayoutServlet;

@Configuration
public class ZkConfig {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ServletRegistrationBean dHtmlLayoutServlet() {
		Map<String, String> params = new HashMap<>();
		params.put("update-uri", "/zkau");
		DHtmlLayoutServlet dHtmlLayoutServlet = new DHtmlLayoutServlet();
		ServletRegistrationBean reg = new ServletRegistrationBean(dHtmlLayoutServlet, "*.zul");
		reg.setLoadOnStartup(1);
		reg.setInitParameters(params);
		return reg;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ServletRegistrationBean dHtmlUpdateServlet() {
		Map<String, String> params = new HashMap<>();
		params.put("update-uri", "/zkau/*");
		ServletRegistrationBean reg = new ServletRegistrationBean(new DHtmlUpdateServlet(), "/zkau/*");
		reg.setLoadOnStartup(2);
		reg.setInitParameters(params);
		return reg;
	}
}
