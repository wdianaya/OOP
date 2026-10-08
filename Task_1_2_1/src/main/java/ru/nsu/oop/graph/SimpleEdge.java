package ru.nsu.oop.graph;

public record SimpleEdge<V>(V from, V to) implements Edge<V> { }