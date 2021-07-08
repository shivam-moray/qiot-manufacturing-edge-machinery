package io.qiot.manufacturing.edge.machinery.service.production.chain;

import java.util.Objects;
import java.util.UUID;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import io.qiot.manufacturing.edge.machinery.domain.ProductionChainStageEnum;
import io.qiot.manufacturing.edge.machinery.domain.event.chain.StageCompletedEvent;
import io.qiot.manufacturing.edge.machinery.domain.production.ItemDTO;
import io.qiot.manufacturing.edge.machinery.domain.productline.ProductLineDTO;
import io.qiot.manufacturing.edge.machinery.service.production.ConveyorBeltService;
import io.qiot.manufacturing.edge.machinery.service.productline.ProductLineService;
import io.qiot.manufacturing.edge.machinery.util.producer.RandomGeneratorProducer;
import io.quarkus.scheduler.Scheduled;

public abstract class AbstractChainService implements ChainService {

    @Inject
    protected ConveyorBeltService conveyorBeltService;

    @Inject
    protected ProductLineService productLineService;

    @Inject
    protected RandomGeneratorProducer randomGeneratorProducer;

    protected UUID productLineId;

    @Scheduled(every = "5s")
    @Override
    public void simulate() {
        ItemDTO item = poll(getStage());
        checkProductLineId(item.productLineId);
        item.stage = getStage();
        doSimulate(item);
        notify(item);
    }

    private void notify(ItemDTO item) {
        StageCompletedEvent event = new StageCompletedEvent();
        event.item = item;
        getEvent().fireAsync(event);
    }

    private void checkProductLineId(UUID productLineId) {
        if (Objects.isNull(this.productLineId)
                || (!productLineId.equals(this.productLineId))) {
            ProductLineDTO productLine = productLineService
                    .getProductLine(productLineId);
            initRandomNumberGenerators(productLine);
        }
    }

    protected abstract void initRandomNumberGenerators(
            ProductLineDTO productLine);

    protected abstract ProductionChainStageEnum getStage();

    protected abstract void doSimulate(ItemDTO item);

    protected abstract Event<StageCompletedEvent> getEvent();

    private ItemDTO poll(ProductionChainStageEnum stage) {
        ItemDTO item = conveyorBeltService.nextItemInQueue(stage);
        return item;
    }

}
