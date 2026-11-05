package nsu.oop.wdianaya.task131.substring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LargeFileTest {
    @Test
    void testSearchInLargeGeneratedFile(@TempDir Path tempDir) throws IOException {
        Path largeFile = tempDir.resolve("large_input.txt");

        String target = "ИСКОМАЯ_СТРОКА";
        int repeats = 500_000;
        try (BufferedWriter writer = Files.newBufferedWriter(largeFile, StandardCharsets.UTF_8)) {
            writer.write(target);

            for (int i = 0; i < repeats; i++) {
                writer.write("случайный текст для наполнения объема файла ");
            }

            writer.write(target);
        }
        List<Integer> result = SubstringSearch.find(largeFile.toString(), target);

        assertEquals(2, result.size());
        assertEquals(0, result.get(0));
    }
}
