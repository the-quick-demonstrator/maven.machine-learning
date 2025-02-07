package io.github.ailearner;

import io.github.ailearner.utils.DirectoryReference;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class NameToINDArrayTest {


    @Test
    public void testNameToINDArray() throws IOException {
        final long startTime = System.currentTimeMillis();
        // Read file (assuming it contains only one name)
        final String filePath = DirectoryReference.RESOURCE.getAbsolutePath("/names.txt");
        final NameToINDArray nameToINDArray = new NameToINDArray(filePath);
        nameToINDArray.run();
        final long endTime = System.currentTimeMillis();
        System.out.printf("Training Completed in %d ms\n", endTime - startTime);
    }

}