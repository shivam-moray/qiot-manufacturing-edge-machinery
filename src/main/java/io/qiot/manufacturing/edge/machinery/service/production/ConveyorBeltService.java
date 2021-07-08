package io.qiot.manufacturing.edge.machinery.service.production;

import java.util.UUID;

import io.qiot.manufacturing.edge.machinery.domain.ItemDTO;
import io.qiot.manufacturing.edge.machinery.domain.ProductionChainStageEnum;

public interface ConveyorBeltService {
    void createNewItem(UUID productLineId, int itemId);

    ItemDTO nextItemInQueue(ProductionChainStageEnum stage);

    void moveToWaitingQueue(ItemDTO item);

    ItemDTO dropItem(int itemId, ProductionChainStageEnum stage);

    ItemDTO moveToNextStage(int itemId, ProductionChainStageEnum stage);
}
