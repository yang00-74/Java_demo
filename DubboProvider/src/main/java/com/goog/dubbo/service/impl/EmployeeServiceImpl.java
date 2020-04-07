package com.goog.dubbo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.goog.dubbo.api.EmployeeRemoteService;
import com.goog.dubbo.entity.Employee;

public class EmployeeServiceImpl implements EmployeeRemoteService {

	public List<Employee> getEmployeeByConditionRemote(Employee e) {
		System.out.println(e);
		List<Employee> list = new ArrayList<Employee>();
		list.add(new Employee(111,"white",120.0));
		list.add(new Employee(112,"kite",120.01));
		return list;
	}

}
