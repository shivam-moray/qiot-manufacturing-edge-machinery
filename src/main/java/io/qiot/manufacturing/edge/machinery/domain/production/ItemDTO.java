/**
 * 
 */
package io.qiot.manufacturing.edge.machinery.domain.production;

import java.util.UUID;

import io.qiot.manufacturing.edge.machinery.domain.ProductionChainStageEnum;
import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * @author andreabattaglia
 *
 */
@RegisterForReflection
public class ItemDTO {
    public int id;
    public ProductionChainStageEnum stage;
    public UUID productLineId;
    public SizeMetricsDTO sizeMetrics;
    public ColorMetricsDTO colorMetrics;
    public PrintingMetricsDTO printingMetrics;
    public PackagingMetricsDTO packagingMetrics;
}
