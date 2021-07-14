package io.qiot.manufacturing.edge.machinery.domain.event.chain;

import java.util.UUID;

public class AbstractValidationRequestEvent
        extends AbstractProductionChainEvent {
    public String machineryId;
    public UUID productLineId;

}
