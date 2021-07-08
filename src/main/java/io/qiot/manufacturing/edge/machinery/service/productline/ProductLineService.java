package io.qiot.manufacturing.edge.machinery.service.productline;

import java.util.UUID;

import io.qiot.manufacturing.edge.machinery.domain.productline.ProductLineDTO;

public interface ProductLineService {

    /**
     * This method returns the Dada object containing the values for the current
     * product lines
     * 
     * @return {@link ProductLineDTO}
     */
    ProductLineDTO getCurrentProductLine();
    ProductLineDTO getProductLine(UUID productLineId);
    boolean hasProductLine();

}