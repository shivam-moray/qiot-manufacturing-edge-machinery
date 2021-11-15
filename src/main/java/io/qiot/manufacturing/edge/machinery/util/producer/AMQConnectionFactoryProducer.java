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

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class AMQConnectionFactoryProducer {

    @ConfigProperty(name = "qiot.artemis.sslEnabled", defaultValue = "false")
    boolean sslEnabled;

    @ConfigProperty(name = "qiot.artemis.url")
    String url;

    @ConfigProperty(name = "qiot.artemis.username")
    String username;

    @ConfigProperty(name = "qiot.artemis.password")
    String password;

    @ConfigProperty(name = "qiot.artemis.host")
    String host;

    @ConfigProperty(name = "qiot.artemis.port")
    int port;

    @ConfigProperty(name = "qiot.artemis.verifyHost")
    boolean verifyHost;

    @ConfigProperty(name = "qiot.artemis.trustAll")
    boolean trustAll;

    @ConfigProperty(name = "qiot.artemis.keyStoreProvider")
    String keyStoreProvider;

    @ConfigProperty(name = "qiot.artemis.keyStorePath")
    String keyStorePath;

    @ConfigProperty(name = "qiot.artemis.keyStorePassword")
    String keyStorePassword;

    @ConfigProperty(name = "qiot.artemis.trustStoreProvider")
    String trustStoreProvider;

    @ConfigProperty(name = "qiot.artemis.trustStorePath")
    String trustStorePath;

    @ConfigProperty(name = "qiot.artemis.trustStorePassword")
    String trustStorePassword;

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

    private ActiveMQJMSConnectionFactory initSSLConnectionFactory() {

        HashMap<String, Object> map = new HashMap<>();

        // ./artemis producer --destination foo --user amq --password amq
        // --message-count 10
        // --url="tcp://broker-service-edge-0-svc-rte-factory.apps.manufacturingfacility.qiot.io:443?sslEnabled=true;keyStorePath=./client.ks;keyStorePassword=password;trustStorePath=./client.ts;trustStorePassword=password"

        // ./artemis consumer --destination foo --user amq --password amq
        // --url="tcp://broker-service-edge-0-svc-rte-factory.apps.manufacturingfacility.qiot.io:443?sslEnabled=true;keyStorePath=./client.ks;keyStorePassword=password;trustStorePath=./client.ts;trustStorePassword=password"

        // map.put(TransportConstants.PROTOCOLS_PROP_NAME, "CORE");
        // host
        map.put(TransportConstants.HOST_PROP_NAME, host);
        // port
        map.put(TransportConstants.PORT_PROP_NAME, port);
        // sslEnabled
        map.put(TransportConstants.SSL_ENABLED_PROP_NAME, true);
        // verifyHost
        map.put(TransportConstants.VERIFY_HOST_PROP_NAME, verifyHost);
        // trustAll
        map.put(TransportConstants.TRUST_ALL_PROP_NAME, trustAll);
        // keyStoreProvider
        map.put(TransportConstants.KEYSTORE_PROVIDER_PROP_NAME,
                keyStoreProvider);
        // keyStorePath
        map.put(TransportConstants.KEYSTORE_PATH_PROP_NAME, keyStorePath);
        // keyStorePassword
        map.put(TransportConstants.KEYSTORE_PASSWORD_PROP_NAME,
                keyStorePassword);
        // trustStoreProvider
        map.put(TransportConstants.TRUSTSTORE_PROVIDER_PROP_NAME,
                trustStoreProvider);
        // trustStorePath
        map.put(TransportConstants.TRUSTSTORE_PATH_PROP_NAME, trustStorePath);
        // trustStorePassword
        map.put(TransportConstants.TRUSTSTORE_PASSWORD_PROP_NAME,
                trustStorePassword);

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
