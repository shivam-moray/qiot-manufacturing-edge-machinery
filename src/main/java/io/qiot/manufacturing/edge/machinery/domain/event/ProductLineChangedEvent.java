package io.qiot.manufacturing.edge.machinery.domain.event;

import io.qiot.manufacturing.edge.machinery.domain.productline.ProductLineDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ProductLineChangedEvent {
    private final ProductLineDTO productLine;

    public ProductLineChangedEvent(ProductLineDTO productLine) {
        this.productLine = productLine;
    }

    public ProductLineDTO getProductLine() {
        return productLine;
    }

}
