package cn.connie.search.core;

import com.sgcai.zookeeper.lib.DistributedLock;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

/**
 * Created by Q on 2017/7/10.
 * <p>
 * ApplicationConfig
 */
@Configuration
public class ApplicationConfig {

    @Value("${dubbo.registry.address}")
    private String zookeeperUrl;

    @Value("${elasticsearch.host}")
    private String ELASTICSEARCH_HOST;
    @Value("${elasticsearch.port}")
    private String ELASTICSEARCH_PORT;
    @Value("${elasticsearch.cluster.name}")
    private String ELASTICSEARCH_CLUSTER_NAME;
    @Value("${elasticsearch.node.name}")
    private String ELASTICSEARCH_NODE_NAME;

    @Bean
    public DistributedLock DistributedLock() throws Exception {
        return new DistributedLock(zookeeperUrl, "cn-search-core");
    }

    /**
     * 初始化 elasticsearch的客户端TransportClient
     * @return
     */
    @Bean
    public TransportClient transportClient() {
        Settings settings = Settings.builder()
                .put("cluster.name", ELASTICSEARCH_CLUSTER_NAME)
                .put("node.name", ELASTICSEARCH_NODE_NAME)
                .build();
        PreBuiltTransportClient preBuiltTransportClient = new PreBuiltTransportClient(settings);
        InetSocketAddress inetSocketAddress = new InetSocketAddress(ELASTICSEARCH_HOST, Integer.parseInt(ELASTICSEARCH_PORT));
        TransportAddress transportAddress = new TransportAddress(inetSocketAddress);
        TransportClient transportClient = preBuiltTransportClient.addTransportAddress(transportAddress);
        return transportClient;
    }
}
