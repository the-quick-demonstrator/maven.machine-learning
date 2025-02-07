package io.github.ailearner;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.nd4j.linalg.api.ndarray.INDArray;

class MultiLayerPerceptionExampleTest {

    @Test
    public void testMultiLayerPerceptionExample() {
        final MultiLayerPerceptionExample mlp = new MultiLayerPerceptionExample();
        final long startTime = System.currentTimeMillis();
        final Pair<INDArray, INDArray> XY = mlp.prepareTrainingData(System.getProperty("user.dir") + "/src/main/resources/names.txt");
        final long endTime = System.currentTimeMillis();
        System.out.printf("Training Completed in %d ms\n", endTime - startTime);
    }
}