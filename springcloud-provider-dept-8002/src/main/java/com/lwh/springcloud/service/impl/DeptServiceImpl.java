package com.lwh.springcloud.service.impl;

import com.lwh.springcloud.mapper.DeptMapper;
import com.lwh.springcloud.pojo.Dept;
import com.lwh.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public boolean addDept(Dept dept) {
        return deptMapper.addDept(dept);
    }

    @Override
    public Dept queryById(Long deptno) {
        return deptMapper.queryById(deptno);
    }

    @Override
    public List<Dept> queryAll() {
        return deptMapper.queryAll();
    }
}
