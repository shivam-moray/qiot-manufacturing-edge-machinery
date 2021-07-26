package io.qiot.manufacturing.edge.machinery.service.productline;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.qiot.manufacturing.commons.domain.productline.ProductLineDTO;
import io.qiot.manufacturing.commons.util.producer.SampleProductLineProducer;
import io.qiot.manufacturing.edge.machinery.domain.event.ProductLineChangedEvent;
import io.quarkus.runtime.LaunchMode;
import io.quarkus.runtime.configuration.ProfileManager;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
class ProductLineServiceImpl implements ProductLineService {

    private ReadWriteLock readWriteLock;
    private final Lock readLock;
    private final Lock writeLock;

    private ProductLineDTO currentProductLine;
    private Map<UUID, ProductLineDTO> productLines;

    @Inject
    Event<Void> productLineEvent;

    @Inject
    Logger LOGGER;

    @Inject
    ObjectMapper MAPPER;

    @Inject
    SampleProductLineProducer productLineProducer;

    public ProductLineServiceImpl() {
        productLines = new TreeMap<UUID, ProductLineDTO>();
        this.readWriteLock = new ReentrantReadWriteLock();
        this.readLock = readWriteLock.readLock();
        this.writeLock = readWriteLock.writeLock();
    }

    @PostConstruct
    void init() {
        if (ProfileManager.getActiveProfile()
                .equals(LaunchMode.DEVELOPMENT.getDefaultProfile())) {
            ProductLineDTO pl = productLineProducer.generate();
            ProductLineChangedEvent event = new ProductLineChangedEvent();
            event.productLine = pl;
            try {
                newProductLine(event);
            } catch (JsonProcessingException e) {
            }
        }
    }

    @Override
    public boolean hasProductLine() {
        return currentProductLine != null;
    }

    void newProductLine(@Observes ProductLineChangedEvent event)
            throws JsonProcessingException {
        LOGGER.info("Received new Product Line:\n\n{}",
                MAPPER.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(event.productLine));
        writeLock.lock();
        try {
            currentProductLine = event.productLine;
            productLines.put(event.productLine.id,
                    event.productLine);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public ProductLineDTO getCurrentProductLine() {
        readLock.lock();
        try {
            return currentProductLine;
        } finally {
            readLock.unlock();
        }

    }

    @Override
    public ProductLineDTO getProductLine(UUID productLineId) {
        readLock.lock();
        try {
            return productLines.get(productLineId);
        } finally {
            readLock.unlock();
        }
    }
}