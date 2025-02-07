package io.github.ailearner;

import io.github.ailearner.utils.DirectoryReference;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class NameToINDArrayTest {


    @Test
    public void testNameToINDArray() throws IOException {
        // Read file (assuming it contains only one name)
        final String filePath = DirectoryReference.RESOURCE.getAbsolutePath("/names.txt");
        final NameToINDArray nameToINDArray = new NameToINDArray(filePath);
        final long startTime = System.currentTimeMillis();
        final List<EncodedValue> encodedValues = nameToINDArray.getEncodedValues();
        final long endTime = System.currentTimeMillis();
        System.out.println(encodedValues);

        System.out.printf("Training Completed in %d ms\n", endTime - startTime);
    }

}