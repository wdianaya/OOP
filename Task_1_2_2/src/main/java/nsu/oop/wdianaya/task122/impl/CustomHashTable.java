package nsu.oop.wdianaya.task122.impl;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import nsu.oop.wdianaya.task122.util.Constants;
import nsu.oop.wdianaya.task122.CustomMap;
import nsu.oop.wdianaya.task122.util.MurmurHash;

/**
 * Реализация хештаблицы.
 */
public class CustomHashTable<K, V> implements CustomMap<K, V> {
    private List<Node<K, V>> table;
    private int modificationCount;
    private int size;

    /**
     * Создает пустую хеш-таблицу с начальной емкостью по умолчанию.
     */
    public CustomHashTable() {
        this.table = new ArrayList<>(Constants.INITIAL_CAPACITY);
        for (int i = 0; i < Constants.INITIAL_CAPACITY; ++i) {
            this.table.add(null);
        }

        this.size = 0;
        this.modificationCount = 0;
    }

    /**
     * Внутренний класс узла, хранящий пару ключ-значение и ссылку на следующий узел.
     */
    private static class Node<K, V> implements CustomEntry<K, V> {
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
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }
    }

    /**
     * Внутренний итератор.
     * Отслеживает изменение структуры таблицы
     * для выброса ConcurrentModificationException.
     */
    private class CustomIterator implements Iterator<CustomEntry<K, V>> {
        private final int expectedModificationCount = modificationCount;
        private final Iterator<CustomEntry<K, V>> listIterator;

        private CustomIterator(List<CustomEntry<K, V>> listIterator) {
            this.listIterator = listIterator.iterator();
        }

        @Override
        public boolean hasNext() {
            return listIterator.hasNext();
        }

        @Override
        public CustomEntry<K, V> next() {
            if (modificationCount != expectedModificationCount) {
                throw new ConcurrentModificationException();
            }
            return listIterator.next();
        }
    }

    @Override
    public void addValue(K key, V value) {
        int index = getIndex(key);
        Node<K, V> head = table.get(index);

        Node<K, V> current = head;
        while (current != null) {
            if (Objects.equals(current.getKey(), key)) {
                current.value = value; // Ключ есть, просто обновляем значение
                return;
            }
            current = current.next;
        }

        // Ключа нет, создаем новый узел и ставим его в начало бакета
        Node<K, V> newNode = new Node<>(getHash(key), key, value, head);
        table.set(index, newNode);

        size++;
        modificationCount++;
        if (size >= table.size() * Constants.LOAD_FACTOR) {
            resize();
        }
    }

    @Override
    public void deleteValue(K key) {
        int index = getIndex(key);
        Node<K, V> current = table.get(index);
        Node<K, V> previous = null;

        while (current != null) {
            if (Objects.equals(current.getKey(), key)) {
                if (previous == null) {
                    table.set(index, current.next);
                } else {
                    previous.next = current.next;
                }

                size--;
                modificationCount++;
                return;
            }
            previous = current;
            current = current.next;
        }
    }

    @Override
    public V getValue(K key) {
        Node<K, V> node = findNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void updateValue(K key, V value) {
        Node<K, V> node = findNode(key);
        if (node != null) {
            node.value = value;
        }
    }

    @Override
    public boolean isExistValue(K key) {
        return findNode(key) != null;
    }

    @Override
    public Set<CustomEntry<K, V>> entrySet() {
        List<CustomEntry<K, V>> listNode = new ArrayList<>();
        for (Node<K, V> head : table) {
            Node<K, V> current = head;
            while (current != null) {
                listNode.add(current);
                current = current.next;
            }
        }
        return new AbstractSet<CustomEntry<K, V>>() {
            @Override
            public Iterator<CustomEntry<K, V>> iterator() {
                return new CustomIterator(listNode);
            }

            @Override
            public int size() {
                return listNode.size();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("CustomHashTable {\n");
        for (int i = 0; i < table.size(); ++i) {
            string.append("  Bucket ").append(i).append(": ");
            Node<K, V> current = table.get(i);
            if (current == null) {
                string.append("empty\n");
            } else {
                while (current != null) {
                    string.append("[")
                            .append(current.key)
                            .append("=")
                            .append(current.value)
                            .append("]");
                    if (current.next != null) {
                        string.append(" -> ");
                    }
                    current = current.next;
                }
                string.append("\n");
            }
        }
        return string.append("}\n").toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        CustomHashTable<?, ?> otherTable = (CustomHashTable<?, ?>) obj;
        if (this.size != otherTable.size) {
            return false;
        }
        for (Node<K, V> head : table) {
            Node<K, V> current = head;
            while (current != null) {
                Object otherValue = ((CustomHashTable) otherTable).getValue(current.key);
                if (otherValue == null) {
                    if (current.value != null
                            || !((CustomHashTable) otherTable).isExistValue(current.key)) {
                        return false;
                    }
                } else if (!Objects.equals(current.value, otherValue)) {
                    return false;
                }

                current = current.next;
            }
        }
        return true;
    }

    /**
     * Вычисляет индекс бакета для ключа.
     * Использует хеш MurmurHash, берет модуль для защиты от отрицательных чисел.
     */
    private int getIndex(K key) {
        return Math.abs(MurmurHash.hash(key)) % table.size();
    }

    /**
     * Ищет узел по ключу в связном списке конкретного бакета.
     *
     * @param key искомый ключ
     * @return найденный узел или null
     */
    private Node<K, V> findNode(K key) {
        int index = getIndex(key);
        Node<K, V> current = table.get(index);

        while (current != null) {
            if (Objects.equals(current.getKey(), key)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Возвращает хеш для ключа, используя алгоритм MurmurHash.
     */
    private int getHash(K key) {
        return MurmurHash.hash(key);
    }

    /**
     * Расширяет таблицу. Увеличивает количество бакетов в 2 раза
     * и перераспределяет все существующие элементы по новым индексам.
     */
    private void resize() {
        List<Node<K, V>> oldTable = this.table;
        int newCapacity = oldTable.size() * 2;
        this.table = new ArrayList<>(newCapacity);
        for (int i = 0; i < newCapacity; i++) {
            this.table.add(null);
        }

        this.size = 0;

        for (Node<K, V> head : oldTable) {
            Node<K, V> current = head;
            while (current != null) {
                Node<K, V> nextNode = current.next;

                int newIndex = getIndex(current.getKey());

                current.next = this.table.get(newIndex);
                this.table.set(newIndex, current);

                this.size++;
                current = nextNode;
            }
        }
    }
}
