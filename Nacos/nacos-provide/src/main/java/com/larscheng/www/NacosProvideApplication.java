package com.larscheng.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient // 开启服务注册发现功能
@SpringBootApplication
public class NacosProvideApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosProvideApplication.class, args);
    }

    @GetMapping("/helloNacos")
    public String helloNacos(){
        System.out.println("zhy--master--test---");
        return "你好，nacos！";
    }
}







