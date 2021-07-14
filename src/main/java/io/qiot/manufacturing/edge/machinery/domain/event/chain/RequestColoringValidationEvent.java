package io.qiot.manufacturing.edge.machinery.domain.event.chain;

import io.qiot.manufacturing.edge.machinery.domain.production.ColorMetricsDTO;

public class RequestColoringValidationEvent
        extends AbstractValidationRequestEvent {
    public ColorMetricsDTO colorMetrics;
}
