package io.qiot.manufacturing.edge.machinery.domain.event.chain;

import java.util.UUID;

import io.qiot.manufacturing.edge.machinery.domain.ProductionChainStageEnum;

/**
 * @author andreabattaglia
 *
 */
public abstract class AbstractProductionChainEvent {
    public UUID productLineId;
    public int itemId;
    public ProductionChainStageEnum stage;
}
