package com.lwh.springcloud.mapper;

import com.lwh.springcloud.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface DeptMapper {
    public boolean addDept(Dept dept);
    public Dept queryById(Long deptno);
    public List<Dept> queryAll();
}
