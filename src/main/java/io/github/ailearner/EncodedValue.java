package io.github.ailearner;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.shade.jackson.databind.ObjectMapper;

import java.util.List;

public class EncodedValue {
    private static final int END_TOKEN = 0; // Representing end of input
    private static final int BLOCK_SIZE = 3;

    private final List<INDArray> xList;
    private final List<INDArray> yList;
    private final String name;
    private int[] encodedName;
    private int[] context;

    public EncodedValue(List<INDArray> xList, List<INDArray> yList, String name) {
        this.xList = xList;
        this.yList = yList;
        this.name = name;
    }

    public void process() {
        final int[] encodedName = getEncodedName();

        // Generate X and Y INDArrays
        for (int i = 0; i <= encodedName.length - 1; i++) {
            final int[] context = getContext(encodedName, i);
            final INDArray x = Nd4j.create(new int[][]{context});
            final INDArray y = Nd4j.create(new int[][]{{encodedName[i]}});

            xList.add(x);
            yList.add(y);
        }
    }

    public int[] getEncodedName() {
        if(this.encodedName == null) {
            final int[] encoded = new int[name.length() + 1]; // +1 for END_TOKEN
            for (int i = 0; i < name.length(); i++) {
                encoded[i] = name.charAt(i) - 'a' + 1; // 'a' -> 1, 'b' -> 2, ..., 'z' -> 26
            }
            encoded[name.length()] = END_TOKEN; // Add END_TOKEN
            this.encodedName = encoded;
        }
        return this.encodedName;
    }


    public int[] getContext(final int[] encodedName, final int index) {
        if(this.context == null){
            final int[] context = new int[BLOCK_SIZE];
            final int start = index - BLOCK_SIZE;

            for (int i = 0; i < BLOCK_SIZE; i++) {
                final int pos = start + 1 + i;
                context[i] = (pos < 0) ? END_TOKEN : encodedName[Math.min(pos, encodedName.length - 1)];
            }
            this.context = context;
        }
        return this.context;
    }

    // picked up by Jackson for JSON toString
    public String getName() {
        return name;
    }

    // picked up by Jackson for JSON toString
    public int[] getContext() {
        return context;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (Exception e) {
            return "EncodedValue{" + "xList=" + xList + ", yList=" + yList + ", name='" + name + '\'' + '}';
        }
    }
}