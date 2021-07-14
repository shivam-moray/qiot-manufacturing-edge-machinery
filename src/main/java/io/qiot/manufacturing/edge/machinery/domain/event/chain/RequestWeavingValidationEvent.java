package io.qiot.manufacturing.edge.machinery.domain.event.chain;

import io.qiot.manufacturing.edge.machinery.domain.production.SizeMetricsDTO;

public class RequestWeavingValidationEvent
        extends AbstractValidationRequestEvent {
    public SizeMetricsDTO sizeMetrics;
}
