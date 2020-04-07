package com.goog.dubbo.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goog.dubbo.api.EmployeeRemoteService;
import com.goog.dubbo.entity.Employee;

@Controller
public class EmployeeHandler {

	@Autowired
	private EmployeeRemoteService service;

	@RequestMapping("/get/emp/list")
	public String getEmplist() {

		Employee e = new Employee(11, "hh", 120.08);
		List<Employee> list = service.getEmployeeByConditionRemote(e);
		for (Employee emp : list) {
			System.out.println(emp.toString());
		}
		return "target";
	}

}
