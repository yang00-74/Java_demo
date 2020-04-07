package com.goog.dubbo.api;

import java.util.List;

import com.goog.dubbo.entity.Employee;

public interface EmployeeRemoteService {
      List<Employee> getEmployeeByConditionRemote(Employee e);
 }
