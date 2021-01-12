package com.lwh.springcloud.controller;


import com.lwh.springcloud.pojo.Dept;
import com.lwh.springcloud.remote.service.DeptRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("lwh/springcloud")
public class DeptConsumerController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private DeptRemoteService deptRemoteService = null;



    @GetMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id)
    {
        Dept dept = deptRemoteService.queryById(id);
        if (dept == null)
        {
            throw new RuntimeException("id=>" + id + ",不存在该用户");
        }
        return dept;
    }

    @PostMapping("/consumer/dept/add")
    public boolean add(@RequestBody Dept dept)
    {
        return deptRemoteService.addDept(dept);
    }

    @GetMapping("/consumer/dept/list")
    public List<Dept> queryAll()
    {
        return deptRemoteService.queryAll();
    }

    @GetMapping("/dept/discovery")
    public Object discovery()
    {
        List<String> services = discoveryClient.getServices();
        System.out.println("duscovery=>servers:" + services);

        List<ServiceInstance> instances = discoveryClient.getInstances("SPRINGCLOUD-PROVIDER-DEPT");

        for (ServiceInstance instance: instances) {
            System.out.println(instance.getHost());
            System.out.println(instance.getUri());
            System.out.println(instance.getServiceId());
            System.out.println(instance.getPort());
            System.out.println(instance.getMetadata());
            System.out.println(instance.getInstanceId());
        }
        return this.discoveryClient;
    }
}
