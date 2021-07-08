package io.qiot.manufacturing.edge.machinery.domain.production;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ColorMetricsDTO {
    public float r;
    public float g;
    public float b;
}
