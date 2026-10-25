package nsu.oop.wdianaya.task122;

import java.util.Set;

public interface CustomMap<K, V> {

    void addValue(K key, V value);

    void deleteValue(K key);

    V getValue(K key);

    void updateValue(K key, V value);

    boolean isExistValue(K key);

    Set<CustomEntry<K, V>> entrySet();

    interface CustomEntry<K, V> {
        K getKey();
        V getValue();
    }
}
