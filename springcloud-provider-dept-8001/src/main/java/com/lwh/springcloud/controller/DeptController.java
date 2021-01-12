package com.lwh.springcloud.controller;

import com.lwh.springcloud.pojo.Dept;
import com.lwh.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lwh/springcloud")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient discoveryClient;
    @PostMapping("/dept/add")
    public boolean addDept(@RequestBody  Dept dept)
    {
        System.out.println(dept.getDname());
        return deptService.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "hystrixGet")
    public Dept getById(@PathVariable("id") Long id)
    {
        Dept dept = deptService.queryById(id);
        if (dept == null)
        {
            throw new RuntimeException("id=>" + id + ", 不存在该异常");
        }
        return dept;
    }

    public Dept hystrixGet(@PathVariable("id") Long id)
    {
        return new Dept().setDeptno(id)
                .setDname("id=>" + id + " 没有对应的信息，null--@Hystrix")
                .setDe_source("no this database in MySQL");
    }


    @GetMapping("/dept/list")
    public List<Dept> queryAll(){
        List<Dept> depts = deptService.queryAll();
        return deptService.queryAll();
    }

    @GetMapping("/dept/discovery")
    public Object discovery()
    {
        List<String> services = discoveryClient.getServices();
        System.out.println("duscovery=>servers:" + services);

        List<ServiceInstance> instances = discoveryClient.getInstances("SPRINGCLOUD-PROVIDER-DEPT");

        for (ServiceInstance instance: instances) {
            System.out.println(instance.getHost() + "\t"
                               + instance.getPort() + "\t" + instance.getServiceId());
        }
        return this.discoveryClient;
    }
}
