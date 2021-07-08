/**
 * 
 */
package io.qiot.manufacturing.edge.machinery.service.production.chain;

import java.util.PrimitiveIterator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.edge.machinery.domain.ProductionChainStageEnum;
import io.qiot.manufacturing.edge.machinery.domain.event.chain.StageCompletedEvent;
import io.qiot.manufacturing.edge.machinery.domain.production.ItemDTO;
import io.qiot.manufacturing.edge.machinery.domain.productline.ProductLineDTO;
import io.qiot.manufacturing.edge.machinery.util.qualifier.chain.PackagingChainQualifier;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
@Typed(PackagingChainServiceImpl.class)
@PackagingChainQualifier
public class PackagingChainServiceImpl extends AbstractChainService {

    @Inject
    Logger LOGGER;
    
    @Inject
    Event<StageCompletedEvent> event;
    
    private PrimitiveIterator.OfDouble packagingRandomNumberGenerator;

    @Override
    protected ProductionChainStageEnum getStage() {
        return ProductionChainStageEnum.PACKAGING;
    }

    @Override
    protected Event<StageCompletedEvent> getEvent() {
        return event;
    }

    @Override
    protected void initRandomNumberGenerators(ProductLineDTO productLine) {
        packagingRandomNumberGenerator=randomGeneratorProducer.doubleRandomNumberGenerator(productLine.print.min, productLine.print.max); 
    }

    @Override
    protected void doSimulate(ItemDTO item) {
        item.packaging=packagingRandomNumberGenerator.nextDouble();
    }
}
