package io.qiot.manufacturing.edge.machinery.util.producer;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.apache.activemq.artemis.api.core.TransportConfiguration;
import org.apache.activemq.artemis.core.remoting.impl.netty.NettyConnectorFactory;
import org.apache.activemq.artemis.core.remoting.impl.netty.TransportConstants;
import org.apache.activemq.artemis.jms.client.ActiveMQJMSConnectionFactory;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class AMQConnectionFactoryProducer {

    @ConfigProperty(name = "qiot.artemis.ssl", defaultValue = "false")
    boolean sslEnabled;

    @ConfigProperty(name = "quarkus.artemis.url")
    String url;

    @ConfigProperty(name = "quarkus.artemis.username")
    String username;

    @ConfigProperty(name = "quarkus.artemis.password")
    String password;

    private ActiveMQJMSConnectionFactory connectionFactory;

    @PostConstruct
    void init() {
        if (sslEnabled)
            connectionFactory = initSSLConnectionFactory();
        else
            connectionFactory = new ActiveMQJMSConnectionFactory(url, username,
                    password);
    }

    @PreDestroy
    void destroy() {
        connectionFactory.close();
    }

    private void initPlainConnectionFactory() {

    }

    private ActiveMQJMSConnectionFactory initSSLConnectionFactory() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(TransportConstants.PROTOCOLS_PROP_NAME, "ssl");
        map.put(TransportConstants.HOST_PROP_NAME,
                "broker-service-edge-0-svc-rte-factory.apps.manufacturingfacility.qiot.io");
        map.put(TransportConstants.PORT_PROP_NAME, 443);
        map.put(TransportConstants.SSL_ENABLED_PROP_NAME, true);
        map.put(TransportConstants.TRUSTSTORE_PATH_PROP_NAME,
                "/var/data/qiot/machinery/data/runtime/client.ks");
        map.put(TransportConstants.KEYSTORE_PROVIDER_PROP_NAME, "PKCS12");
        map.put(TransportConstants.KEYSTORE_PASSWORD_PROP_NAME, "password");
        map.put(TransportConstants.KEYSTORE_PATH_PROP_NAME,
                "/var/data/qiot/machinery/data/runtime/client.ts");
        map.put(TransportConstants.TRUSTSTORE_PROVIDER_PROP_NAME, "PKCS12");
        map.put(TransportConstants.TRUSTSTORE_PASSWORD_PROP_NAME, "password");

        TransportConfiguration tc = new TransportConfiguration(
                NettyConnectorFactory.class.getName(), map);

        ActiveMQJMSConnectionFactory cf = new ActiveMQJMSConnectionFactory(
                false, tc);
        return cf;
    }

    @Produces
    public ActiveMQJMSConnectionFactory produce() {
        return connectionFactory;
    }
}
