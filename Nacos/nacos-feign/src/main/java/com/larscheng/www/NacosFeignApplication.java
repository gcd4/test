package com.larscheng.www;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//https://www.larscheng.com/nacos-openfeign/
//https://blog.csdn.net/qq_33619378/article/details/95353326

@SpringBootApplication
@RestController
@EnableFeignClients
@EnableDiscoveryClient
public class NacosFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosFeignApplication.class, args);
    }

    @Autowired
    private RemoteClient remoteClient;

    @GetMapping("/feign")//  http://127.0.0.1:9529/feign
    public String test() {
        return remoteClient.helloNacos();
    }
}
