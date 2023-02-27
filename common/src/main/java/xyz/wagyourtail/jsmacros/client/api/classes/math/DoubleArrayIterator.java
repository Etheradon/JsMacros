package xyz.wagyourtail.jsmacros.client.api.classes.math;

import java.util.Iterator;

/**
 * @author Etheradon
 * @since 1.8.4
 */
public class DoubleArrayIterator implements Iterator<Double> {

    private final double[] array;
    private int index;

    public DoubleArrayIterator(double[] array) {
        if (array == null) {
            throw new NullPointerException(array + " is null");
        }
        this.array = array;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < array.length;
    }

    @Override
    public Double next() {
        return array[index++];
    }

}