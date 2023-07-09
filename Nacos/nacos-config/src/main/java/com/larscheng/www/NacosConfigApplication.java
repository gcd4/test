package com.larscheng.www;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;
import java.util.concurrent.Executor;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RefreshScope  //使当前类下的配置支持动态更新
public class NacosConfigApplication {

    public static void main(String[] args) throws NacosException, InterruptedException {
        SpringApplication.run(NacosConfigApplication.class, args);
        
        
        //ps add---------------------------------------------
        
        String serverAddr = "localhost";
    	String dataId = "nacos-config.yml"; //nacos-config.yaml
    	String group = "DEFAULT_GROUP";
    	Properties properties = new Properties();
    	properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
    	ConfigService configService = NacosFactory.createConfigService(properties);
    	String content = configService.getConfig(dataId, group, 5000);
    	System.out.println(content);
    	configService.addListener(dataId, group, new Listener() {
    		@Override
    		public void receiveConfigInfo(String configInfo) {
    			System.out.println("recieve:" + configInfo);
    		}

    		@Override
    		public Executor getExecutor() {
    			return null;
    		}
    	});

    	boolean isPublishOk = configService.publishConfig(dataId, group, "content");
    	System.out.println(isPublishOk);

    	Thread.sleep(3000);
    	content = configService.getConfig(dataId, group, 5000);
    	System.out.println(content);

    	boolean isRemoveOk = configService.removeConfig(dataId, group);
    	System.out.println(isRemoveOk);
    	Thread.sleep(3000);

    	content = configService.getConfig(dataId, group, 5000);
    	System.out.println(content);
    	Thread.sleep(300000);
    }

    /***/
    @Value("${nacos.config}") // Orig   打开 group: DEV_GROUP  或 打开 namespace: ee350a33-a77d-4f4c-bb31-4838546dbfe9
    private String config;

    @RequestMapping("/getValue") // http://127.0.0.1:9980/getValue   9980是在nacos-config-DEV_GROUP里配置的
    public String getValue() {
        return config;
    }
    
    /*
    //ps add
    @Value("${bbb.ccc}") // ps add   打开 group: aaa1 
    private String test1;

    @RequestMapping("/test1") // http://127.0.0.1:8080/test1    读取nacos-config-aaa1配置
    public String getTest1() {
        return test1;
    }
    
    //ps add
    @Value("${a1.b1}")
    private String test;
    @RequestMapping("/test") // http://127.0.0.1:8080/test
    public String getTest() {
        return test;
    }*/
    
    
/**
https://blog.csdn.net/qq_33619378/article/details/97017801
https://blog.csdn.net/qq_33619378/article/details/96991237

新建配置http://127.0.0.1:8848/nacos/index.html#/newconfig?serverId=center&namespace=&edasAppName=&edasAppId=&searchDataId=&searchGroup=
Data ID：nacos-config.yml
Group：aaa1
配置格式：yaml
配置内容：
nacos:
  config:zhy
bbb:
  ccc:
    1234
a1:
  b1:
    a1b1
*/
    
  
}
