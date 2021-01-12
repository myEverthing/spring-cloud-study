package com.lwh.springcloud.remote.service;


import com.lwh.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT", fallbackFactory = DpetRemoteServiceFallbackFactory.class)
@RequestMapping("/lwh/springcloud")
public interface DeptRemoteService {

    @GetMapping("/dept/get/{id}")
    public Dept queryById(@PathVariable("id") Long id);

    @GetMapping("/dept/list")
    public List<Dept> queryAll();

    @PostMapping("/dept/add")
    public Boolean addDept(@RequestBody Dept dept);
}
