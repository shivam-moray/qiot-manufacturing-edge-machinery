package io.qiot.manufacturing.edge.machinery.domain.productline;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ColorDTO {
    public float rMin;
    public float rMax;
    public float gMin;
    public float gMax;
    public float bMin;
    public float bMax;
}
