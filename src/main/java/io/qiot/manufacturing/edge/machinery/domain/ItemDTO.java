/**
 * 
 */
package io.qiot.manufacturing.edge.machinery.domain;

import java.util.UUID;

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

}
