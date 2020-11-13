package fontys.fhict.nl.domain;

import fontys.fhict.nl.datastructure.CustomBitSet;
import fontys.fhict.nl.datastructure.HuffmanTree;
import org.junit.jupiter.api.Test;

import java.util.BitSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanEncoderTest {
    String message = "Eerie eyes seen near lake.";

    @Test
    void buildHuffmanTree() {
        String text = "Eerie eyes seen near lake.";

        TextFrequencyAnalyzer analyzer = new TextFrequencyAnalyzer();
        Map<Character, Integer> frequencyList = analyzer.calculateFrequency(text);

        System.out.println();
        HuffmanEncoder encoder = new HuffmanEncoder();

        HuffmanTree tree = encoder.buildTree(frequencyList);

        assertNotNull(tree);
    }

    @Test
    void maskedBitSetTest() {
        HuffmanEncoder huffmanEncoder = new HuffmanEncoder();
        CustomBitSet a = new CustomBitSet();
        a.set(0, true);
        a.set(1, true);
        a.set(2, true);

        CustomBitSet b = new CustomBitSet();
        b.set(1, true);
        b.set(2, true);
        b.set(3, true);

        CustomBitSet s = huffmanEncoder.maskedBitSet(b, new int[] { 3 });

        BitSet b2 = new BitSet();
        b2.set(4, true);
        b2.set(5, true);
        b2.set(6, true);

        assertEquals(s.getData(), b2);

        a.or(s);
        System.out.println(a);
    }

    @Test
    void huffmanCodeMapTest() {
        TextFrequencyAnalyzer analyzer = new TextFrequencyAnalyzer();
        Map<Character, Integer> frequencyList = analyzer.calculateFrequency(message);

        HuffmanEncoder encoder = new HuffmanEncoder();

        HuffmanTree tree = encoder.buildTree(frequencyList);

        Map<Character, CustomBitSet> map = encoder.generateEncodingMap(tree);

        for (Character name: map.keySet()){
            char key = name.charValue();
            System.out.println("char: " + key + " code: " + map.get(name) + " , code length: " + map.get(name).getDataPosition().cardinality());
        }

        assertEquals(map.size(), 12);
    }

    @Test
    void huffManTreeTraversal() {
        String text = "Eerie eyes seen near lake.";

        TextFrequencyAnalyzer analyzer = new TextFrequencyAnalyzer();
        Map<Character, Integer> frequencyList = analyzer.calculateFrequency(text);

        HuffmanEncoder encoder = new HuffmanEncoder();

        HuffmanTree tree = encoder.buildTree(frequencyList);
        encoder.treeTraversal(tree);

        assertNotNull(encoder.getEncodingMap());
    }

    @Test
    void encodeTest() {
        String text = "Eerie eyes seen near lake.";
        HuffmanEncoder encoder = new HuffmanEncoder();

        CustomBitSet encodedMessage = encoder.encode(text);

        assertNotNull(encodedMessage);
        System.out.println(encodedMessage);
        bitSetToString(encodedMessage);

        calculateEntropy(text);
    }

    private void calculateEntropy(String message) {
        TextFrequencyAnalyzer frequencyAnalyzer = new TextFrequencyAnalyzer();
        Map<Character, Integer> frequencyList = frequencyAnalyzer.calculateFrequency(message);
        double entropy = 0;
        double totalCharacter = message.length();
        for(Map.Entry<Character, Integer> entry : frequencyList.entrySet() ) {
            double probability = entry.getValue() / totalCharacter;
            entropy += -(probability * ( log2(probability)) );
        }
        System.out.println(entropy);
    }

    private double log2(double value) {
        double result = Math.log(value) / Math.log(2);
        return result;
    }

    // this func is used to print bitset on console
    public void bitSetToString(CustomBitSet code) {
        String s = "";
        for (int j = 0; j < code.getDataPosition().cardinality(); j++) {
            if(code.getData().get( j ))
                s += 1;
            else
                s += 0;
        }

        System.out.println(s);
    }
}
