mvn exec:java -Dexec.args="MutableQueue enqueue UNSORTED"
mvn exec:java -Dexec.args="ImmutableQueue enqueue UNSORTED"

mvn exec:java -Dexec.args="MutableQueue dequeue UNSORTED"
mvn exec:java -Dexec.args="ImmutableQueue dequeue UNSORTED"