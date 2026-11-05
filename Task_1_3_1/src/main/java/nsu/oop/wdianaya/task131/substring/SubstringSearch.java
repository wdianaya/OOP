package nsu.oop.wdianaya.task131.substring;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import nsu.oop.wdianaya.task131.io.IOFileReader;

/**
 * Класс для поиска вхождений подтроки в файле и текстовых потоках.
 * Производит поиск подстроки за время O(N) по длине текста
 * и требует O(M) дополнительной памяти, где M -- длина подстроки.
 */
public class SubstringSearch {
    /**
     * Константа, обозначающая конец файла при посимвольном чтении из {@link Reader}.
     */
    private static final int EOF = -1;

    /**
     * Выполняет поиск всех вхождений заданной подстроки в текстовом потоке.
     * @param reader    поток ввода {@link Reader}, из которого считываются символы
     * @param substring искомая подстрока в кодировке UTF-8
     * @return список индексов начальных позиций всех найденных вхождений;
     * пустой список, если подстрока пуста или вхождения не найдены
     */
    public static List<Integer> find(Reader reader, String substring) throws IOException {
        if (substring == null) {
            throw new IllegalArgumentException("null вместо строки, которую нужно найти");
        } else if (substring.isEmpty()) {
            return List.of();
        }

        List<Integer> resultList = new ArrayList<>();
        int lenSubstring = substring.length();

        List<Integer> prefixFunc = divideSubstringOnSuffixAndPrefix(substring, lenSubstring);

        Integer globalIndex = 0; // глобальный счётчик прочитанных символов
        Integer pointSimbolMatch = 0; // сколько символом совпало прямо сейчас

        int currentChar;
        while ((currentChar = reader.read()) != EOF) {
            char ch = (char) currentChar;
            // при несовпадении откат по префикс функции
            while (pointSimbolMatch > 0
                    && (substring.charAt(pointSimbolMatch) != ch)) {
                pointSimbolMatch = prefixFunc.get(pointSimbolMatch - 1);
            }
            // увеличение счётчика совпавших символов
            if (ch == substring.charAt(pointSimbolMatch)) {
                pointSimbolMatch++;
            }
            // полное совпадение подстроки
            if (pointSimbolMatch == lenSubstring) {
                resultList.add(globalIndex - lenSubstring + 1);
                // откат для поиска пересекающихся подстрок
                pointSimbolMatch = prefixFunc.get(pointSimbolMatch - 1);
            }
            globalIndex++;
        }

        return resultList;
    }

    /**
     * Выполняет поиск всех вхождений заданной подстроки в файле по указанному пути.
     * Перегрузка метода нужна преимущественно для удобства тестирования.
     */
    public static List<Integer> find(String fileName, String substring) throws IOException {
        try (Reader source = IOFileReader.openReader(fileName)) {
            return find(source, substring);
        }
    }

    /**
     * Построение префикс-функции (массива) для данной подстроки.
     * Для каждого символа подстроки вычисляется, какова длина
     * самого длинного префикса, который одновременной является суффиксом этой подстроки.
     * @param substring искомая подстрока
     * @param len       длина подстроки
     * @return список значений префикс-функции длины {@code len}
     */
    private static List<Integer> divideSubstringOnSuffixAndPrefix(String substring, int len) {
        List<Integer> result = new ArrayList<>();
        result.add(0); // у первого символа нет префикса/суффикса, поэтому 0 совпадений
        int prefixIndex = 0; // длина текущего совпадающего префикса
        for (int suffixIndex = 1; suffixIndex < len; ++suffixIndex) {
            // пока символы не совпадают, откатываем prefixIndex по уже просчитанному массиву
            while (prefixIndex > 0
                    && substring.charAt(prefixIndex) != substring.charAt(suffixIndex)) {
                prefixIndex = result.get(prefixIndex - 1);
            }
            // если символы совпали, увеличиваем длину префикса
            if (substring.charAt(prefixIndex) == substring.charAt(suffixIndex)) {
                prefixIndex++;
            }

            result.add(prefixIndex);
        }
        return result;
    }

}
