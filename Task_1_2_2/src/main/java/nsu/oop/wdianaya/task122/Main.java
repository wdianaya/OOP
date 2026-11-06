package nsu.oop.wdianaya.task122;

import nsu.oop.wdianaya.task122.impl.CustomHashTable;

/**
 * Пример работы программы.
 */
public class Main {
    public static void main(String[] args) {
        CustomMap<Integer, String> hashTable = new CustomHashTable<>();
        hashTable.addValue(1, "one");
        hashTable.addValue(2, "two");
        System.out.println(hashTable.getValue(2));
        hashTable.addValue(3, "three");
        hashTable.updateValue(2, "double");
        System.out.println(hashTable.getValue(2));
        System.out.println(hashTable.isExistValue(1));

        for (CustomMap.CustomEntry<Integer, String> elem : hashTable.entrySet()) {
            System.out.println(elem.getKey() + " " + elem.getValue());
        }

        try {
            for (CustomMap.CustomEntry<Integer, String> elem : hashTable.entrySet()) {
                System.out.println(elem.getKey() + " " + elem.getValue());
                hashTable.deleteValue(2);
            }
        } catch (java.util.ConcurrentModificationException e) {
            System.out.println("Поймали ошибку! Нельзя удалять элементы прямо во время цикла for-each.");
        }

        System.out.println(hashTable);
        CustomMap<Integer, String> hashTable2 = new CustomHashTable<>();
        hashTable2.addValue(1, "one");
        hashTable2.addValue(3, "three");
        System.out.println(hashTable.equals(hashTable2));
    }
}