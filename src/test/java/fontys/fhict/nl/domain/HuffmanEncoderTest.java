package fontys.fhict.nl.domain;

import fontys.fhict.nl.datastructure.CustomBitSet;
import fontys.fhict.nl.datastructure.FrequencyDataStructure;
import fontys.fhict.nl.datastructure.HuffmanTree;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class HuffmanEncoderTest {

    @Test
    void calculateFrequencyTest() {
        String text = "Eerie eyes seen near lake.";

        TextFrequencyAnalizer analyzer = new TextFrequencyAnalizer();
        List<FrequencyDataStructure> frequency = analyzer.calculateFrequency(text);

        for (FrequencyDataStructure name: frequency){
            System.out.println(name.getCharacter() + ": " + name.getFrequency());
        }
    }

    @Test
    void buildHuffmanTree() {
        String text = "Eerie eyes seen near lake.";

        TextFrequencyAnalizer analizer = new TextFrequencyAnalizer();
        List<FrequencyDataStructure> frequencyList = analizer.calculateFrequency(text);

        System.out.println();
        HuffmanEncoder encoder = new HuffmanEncoder();

        HuffmanTree tree = encoder.buildTree(frequencyList);
    }

    @Test
    void huffmanCodeMaptest() {
        String text = "Eerie eyes seen near lake.";

        TextFrequencyAnalizer analyzer = new TextFrequencyAnalizer();
        List<FrequencyDataStructure> frequencyList = analyzer.calculateFrequency(text);

        HuffmanEncoder encoder = new HuffmanEncoder();

        HuffmanTree tree = encoder.buildTree(frequencyList);

        Map<Character, CustomBitSet> map = encoder.generateEncodingMap(tree);

        for (Character name: map.keySet()){
            char key = name.charValue();
            System.out.println(key + " " + map.get(name) + " ," + map.get(name).getDataPosition().cardinality());
        }
    }

    @Test
    void huffManTreetravers() {
        String text = "Eerie eyes seen near lake.";

        TextFrequencyAnalizer analyzer = new TextFrequencyAnalizer();
        List<FrequencyDataStructure> frequencyList = analyzer.calculateFrequency(text);

        HuffmanEncoder encoder = new HuffmanEncoder();

        HuffmanTree tree = encoder.buildTree(frequencyList);
        encoder.treeTraversal(tree, new CustomBitSet());
    }

    @Test
    void encodeTest() {
        String text = "Eerie eyes seen near lake.";
        HuffmanEncoder encoder = new HuffmanEncoder();

        String encodedMessage = encoder.encode(text);

        System.out.println(encodedMessage);
    }

    @Test
    void decodeTest() {
        String text = "This is a test of Huffman coding!";
        HuffmanEncoder encoder = new HuffmanEncoder();
        HuffmanDecoder decoder = new HuffmanDecoder();

        String encodedMessage = encoder.encode(text);
        System.out.println(encodedMessage);

        HuffmanTree tree= encoder.getTree();

        String decoded = decoder.decode(encodedMessage, tree);
        System.out.println(decoded);

        float efficiency = ((float)encodedMessage.length() / (text.length()*8))*100;
        System.out.println("Efficiency: " + (int)efficiency + "%");
    }
}
