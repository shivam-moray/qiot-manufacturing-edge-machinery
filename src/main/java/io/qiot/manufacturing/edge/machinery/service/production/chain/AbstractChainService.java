package io.qiot.manufacturing.edge.machinery.service.production.chain;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import io.qiot.manufacturing.edge.machinery.domain.ItemDTO;
import io.qiot.manufacturing.edge.machinery.domain.ProductionChainStageEnum;
import io.qiot.manufacturing.edge.machinery.domain.event.chain.StageCompletedEvent;
import io.qiot.manufacturing.edge.machinery.service.production.ConveyorBeltService;

public abstract class AbstractChainService implements ChainService {

    @Inject
    protected ConveyorBeltService conveyorBeltService;

    @Override
    public void simulate() {
        ItemDTO item = poll(getStage());
        item.stage = getStage();
        doSimulate(item);
        StageCompletedEvent event = new StageCompletedEvent();
        event.item = item;
        getEvent().fireAsync(event);
    }

    protected abstract ProductionChainStageEnum getStage();

    protected abstract void doSimulate(ItemDTO product);

    protected abstract Event<StageCompletedEvent> getEvent();

    protected ItemDTO poll(ProductionChainStageEnum stage) {
        ItemDTO item = conveyorBeltService.nextItemInQueue(stage);
        return item;
    }

}
