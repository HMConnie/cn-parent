package cn.connie.search.boot;

import cn.connie.search.core.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;

@ComponentScan(basePackages = {"cn.connie.search.core"})
@ImportResource({"classpath:applicationContext.xml", "classpath:dubbo-provider.xml"})
@ContextConfiguration(classes = ApplicationConfig.class)
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
