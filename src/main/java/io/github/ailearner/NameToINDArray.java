package io.github.ailearner;

import org.nd4j.linalg.api.ndarray.INDArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class NameToINDArray {
    private final List<String> lines;

    public NameToINDArray(final String filePath) throws IOException {
        this.lines = Files.readAllLines(Path.of(filePath));
    }

    public List<EncodedValue> getEncodedValues() {
        List<EncodedValue> list = new ArrayList<>();
        // Read name and process
        for (final String name : lines) {
            // Generate X and Y INDArrays
            final List<INDArray> xList = new ArrayList<>();
            final List<INDArray> yList = new ArrayList<>();
            final EncodedValue encodedValue = new EncodedValue(xList, yList, name);
            encodedValue.process();
            list.add(encodedValue);
        }
        return list;
    }
}
