package io.github.ailearner.utils;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NNTrainingDataHelper {

    public Map<String, Integer> createCharToIdxMap() {
        Map<String, Integer> charToIntegerAlphabet = IntStream.rangeClosed('a', 'z')
                .boxed() //.mapToObj(c -> c), difference?
                .collect(Collectors.toMap(
                        // not a fan of  this logic for map; not easily readable;
                        // 'a' is ASCII value, subtract from c, convert to int, add 1.
                        c -> String.valueOf((char) c.intValue()), c -> c - 'a' + 1)
                );
        // create map of idx to alphanumeric char
        charToIntegerAlphabet.put(".", 0);

        return charToIntegerAlphabet;
    }

}
