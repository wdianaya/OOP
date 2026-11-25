package nsu.oop.wdianaya.task131;

import java.io.FileNotFoundException;
import java.io.IOException;

import nsu.oop.wdianaya.task131.substring.SubstringSearch;

/**
 * Класс, демонстриурющий работу программы.
 */
public class Main {
    /**
     * Точка входа в программу.
     */
    public static void main(String[] args) {
        String filename = "src/main/resources/input.txt";
        String substring = "бра";
        try {
            System.out.println(SubstringSearch.find(filename, substring));
        } catch (FileNotFoundException | IllegalArgumentException e) {
            System.err.println("Ошибка во входных данных: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        }
    }
}