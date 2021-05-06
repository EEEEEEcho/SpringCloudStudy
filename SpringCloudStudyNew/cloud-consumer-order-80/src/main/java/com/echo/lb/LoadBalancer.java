package com.echo.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {
    //将所有的服务实例，装到List中
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
