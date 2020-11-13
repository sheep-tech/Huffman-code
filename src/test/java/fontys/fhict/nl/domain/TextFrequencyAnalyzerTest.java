package fontys.fhict.nl.domain;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TextFrequencyAnalyzerTest {
    @Test
    void calculateFrequencyTest() {
        String text = "Eerie eyes seen near lake.";

        TextFrequencyAnalyzer analyzer = new TextFrequencyAnalyzer();
        Map<Character, Integer> frequency = analyzer.calculateFrequency(text);

        for (Map.Entry<Character, Integer> name : frequency.entrySet()){
            System.out.println(name.getValue() + ": " + name.getKey());
        }

        // char 'e' has frequency 8
        assertEquals(frequency.get('e'), 8);
    }
}