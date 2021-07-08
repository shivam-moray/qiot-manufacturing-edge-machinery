package io.qiot.manufacturing.edge.machinery.domain.productline;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ColorRangesDTO {
    public int redMin;
    public int redMax;
    public int greenMin;
    public int greenMax;
    public int blueMin;
    public int blueMax;
}
