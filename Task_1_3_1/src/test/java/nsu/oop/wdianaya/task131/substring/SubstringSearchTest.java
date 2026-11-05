package nsu.oop.wdianaya.task131.substring;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class SubstringSearchTest {
    @Test
    void testNullSubstringThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                SubstringSearch.find(new StringReader("текст"), null));
    }

    @Test
    void testEmptySubstring() throws IOException {
        List<Integer> result = SubstringSearch.find(new StringReader("текст"), "");
        assertTrue(result.isEmpty());
    }

    @Test
    void testBasicSearch() throws IOException {
        List<Integer> result = SubstringSearch.find(
                new StringReader("абракадабра"), "бра");
        assertEquals(List.of(1, 8), result);
    }

    @Test
    void testRepeatedCharacters() throws IOException {
        List<Integer> result = SubstringSearch.find(new StringReader("aaaaa"), "aa");
        assertEquals(List.of(0, 1, 2, 3), result);
    }

    @Test
    void testFileNotFound() {
        assertThrows(FileNotFoundException.class, () ->
                SubstringSearch.find("non_existent_file.txt", "тест")
        );
    }
}