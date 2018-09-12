package cn.connie.crawler.boot;

import cn.connie.crawler.core.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;

@ComponentScan(basePackages = {"cn.connie.user.core"})
@ImportResource({"classpath:applicationContext.xml", "classpath:database-config.xml", "classpath:dubbo-provider.xml"})
@ContextConfiguration(classes = ApplicationConfig.class)
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
