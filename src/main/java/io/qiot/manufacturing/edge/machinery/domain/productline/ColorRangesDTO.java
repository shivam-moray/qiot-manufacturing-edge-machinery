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
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ColorRangesDTO [redMin=");
        builder.append(redMin);
        builder.append(", redMax=");
        builder.append(redMax);
        builder.append(", greenMin=");
        builder.append(greenMin);
        builder.append(", greenMax=");
        builder.append(greenMax);
        builder.append(", blueMin=");
        builder.append(blueMin);
        builder.append(", blueMax=");
        builder.append(blueMax);
        builder.append("]");
        return builder.toString();
    }
}
