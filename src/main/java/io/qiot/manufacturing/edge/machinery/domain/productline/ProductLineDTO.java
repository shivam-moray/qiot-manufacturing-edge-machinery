package io.qiot.manufacturing.edge.machinery.domain.productline;

import java.util.Objects;
import java.util.UUID;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ProductLineDTO {
    public UUID productLineId;
    public SizeChartRangesDTO sizeChart;
    public ColorRangesDTO color;
    public PrintingRangesDTO print;
    public PackagingRangesDTO packaging;
    

    @Override
    public int hashCode() {
        return Objects.hash(productLineId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductLineDTO other = (ProductLineDTO) obj;
        return Objects.equals(productLineId, other.productLineId);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ProductLineDTO [productLineId=");
        builder.append(productLineId);
        builder.append(", sizeChart=");
        builder.append(sizeChart);
        builder.append(", color=");
        builder.append(color);
        builder.append(", print=");
        builder.append(print);
        builder.append(", packaging=");
        builder.append(packaging);
        builder.append("]");
        return builder.toString();
    }

}