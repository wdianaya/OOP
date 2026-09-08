#!/bin/bash

javac -d build/classes src/main/java/org/example/HeapSort.java
javadoc -encoding UTF-8 -docencoding UTF-8 -charset UTF-8 -d docs src/main/java/org/example/HeapSort.java
jar cfe heapsort.jar org.example.HeapSort -C build/classes .
java -jar heapsort.jar