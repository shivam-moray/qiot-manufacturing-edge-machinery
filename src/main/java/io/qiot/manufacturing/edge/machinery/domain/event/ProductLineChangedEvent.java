package io.qiot.manufacturing.edge.machinery.domain.event;

import io.qiot.manufacturing.commons.domain.productline.ProductLineDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ProductLineChangedEvent {
    public ProductLineDTO productLine;
}
