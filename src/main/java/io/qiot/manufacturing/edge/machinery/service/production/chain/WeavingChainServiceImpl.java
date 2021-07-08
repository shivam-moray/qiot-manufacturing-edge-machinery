/**
 * 
 */
package io.qiot.manufacturing.edge.machinery.service.production.chain;

import java.util.PrimitiveIterator.OfDouble;
import java.util.PrimitiveIterator;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.edge.machinery.domain.ProductionChainStageEnum;
import io.qiot.manufacturing.edge.machinery.domain.event.chain.StageCompletedEvent;
import io.qiot.manufacturing.edge.machinery.domain.production.ItemDTO;
import io.qiot.manufacturing.edge.machinery.domain.production.SizeMetricsDTO;
import io.qiot.manufacturing.edge.machinery.domain.productline.ProductLineDTO;
import io.qiot.manufacturing.edge.machinery.util.qualifier.chain.WeavingChainQualifier;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
@Typed(WeavingChainServiceImpl.class)
@WeavingChainQualifier
public class WeavingChainServiceImpl extends AbstractChainService {

    @Inject
    Logger LOGGER;
    
    @Inject
    Event<StageCompletedEvent> event;
    
    private PrimitiveIterator.OfDouble chestRandomNumberGenerator;
    private PrimitiveIterator.OfDouble shoulderRandomNumberGenerator;
    private PrimitiveIterator.OfDouble backRandomNumberGenerator;
    private PrimitiveIterator.OfDouble waistRandomNumberGenerator;
    private PrimitiveIterator.OfDouble hipRandomNumberGenerator;

    @Override
    protected ProductionChainStageEnum getStage() {
        return ProductionChainStageEnum.WEAVING;
    }

    @Override
    protected Event<StageCompletedEvent> getEvent() {
        return event;
    }

    @Override
    protected void initRandomNumberGenerators(ProductLineDTO productLine) {
        chestRandomNumberGenerator=randomGeneratorProducer.doubleRandomNumberGenerator(productLine.sizeChart.chestMin, productLine.sizeChart.chestMax); 
        shoulderRandomNumberGenerator= randomGeneratorProducer.doubleRandomNumberGenerator(productLine.sizeChart.shoulderMin, productLine.sizeChart.shoulderMax); 
        backRandomNumberGenerator= randomGeneratorProducer.doubleRandomNumberGenerator(productLine.sizeChart.backMin, productLine.sizeChart.backMax); 
        waistRandomNumberGenerator= randomGeneratorProducer.doubleRandomNumberGenerator(productLine.sizeChart.waistMin, productLine.sizeChart.waistMax); 
        hipRandomNumberGenerator= randomGeneratorProducer.doubleRandomNumberGenerator(productLine.sizeChart.hipMin, productLine.sizeChart.hipMax);        
    }

    @Override
    protected void doSimulate(ItemDTO item) {
        SizeMetricsDTO metrics=new SizeMetricsDTO();
        metrics.chest = chestRandomNumberGenerator.nextDouble();
        metrics.shoulder = shoulderRandomNumberGenerator.nextDouble();
        metrics.back = backRandomNumberGenerator.nextDouble();
        metrics.waist = waistRandomNumberGenerator.nextDouble();
        metrics.hip = hipRandomNumberGenerator.nextDouble();
        
    }

    

    
}