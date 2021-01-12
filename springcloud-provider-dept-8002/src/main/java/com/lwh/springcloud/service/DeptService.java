package com.lwh.springcloud.service;

import com.lwh.springcloud.pojo.Dept;

import java.util.List;

public interface DeptService {
    public boolean addDept(Dept dept);
    public Dept queryById(Long deptno);
    public List<Dept> queryAll();
}
