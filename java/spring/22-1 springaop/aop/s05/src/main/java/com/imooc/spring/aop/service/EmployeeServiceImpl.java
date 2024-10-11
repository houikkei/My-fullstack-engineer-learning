package com.imooc.spring.aop.service;

public class EmployeeServiceImpl implements EmployeeService {
    public void createEmployee() {
        System.out.println("执行创建员工业务逻辑");
    }
}
