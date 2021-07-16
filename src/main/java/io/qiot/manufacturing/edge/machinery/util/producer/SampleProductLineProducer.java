package io.qiot.manufacturing.edge.machinery.util.producer;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import io.qiot.manufacturing.edge.machinery.domain.productline.ColorRangesDTO;
import io.qiot.manufacturing.edge.machinery.domain.productline.PackagingRangesDTO;
import io.qiot.manufacturing.edge.machinery.domain.productline.PrintingRangesDTO;
import io.qiot.manufacturing.edge.machinery.domain.productline.ProductLineDTO;
import io.qiot.manufacturing.edge.machinery.domain.productline.SizeChartRangesDTO;

@ApplicationScoped
public class SampleProductLineProducer {

    public ProductLineDTO generateProductLine() {
        ProductLineDTO pl = new ProductLineDTO();
        
        pl.productLineId=UUID.randomUUID();

        pl.sizeChart = size();

        pl.color = color();

        pl.print = printing();

        pl.packaging = packaging();
        return pl;
    }

    private SizeChartRangesDTO size() {
        SizeChartRangesDTO sizeChart = new SizeChartRangesDTO();
        sizeChart.chestMin = 21.5;
        sizeChart.chestMax = 23;
        sizeChart.shoulderMin = 17.5;
        sizeChart.shoulderMax = 18.5;
        sizeChart.backMin = 27.5;
        sizeChart.backMax = 28.5;
        sizeChart.waistMin = 21.5;
        sizeChart.waistMax = 22.5;
        sizeChart.hipMin = 21;
        sizeChart.hipMax = 22;
        return sizeChart;
    }

    private ColorRangesDTO color() {
        ColorRangesDTO color = new ColorRangesDTO();
        color.redMin = 250;
        color.redMax = 255;
        color.greenMin = 0;
        color.greenMax = 5;
        color.blueMin = 0;
        color.blueMax = 5;
        return color;
    }

    private PrintingRangesDTO printing() {
        PrintingRangesDTO printing = new PrintingRangesDTO();
        printing.min = 0.1;
        printing.max = 0.98;
        return printing;
    }

    private PackagingRangesDTO packaging() {
        PackagingRangesDTO packaging = new PackagingRangesDTO();
        packaging.min = 0.1;
        packaging.max = 0.98;
        return packaging;
    }
}
