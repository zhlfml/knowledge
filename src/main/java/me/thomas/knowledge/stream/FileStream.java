package me.thomas.knowledge.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author zhaoxinsheng
 * @date 29/08/2017.
 */
public class FileStream {

    public static void main(String[] args) {
        long uniqueWords = 0;
        String filePath = "/Users/thomas/send-message.json";
        try (Stream<String> lines =  Files.lines(Paths.get(filePath), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();

        } catch (IOException ioe) {
            System.err.println("No such file: " + filePath);
        }

        System.out.println(uniqueWords);
    }
}
