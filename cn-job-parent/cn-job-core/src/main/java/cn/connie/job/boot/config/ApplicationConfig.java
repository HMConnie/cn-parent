package cn.connie.job.boot.config;

import com.sgcai.zookeeper.lib.DistributedLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Q on 2017/7/10.
 * <p>
 * ApplicationConfig
 */
@Configuration
@ImportResource(value = {"classpath:applicationContext.xml","classpath:dubbo-provider.xml"})
public class ApplicationConfig {

    @Value("${dubbo.registry.address}")
    private String zookeeperUrl;

    @Bean
    public DistributedLock DistributedLock() throws Exception {
        return new DistributedLock(zookeeperUrl, "cn-job-core");
    }
}
