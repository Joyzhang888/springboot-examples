package com.hong.elasticsearch.config;


import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.TransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

/**
 * @author tianhong
 * @Description Elasticsearch 连接配置，进行初始化操作
 * @date 2018/5/31 10:37
 */
@Configuration
public class ESConfiguration implements FactoryBean<TransportClient>, InitializingBean, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(ESConfiguration.class);

    /**
     * ES集群地址
     */
    @Value("${elasticsearch.ip}")
    private String ips;
    /**
     * 端口
     */
    @Value("${elasticsearch.port}")
    private String port;
    /**
     * 集群名称
     */
    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    /**
     * 连接池
     */
    @Value("${elasticsearch.pool}")
    private String poolSize;

    private TransportClient client;

    @Override
    public void destroy() throws Exception {
        try {
            logger.info("Closing elasticSearch client");
            if (client != null) {
                client.close();
            }
        } catch (final Exception e) {
            logger.error("Error closing ElasticSearch client: ", e);
        }
    }

    @Override
    public TransportClient getObject() throws Exception {
        return client;
    }

    @Override
    public Class<TransportClient> getObjectType() {
        return TransportClient.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            Settings setting = Settings.builder()
                    // 集群名称
                    .put("cluster.name", clusterName)
                    // 自动发现新加入集群的机器
                    .put("client.transport.sniff", false)
                    // 搜索线程池大小
                    .put("thread_pool.search.size", Integer.parseInt(poolSize))
                    .build();
            client = new PreBuiltTransportClient(setting);
            String[] esIps = ips.split(",");
            for (String esIp : esIps) {
                InetSocketTransportAddress inetSocketTransportAddress = new InetSocketTransportAddress(InetAddress.getByName(esIp), Integer.valueOf(port));
                client.addTransportAddresses(inetSocketTransportAddress);
            }
        } catch (Exception e) {
            logger.error("elasticsearch TransportClient create error!!!", e);
        }
    }

}
