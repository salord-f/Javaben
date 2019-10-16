mvn exec:java -Dexec.args="MutableArray set-random UNSORTED"
mvn exec:java -Dexec.args="MutableArray get-random UNSORTED"
mvn exec:java -Dexec.args="MutableArray set-sequential UNSORTED"
mvn exec:java -Dexec.args="MutableArray get-sequential UNSORTED"

mvn exec:java -Dexec.args="ImmutableArray set-random UNSORTED"
mvn exec:java -Dexec.args="ImmutableArray get-random UNSORTED"
mvn exec:java -Dexec.args="ImmutableArray set-sequential UNSORTED"
mvn exec:java -Dexec.args="ImmutableArray get-sequential UNSORTED"

mvn exec:java -Dexec.args="NativeArray set-random UNSORTED"
mvn exec:java -Dexec.args="NativeArray get-random UNSORTED"
mvn exec:java -Dexec.args="NativeArray set-sequential UNSORTED"
mvn exec:java -Dexec.args="NativeArray get-sequential UNSORTED"

