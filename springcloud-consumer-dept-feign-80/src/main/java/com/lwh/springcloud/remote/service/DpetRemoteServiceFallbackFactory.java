package com.lwh.springcloud.remote.service;

import com.lwh.springcloud.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DpetRemoteServiceFallbackFactory implements FallbackFactory {
    @Override
    public DeptRemoteService create(Throwable throwable) {

        return new DeptRemoteService() {
            @Override
            public Dept queryById(Long id) {
                return new Dept().setDeptno(id)
                        .setDname("id=>" + id + " 没有对应的信息，null--@Hystrix")
                        .setDe_source("no this database in MySQL");
            }

            @Override
            public List<Dept> queryAll() {
                return null;
            }

            @Override
            public Boolean addDept(Dept dept) {
                return null;
            }
        };
    }
}
