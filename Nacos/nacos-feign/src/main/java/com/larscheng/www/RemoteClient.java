package com.larscheng.www;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @auther: lars
 * @date: 2019/7/10 16:01
 * @description:  通过@FeignClient注解指定被调用方的服务名，通过fallback属性指定RemoteHystrix类，来进行远程调用的熔断和降级处理
 */
@FeignClient(name = "nacos-provide", fallback = RemoteHystrix.class)
public interface RemoteClient {

    @GetMapping("/helloNacos")
    String helloNacos();
}
