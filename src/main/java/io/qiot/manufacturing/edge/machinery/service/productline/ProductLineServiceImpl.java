package io.qiot.manufacturing.edge.machinery.service.productline;

import java.util.Map;
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

import io.qiot.manufacturing.edge.machinery.domain.event.ProductLineChangedEvent;
import io.qiot.manufacturing.edge.machinery.domain.productline.ProductLineDTO;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
class ProductLineServiceImpl implements ProductLineService {

    private final Lock readLock;
    private final Lock writeLock;

    private ProductLineDTO currentProductLine;
    private Map<UUID, ProductLineDTO> productLines;

    @Inject
    Event<Void> productLineEvent;

    @Inject
    Logger LOGGER;

    public ProductLineServiceImpl() {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        this.readLock = readWriteLock.readLock();
        this.writeLock = readWriteLock.writeLock();
    }

    @PostConstruct
    void init() {
    }

    @Override
    public boolean hasProductLine() {
        return currentProductLine != null;
    }

    void newProductLine(@Observes ProductLineChangedEvent event) {
        writeLock.lock();
        {
            currentProductLine = event.getProductLine();
            productLines.put(event.getProductLine().productLineId,
                    event.getProductLine());
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