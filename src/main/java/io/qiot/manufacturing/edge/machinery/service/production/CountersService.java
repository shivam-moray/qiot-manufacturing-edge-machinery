package io.qiot.manufacturing.edge.machinery.service.production;

import java.util.UUID;

import io.qiot.manufacturing.edge.machinery.domain.ProductionChainStageEnum;

public interface CountersService {
    int recordNewItem(UUID productLineId);

    void recordStageEnd(int itemId, UUID productLineId, ProductionChainStageEnum stage);

    void recordStageSuccess(int itemId, UUID productLineId, ProductionChainStageEnum stage);

    void recordStageFailure(int itemId, UUID productLineId, ProductionChainStageEnum stage);
}
