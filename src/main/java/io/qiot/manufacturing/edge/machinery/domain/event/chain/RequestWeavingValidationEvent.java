package io.qiot.manufacturing.edge.machinery.domain.event.chain;

import io.qiot.manufacturing.edge.machinery.domain.production.SizeMetricsDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class RequestWeavingValidationEvent
        extends AbstractValidationRequestEvent {
    public SizeMetricsDTO data;
}
