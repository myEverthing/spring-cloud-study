package com.lwh.springcloud.controller;


import com.lwh.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("lwh/springcloud")
public class DeptConsumerController {
    private static String REST_FUL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";

    //private static String REST_FUL_PREFIX = "http://localhost:8001";
    @Autowired
    private DiscoveryClient discoveryClient;


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id)
    {
        return restTemplate.getForObject(REST_FUL_PREFIX+"/lwh/springcloud/dept/get/" + id, Dept.class);
    }

    @PostMapping("/consumer/dept/add")
    public boolean add(@RequestBody Dept dept)
    {
        return restTemplate.postForObject(REST_FUL_PREFIX+"/lwh/springcloud/dept/add", dept, boolean.class);
    }

    @GetMapping("/consumer/dept/list")
    public List<Dept> queryAll()
    {
        return restTemplate.getForObject(REST_FUL_PREFIX+"/lwh/springcloud/dept/list",List.class);
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
