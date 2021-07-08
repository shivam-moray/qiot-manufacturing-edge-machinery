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
}
