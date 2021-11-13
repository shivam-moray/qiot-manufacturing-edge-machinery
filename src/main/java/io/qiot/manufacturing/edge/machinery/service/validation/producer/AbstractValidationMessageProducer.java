/**
 * 
 */
package io.qiot.manufacturing.edge.machinery.service.validation.producer;

import java.util.HashMap;
import java.util.Objects;

import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.api.core.TransportConfiguration;
import org.apache.activemq.artemis.core.remoting.impl.netty.NettyConnectorFactory;
import org.apache.activemq.artemis.core.remoting.impl.netty.TransportConstants;
import org.apache.activemq.artemis.jms.client.ActiveMQJMSConnectionFactory;
import org.slf4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.qiot.manufacturing.all.commons.domain.production.ProductionChainStageEnum;
import io.qiot.manufacturing.edge.machinery.service.machinery.MachineryService;
import io.qiot.manufacturing.factory.commons.domain.productionvalidation.AbstractValidationRequestEventDTO;
import io.qiot.manufacturing.factory.commons.util.producer.ValidationReplyToQueueNameProducer;

/**
 * @author andreabattaglia
 *
 */
public abstract class AbstractValidationMessageProducer {

    // @Inject
//    ConnectionFactory connectionFactory;

     @Inject
     ActiveMQJMSConnectionFactory connectionFactory;

    @Inject
    MachineryService machineryService;

    @Inject
    ValidationReplyToQueueNameProducer replyToQueueNameProducer;

    @Inject
    ObjectMapper MAPPER;

    protected JMSContext context;

    protected JMSProducer producer;

    protected Queue queue;

    protected String replyToQueueName;

    protected Queue replyToQueue;

//    public AbstractValidationMessageProducer() {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put(TransportConstants.HOST_PROP_NAME,
//                "broker-service-edge-0-svc-rte-factory.apps.manufacturingfacility.qiot.io");
//        map.put(TransportConstants.PORT_PROP_NAME, 443);
//        map.put(TransportConstants.SSL_ENABLED_PROP_NAME, true);
//        map.put(TransportConstants.TRUSTSTORE_PATH_PROP_NAME,
//                "classpath:/certs/bootstrap/truststore.p12");
//        map.put(TransportConstants.TRUSTSTORE_PROVIDER_PROP_NAME, "PKCS12");
//        map.put(TransportConstants.TRUSTSTORE_PASSWORD_PROP_NAME, "password");
//
//        TransportConfiguration tc = new TransportConfiguration(
//                NettyConnectorFactory.class.getName(), map);
//
//        ActiveMQJMSConnectionFactory cf = new ActiveMQJMSConnectionFactory(
//                false, tc);
//        connectionFactory = cf;
//    }

    protected void doInit() {
        if (Objects.nonNull(context))
            context.close();
        context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE);

        producer = context.createProducer();

        queue = context.createQueue(getValidationQueueName());

        replyToQueueName = replyToQueueNameProducer
                .getReplyToQueueName(machineryService.getMachineryId());

        replyToQueue = context.createQueue(replyToQueueName);
        producer.setJMSReplyTo(replyToQueue);
    }

    protected void doRequestValidation(
            AbstractValidationRequestEventDTO event) {
        getLogger().debug("{} stage validation request received.", getStage());
        try {
            String payload = MAPPER.writeValueAsString(event);
            getLogger().debug("Message payload: {}", payload);
            TextMessage message = context.createTextMessage();
            message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
            message.setText(payload);
            message.setJMSReplyTo(replyToQueue);
            producer.send(queue, message);
        } catch (JMSException e) {
            getLogger().error(
                    "The messaging client returned an error: {} and will be restarted.",
                    e);
            doInit();
        } catch (JsonProcessingException e) {
            getLogger().error(
                    "The message payload is malformed and the validation request will not be sent: {}",
                    e);
        }
    }

    abstract protected String getValidationQueueName();

    abstract protected Logger getLogger();

    abstract protected ProductionChainStageEnum getStage();

}
