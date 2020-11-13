package fontys.fhict.nl.domain;

import fontys.fhict.nl.datastructure.CustomBitSet;
import fontys.fhict.nl.datastructure.HuffmanTree;
import org.junit.jupiter.api.Test;

import java.util.BitSet;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanDecoderTest {
    @Test
    void decodeTest() {
        String message = "Eerie eyes seen near lake.";
        HuffmanEncoder encoder = new HuffmanEncoder();
        HuffmanDecoder decoder = new HuffmanDecoder();

        // encode
        CustomBitSet encodedMessage = encoder.encode(message);
        HuffmanTree tree = encoder.getTree();

        String decodedMessage = decoder.decode(encodedMessage, tree);
        System.out.println(decodedMessage);

        float efficiency = ((float)encodedMessage.getDataPosition().cardinality() / (message.length()*8))*100;
        System.out.println("Efficiency: " + (int)efficiency + "%");

        assertEquals(message, decodedMessage);
    }
}