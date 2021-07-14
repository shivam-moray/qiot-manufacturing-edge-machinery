package io.qiot.manufacturing.edge.machinery.util.producer;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ReplyToQueueNameProducer {

    @ConfigProperty(name = "qiot.production.chain.validation.replyto-queue-prefix")
    String replyToQueuePrefix;

    public String getReplyToQueueName(String machineryId) {
        return replyToQueuePrefix + "." + machineryId;
    }
}
