/**
 * 
 */
package io.qiot.manufacturing.edge.machinery.domain.production;

import java.util.UUID;

import io.qiot.manufacturing.edge.machinery.domain.ProductionChainStageEnum;
import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * @author abattagl
 *
 */
@RegisterForReflection
public class ItemDTO {
    public int id;
    public ProductionChainStageEnum stage;
    public UUID productLineId;
    public SizeMetricsDTO sizeMetrics;
    public ColorMetricsDTO colorMetrics;
    public double printing;
    public double packaging;
}
