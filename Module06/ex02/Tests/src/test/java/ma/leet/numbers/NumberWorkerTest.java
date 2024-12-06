package ma.leet.numbers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvFileSource;


import java.io.File;



class NumberWorkerTest {
    
    
    @ParameterizedTest
    @ValueSource(ints = {7,13,31})
    void isPrimeForPrimes(int argument) {
        var numberWorker = new NumberWorker();
        assertTrue(numberWorker.isPrime(argument));
    }

    @ParameterizedTest
    @ValueSource(ints = {9, 15, 21, 25})
    void isPrimeForNotPrimes (int argument) {
        var numberWorker = new NumberWorker();

        assertFalse(numberWorker.isPrime(argument));
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void isPrimeForIncorrectNumbers (int number, int digitSum) {
        var numberWorker = new NumberWorker();
        assertEquals(numberWorker.digitsSum(number), digitSum);
    }
}