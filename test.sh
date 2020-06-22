mkdir -p out
javac -d out src/main/java/com/swissre/cryptocalculator/converter/*.java src/main/java/com/swissre/cryptocalculator/*.java
javac -d out -cp out:lib/junit-platform-console-standalone-1.7.0-M1.jar src/test/java/com/swissre/cryptocalculator/converter/*.java src/test/java/com/swissre/cryptocalculator/*.java
java -jar lib/junit-platform-console-standalone-1.7.0-M1.jar -cp out:src/test/resources --scan-classpath