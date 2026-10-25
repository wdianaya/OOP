package nsu.oop.wdianaya.task122.impl;

import nsu.oop.wdianaya.task122.util.Constants;
import nsu.oop.wdianaya.task122.CustomMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class CustomHashTable<K, V> implements CustomMap<K, V> {
    private final List<Node<K, V>> table;
    private int size;

    public CustomHashTable() {
        this.table = new ArrayList<>(Constants.INITIAL_CAPACITY);
        for (int i = 0; i < Constants.INITIAL_CAPACITY; ++i) {
            this.table.add(null);
        }

        this.size = 0;
    }

    public static class Node<K, V> implements CustomEntry<K, V> {
        final K key;
        final int hash;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.key = key;
            this.hash = hash;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return null;
        }

        @Override
        public V getValue() {
            return null;
        }
    }

    @Override
    public void addValue(K key, V value) {

    }

    @Override
    public void deleteValue(K key) {

    }

    @Override
    public V getValue(K key) {
        return null;
    }

    @Override
    public void updateValue(K key, V value) {

    }

    @Override
    public boolean isExistValue(K key) {
        return false;
    }

    @Override
    public Set<CustomEntry<K, V>> entrySet() {
        return Set.of();
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean equals(Object otherTable) {
        return true;
    }
}
