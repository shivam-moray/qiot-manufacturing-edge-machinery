package io.qiot.manufacturing.edge.machinery.domain.event.chain;

import io.qiot.manufacturing.edge.machinery.domain.production.PrintingMetricsDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class RequestPrintingValidationEvent extends AbstractValidationRequestEvent {
    public PrintingMetricsDTO data;
}
