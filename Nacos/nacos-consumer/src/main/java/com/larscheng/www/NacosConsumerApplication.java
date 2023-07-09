package com.larscheng.www;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class NacosConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApplication.class, args);
    }

    @Autowired
    private RestTemplate restTemplate; 

    @Bean
    @LoadBalanced // 服务消费者是通过RestTemplate+Ribbon的方式来进行服务调用
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @GetMapping("/consumer") //http://127.0.0.1:9528/consumer
    public String test() {
        return restTemplate.getForObject("http://nacos-provide/helloNacos",String.class);
        //return restTemplate.getForObject("http://nacos-provide1/helloNacos",String.class); // ps add test
    }
}
