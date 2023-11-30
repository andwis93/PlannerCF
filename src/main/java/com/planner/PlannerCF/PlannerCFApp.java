package com.planner.PlannerCF;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Theme(value = "plannercfapp")
@PWA(
		name = "PlannerCFApp",
		shortName = "PCF")
public class PlannerCFApp implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(PlannerCFApp.class, args);
	}

}
