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
import io.qiot.manufacturing.edge.machinery.util.qualifier.chain.PrintingChainQualifier;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
@Typed(PrintingChainServiceImpl.class)
@PrintingChainQualifier
public class PrintingChainServiceImpl extends AbstractChainService {

    @Inject
    Logger LOGGER;
    
    @Inject
    Event<StageCompletedEvent> event;
    
    private PrimitiveIterator.OfDouble printingRandomNumberGenerator;

    @Override
    protected ProductionChainStageEnum getStage() {
        return ProductionChainStageEnum.PRINTING;
    }

    @Override
    protected Event<StageCompletedEvent> getEvent() {
        return event;
    }

    @Override
    protected void initRandomNumberGenerators(ProductLineDTO productLine) {
        printingRandomNumberGenerator=randomGeneratorProducer.doubleRandomNumberGenerator(productLine.print.min, productLine.print.max); 
    }

    @Override
    protected void doSimulate(ItemDTO item) {
        item.printing=printingRandomNumberGenerator.nextDouble();
    }
}
