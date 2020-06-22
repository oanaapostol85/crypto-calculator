# Crypto Wallet Calculator
Crypto currency to EUR calculator.
Application input: 
- the path of the file containing crypto currencies amount

## Crypto Wallet File Content Example  

    BTC=10
    ETH=5
    XRP=2000   
    
## Getting Started 

### Getting Started with Java
- Run the following commands in the project root:

      mkdir out;
      javac -d out src/main/java/com/swissre/cryptocalculator/converter/*.java;
      javac -d out -cp out src/main/java/com/swissre/cryptocalculator/*.java;
      java -cp out com.swissre.cryptocalculator.CryptoWalletApplication;
      
- Enter the path of the file containing crypto currencies amount when prompted 
   
#### Run unit tests with JUnit 5 Platform
- Run previous steps to compile the classes under test
- Run the following commands in the project root:

      javac -d out -cp out:lib/junit-platform-console-standalone-1.7.0-M1.jar src/test/java/com/swissre/cryptocalculator/converter/*.java;
      javac -d out -cp out:lib/junit-platform-console-standalone-1.7.0-M1.jar src/test/java/com/swissre/cryptocalculator/*.java;
      java -jar lib/junit-platform-console-standalone-1.7.0-M1.jar -cp out:src/test/resources --scan-classpath;  

### Getting Started with Gradle Wrapper
- Run the following command in the project root:

      ./gradlew run

- Enter the path of the file containing crypto currencies amount when prompted 
   

#### Run unit tests with Gradle Wrapper
- Run the following command in the project root:

      ./gradlew test  