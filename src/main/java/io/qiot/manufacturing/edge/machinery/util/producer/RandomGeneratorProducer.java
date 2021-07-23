package io.qiot.manufacturing.edge.machinery.util.producer;


import java.util.PrimitiveIterator.OfDouble;
import java.util.PrimitiveIterator.OfInt;
import java.util.PrimitiveIterator.OfLong;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class RandomGeneratorProducer {

    public OfInt intRandomNumberGenerator(int min, int max) {
        return new Random().ints(min, max)
                .iterator();
    }

    public OfLong longRandomNumberGenerator(long min, long max) {
        return new Random().longs(min, max)
                .iterator();
    }

    public OfDouble doubleRandomNumberGenerator(double min, double max) {
        return new Random().doubles(min, max)
                .iterator();
    }
}
