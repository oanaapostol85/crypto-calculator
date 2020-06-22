if not exist out mkdir out
javac -d out src/main/java/com/swissre/cryptocalculator/converter/*.java src/main/java/com/swissre/cryptocalculator/*.java
java -cp out com.swissre.cryptocalculator.CryptoWalletApplication