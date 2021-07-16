package io.qiot.manufacturing.edge.machinery.domain.event.chain;

import io.qiot.manufacturing.edge.machinery.domain.production.ColorMetricsDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class RequestColoringValidationEvent
        extends AbstractValidationRequestEvent {
    public ColorMetricsDTO data;
}
