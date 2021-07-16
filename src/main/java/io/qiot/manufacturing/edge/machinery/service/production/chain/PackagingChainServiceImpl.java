/**
 * 
 */
package io.qiot.manufacturing.edge.machinery.service.production.chain;

import java.util.PrimitiveIterator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.edge.machinery.domain.ProductionChainStageEnum;
import io.qiot.manufacturing.edge.machinery.domain.event.chain.StageCompletedEvent;
import io.qiot.manufacturing.edge.machinery.domain.production.ItemDTO;
import io.qiot.manufacturing.edge.machinery.domain.production.PackagingMetricsDTO;
import io.qiot.manufacturing.edge.machinery.domain.productline.ProductLineDTO;
import io.quarkus.scheduler.Scheduled;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
//@Typed(PackagingChainServiceImpl.class)
//@PackagingChainQualifier
public class PackagingChainServiceImpl extends AbstractChainService {

    @Inject
    Logger LOGGER;
    
    @Inject
    Event<StageCompletedEvent> event;
    
    private PrimitiveIterator.OfDouble packagingRandomNumberGenerator;

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @Override
    protected ProductionChainStageEnum getStage() {
        return ProductionChainStageEnum.PACKAGING;
    }

    @Override
    protected Event<StageCompletedEvent> getEvent() {
        return event;
    }

    @Scheduled(every = "2s")
    @Override
    public void simulate() {
        super.doSimulate();
    }

    @Override
    protected void initRandomNumberGenerators(ProductLineDTO productLine) {
        packagingRandomNumberGenerator=randomGeneratorProducer.doubleRandomNumberGenerator(productLine.print.min, productLine.print.max); 
    }

    @Override
    protected void generate(ItemDTO item) {
        PackagingMetricsDTO metrics=new PackagingMetricsDTO();
        metrics.packaging=packagingRandomNumberGenerator.nextDouble();
        item.packagingMetrics=metrics;
        try {
            Thread.sleep(2000L); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
