package com.lwh.configlwh;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.cloud.client.ServiceInstance;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;



public class LwhRoundRule extends AbstractLoadBalancerRule {

    private int serverCallCount = 0;
    private int currentIndex = 0;
    private static int EVERY_TIME_CALL_COUNT = 5;
    public LwhRoundRule() {
    }

    @SuppressWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"})
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers();//获得存活的服务
                List<Server> allList = lb.getAllServers();    //获得全部的服务
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }

//                int index = this.chooseRandomInt(serverCount); ////生产区间随机数
//                server = (Server)upList.get(index); //获得服务
                if(this.serverCallCount < EVERY_TIME_CALL_COUNT)
                {
                    this.serverCallCount++;
                }else {
                    this.serverCallCount = 0;
                    this.currentIndex++;
                }
                if (this.currentIndex >= upList.size())
                {
                    this.currentIndex = 0;
                }
                server = (Server)upList.get(currentIndex);
                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }
    @Override
    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }
    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
