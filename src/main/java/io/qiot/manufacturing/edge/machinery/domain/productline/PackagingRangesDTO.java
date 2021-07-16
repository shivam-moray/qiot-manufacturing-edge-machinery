package io.qiot.manufacturing.edge.machinery.domain.productline;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class PackagingRangesDTO {
    public double min=0;
    public double max=1;
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PackagingRangesDTO [min=");
        builder.append(min);
        builder.append(", max=");
        builder.append(max);
        builder.append("]");
        return builder.toString();
    }
}
