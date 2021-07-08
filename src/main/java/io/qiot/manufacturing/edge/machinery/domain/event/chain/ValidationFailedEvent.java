package io.qiot.manufacturing.edge.machinery.domain.event.chain;

import io.qiot.manufacturing.edge.machinery.domain.ProductionChainStageEnum;

public class ValidationFailedEvent extends AbstractProductionChainEvent {
    public int itemId;
    public ProductionChainStageEnum stage;
}
