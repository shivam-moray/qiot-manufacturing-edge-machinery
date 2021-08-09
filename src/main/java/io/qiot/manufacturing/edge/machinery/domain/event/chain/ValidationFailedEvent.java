package io.qiot.manufacturing.edge.machinery.domain.event.chain;

import io.qiot.manufacturing.commons.domain.productionvalidation.AbstractProductionChainEventDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ValidationFailedEvent extends AbstractProductionChainEventDTO {
}
