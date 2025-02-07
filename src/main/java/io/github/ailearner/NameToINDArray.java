package io.github.ailearner;

import io.github.ailearner.utils.DirectoryReference;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class NameToINDArray {
    private static final int BLOCK_SIZE = 3;
    private static final int END_TOKEN = 0; // Representing end of input
    private final List<String> lines;

    public NameToINDArray(final String filePath) throws IOException {
        this.lines = Files.readAllLines(Path.of(filePath));
    }

    public void run() {
        // Read name and process
        for (final String name : lines) {
            final int[] encodedName = encodeName(name);

            // Generate X and Y INDArrays
            final List<INDArray> xList = new ArrayList<>();
            final List<INDArray> yList = new ArrayList<>();

            System.out.println("Word: " + name);
            for (int i = 0; i <= encodedName.length - 1; i++) {
                final int[] context = getContext(encodedName, i);
                final INDArray x = Nd4j.create(new int[][]{context});
                final INDArray y = Nd4j.create(new int[][]{{encodedName[i]}});

                xList.add(x);
                yList.add(y);

                System.out.printf("Given: %s  --> Expect: %.4f%n", x, y.getDouble(0));
            }
        }
    }

    private static int[] encodeName(String name) {
        final int[] encoded = new int[name.length() + 1]; // +1 for END_TOKEN
        for (int i = 0; i < name.length(); i++) {
            encoded[i] = name.charAt(i) - 'a' + 1; // 'a' -> 1, 'b' -> 2, ..., 'z' -> 26
        }
        encoded[name.length()] = END_TOKEN; // Add END_TOKEN
        return encoded;
    }

    private static int[] getContext(
            final int[] encodedName, final int index) {
        final int[] context = new int[BLOCK_SIZE];
        final int start = index - BLOCK_SIZE;

        for (int i = 0; i < BLOCK_SIZE; i++) {
            final int pos = start + 1 + i;
            context[i] = (pos < 0) ? END_TOKEN : encodedName[Math.min(pos, encodedName.length - 1)];
        }
        return context;
    }
}
