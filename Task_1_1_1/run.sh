#!/bin/sh

javac -d bin src/main/java/org/example/HeapSort.java
javadoc -d docs src/main/java/org/example/HeapSort.java
jar cvfe heapsort.jar org.example.HeapSort -C bin .
java -jar heapsort.jar