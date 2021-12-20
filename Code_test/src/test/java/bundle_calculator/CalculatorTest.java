package bundle_calculator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testExceptionIsThrown() {
        Calculator tester = new Calculator();
        assertThrows(IllegalArgumentException.class, () -> tester.getImgBundle(-10, tester.getImgBundle()));
    }

    @Test
    void testGetImgBundle(){
        Calculator tester = new Calculator();
        ArrayList<Integer> testResult = new ArrayList<Integer>();
        testResult.add(0);
        testResult.add(1);
        assertEquals(testResult, tester.getImgBundle(10, tester.getImgBundle()));
    }

    @Test
    void testGetBundle(){
        Calculator tester = new Calculator();
        ArrayList<Integer> testResult = new ArrayList<Integer>();
        testResult.add(0);
        testResult.add(1);
        testResult.add(1);
        assertEquals(testResult, tester.getBundle(13, tester.getAudioBundle()));
    }

}