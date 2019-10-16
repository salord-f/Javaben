mvn exec:java -Dexec.args="MutableHeap add UNSORTED"
mvn exec:java -Dexec.args="MutableHeap add SORTEDASC"
mvn exec:java -Dexec.args="MutableHeap add SORTEDDESC"

mvn exec:java -Dexec.args="ImmutableHeap add UNSORTED"
mvn exec:java -Dexec.args="ImmutableHeap add SORTEDASC"
mvn exec:java -Dexec.args="ImmutableHeap add SORTEDDESC"


mvn exec:java -Dexec.args="MutableHeap pop UNSORTED"
mvn exec:java -Dexec.args="ImmutableHeap pop UNSORTED"