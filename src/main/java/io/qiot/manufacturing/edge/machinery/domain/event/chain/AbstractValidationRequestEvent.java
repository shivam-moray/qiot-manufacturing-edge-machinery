package io.qiot.manufacturing.edge.machinery.domain.event.chain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.quarkus.runtime.annotations.RegisterForReflection;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RequestWeavingValidationEvent.class, name = "RequestWeavingValidationEvent"),
        @JsonSubTypes.Type(value = RequestColoringValidationEvent.class, name = "RequestColoringValidationEvent"),
        @JsonSubTypes.Type(value = RequestPrintingValidationEvent.class, name = "RequestPrintingValidationEvent"),
        @JsonSubTypes.Type(value = RequestPackagingValidationEvent.class, name = "RequestPackagingValidationEvent") })
@RegisterForReflection
public class AbstractValidationRequestEvent
        extends AbstractProductionChainEvent {
    public String machineryId;

}
