package io.github.ailearner;

import io.github.ailearner.utils.FileHandler;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestUtilityOperations {

    @Test
    public void TestReadWordsFromFile() {
        //given
        FileHandler fh = new FileHandler();
        List<String> arrayList = new ArrayList<>();
        String file_path = "/main/java/resources/names.txt";

        //When
        fh.readWordsFromFile(System.getProperty("user.dir")+file_path).forEach(word -> arrayList.add(word));

        //Then
        arrayList.forEach(System.out::println);
        assert false;
    }
}
