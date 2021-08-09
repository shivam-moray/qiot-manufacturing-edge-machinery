package io.qiot.manufacturing.edge.machinery.domain.event.productline;

import io.qiot.manufacturing.commons.domain.productline.ProductLineDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ProductLineChangedEventDTO {
    public ProductLineDTO productLine;
}
