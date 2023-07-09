package com.larscheng.www;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RefreshScope
public class NacosNamespaceOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosNamespaceOneApplication.class, args);
    }

    @Value("${nacos.config}")
    private String config;

    @RequestMapping("/getValue")
    public String getValue() {
        return config;
    }
}

/**
 * https://blog.csdn.net/qq_33619378/article/details/98634900
 * 
 * 方案1  使用命名空间隔离环境dev/test/prof
 * 方案2  使用命名空间隔离租户张三/李四/王五
 * 
 * */
