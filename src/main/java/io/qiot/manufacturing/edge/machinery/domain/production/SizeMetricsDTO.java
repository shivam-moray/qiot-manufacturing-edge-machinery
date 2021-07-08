package io.qiot.manufacturing.edge.machinery.domain.production;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class SizeMetricsDTO {
    public float chest;
    public float shoulder;
    public float backLength;
    public float waist;
    public float hip;
}
