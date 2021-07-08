package io.qiot.manufacturing.edge.machinery.domain.productline;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class SizeChartDTO {
    public float chestMin;
    public float chestMax;
    public float shoulderMin;
    public float shoulderMax;
    public float backLengthMin;
    public float backLengthMax;
    public float waistMin;
    public float waistMax;
    public float hipMin;
    public float hipMax;
}
