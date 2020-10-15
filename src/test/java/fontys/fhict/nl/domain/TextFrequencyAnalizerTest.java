package fontys.fhict.nl.domain;

import org.junit.jupiter.api.Test;

import java.util.BitSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TextFrequencyAnalizerTest {

    @Test
    void calculateFrequencyTest() {
        String text = "Eerie eyes seen near lake.";

        TextFrequencyAnalizer analizer = new TextFrequencyAnalizer(text);
        List<FrequencyDataStructure> frequency = analizer.calculateFrequency();

        for (FrequencyDataStructure name: frequency){
            System.out.println(name.getCharacter() + ": " + name.getFrequency());
        }
    }

    @Test
    void insertFrequencyListToPriorityQueueTest() {
        String text = "Eerie eyes seen near lake.";

        TextFrequencyAnalizer analizer = new TextFrequencyAnalizer(text);
        List<FrequencyDataStructure> frequencyList = analizer.calculateFrequency();

        HuffmanCode encoder = new HuffmanCode();

        encoder.insertFrequencyListToPriorityQueue(frequencyList);
    }

    @Test
    void buildHuffmanCodeTree() {
        String text = "Eerie eyes seen near lake.";

        TextFrequencyAnalizer analizer = new TextFrequencyAnalizer(text);
        List<FrequencyDataStructure> frequencyList = analizer.calculateFrequency();

        System.out.println();
        HuffmanCode encoder = new HuffmanCode();

        encoder.buildHuffmanCodeTree(frequencyList);
    }

    @Test
    void encode() {
        String text = "Eerie eyes seen near lake.";

        TextFrequencyAnalizer analizer = new TextFrequencyAnalizer(text);
        List<FrequencyDataStructure> frequencyList = analizer.calculateFrequency();

        System.out.println();
        HuffmanCode encoder = new HuffmanCode();

        encoder.buildHuffmanCodeTree(frequencyList);
        Map<Character, BitSet> map = encoder.encode();

        for (Character name: map.keySet()){
            char key = name.charValue();
            System.out.println(key + " " + map.get(name));
        }

    }
}