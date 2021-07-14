package io.qiot.manufacturing.edge.machinery.domain;

import java.util.UUID;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * @author andreabattaglia
 *
 */
@RegisterForReflection
public class ValidationResponseDTO {
    public UUID productLineId;
    public int itemId;
    public ProductionChainStageEnum stage;
    public boolean validated;
}
