package nsu.oop.wdianaya.task131;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class IOFileReader {

    public static BufferedReader openReader(String fileName) throws IOException {
        Path path = Path.of(fileName);
        if (!Files.exists(path)) {
            throw new FileNotFoundException("Файл не найден по пути: " + path.toAbsolutePath());
        }
        return Files.newBufferedReader(path, StandardCharsets.UTF_8);
    }
}