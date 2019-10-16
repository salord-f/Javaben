mvn exec:java -Dexec.args="MutableAVL insert UNSORTEDSET"
mvn exec:java -Dexec.args="MutableAVL insert SORTEDASC"
mvn exec:java -Dexec.args="MutableAVL insert SORTEDDESC"
mvn exec:java -Dexec.args="ImmutableAVL insert UNSORTEDSET"
mvn exec:java -Dexec.args="ImmutableAVL insert SORTEDASC"
mvn exec:java -Dexec.args="ImmutableAVL insert SORTEDDESC"

mvn exec:java -Dexec.args="MutableAVL search UNSORTEDSET"
mvn exec:java -Dexec.args="MutableAVL delete UNSORTEDSET"

mvn exec:java -Dexec.args="ImmutableAVL search UNSORTEDSET"
mvn exec:java -Dexec.args="ImmutableAVL delete UNSORTEDSET"