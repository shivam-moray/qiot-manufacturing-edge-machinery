package io.qiot.manufacturing.edge.machinery.domain.productline;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class SizeChartRangesDTO {
    public double chestMin;
    public double chestMax;
    public double shoulderMin;
    public double shoulderMax;
    public double backMin;
    public double backMax;
    public double waistMin;
    public double waistMax;
    public double hipMin;
    public double hipMax;
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SizeChartRangesDTO [chestMin=");
        builder.append(chestMin);
        builder.append(", chestMax=");
        builder.append(chestMax);
        builder.append(", shoulderMin=");
        builder.append(shoulderMin);
        builder.append(", shoulderMax=");
        builder.append(shoulderMax);
        builder.append(", backMin=");
        builder.append(backMin);
        builder.append(", backMax=");
        builder.append(backMax);
        builder.append(", waistMin=");
        builder.append(waistMin);
        builder.append(", waistMax=");
        builder.append(waistMax);
        builder.append(", hipMin=");
        builder.append(hipMin);
        builder.append(", hipMax=");
        builder.append(hipMax);
        builder.append("]");
        return builder.toString();
    }
}
