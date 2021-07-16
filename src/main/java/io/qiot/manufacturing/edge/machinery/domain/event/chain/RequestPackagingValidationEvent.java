package io.qiot.manufacturing.edge.machinery.domain.event.chain;

import io.qiot.manufacturing.edge.machinery.domain.production.PackagingMetricsDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class RequestPackagingValidationEvent extends AbstractValidationRequestEvent {
    public PackagingMetricsDTO data;
}
