package fontys.fhict.nl;

import fontys.fhict.nl.datastructure.CustomBitSet;
import fontys.fhict.nl.datastructure.HuffmanTree;
import fontys.fhict.nl.domain.HuffmanDecoder;
import fontys.fhict.nl.domain.HuffmanEncoder;

public class Main {
    public static void main(String[] args) {
        String message = "Eerie eyes seen near lake.";
        HuffmanEncoder encoder = new HuffmanEncoder();
        HuffmanDecoder decoder = new HuffmanDecoder();

        // encode
        CustomBitSet encodedMessage = encoder.encode(message);
        HuffmanTree tree = encoder.getTree();

        // show encoded message
        System.out.print("Encoded Message: ");
        bitSetToString(encodedMessage);

        String decodedMessage = decoder.decode(encodedMessage, tree);
        System.out.println("Decoded Message: " + decodedMessage);

        float efficiency = ((float)encodedMessage.getDataPosition().cardinality() / (message.length()*8))*100;
        System.out.println("Encoding Efficiency: " + (int)efficiency + "%");
    }

    // this func is used to print bitset on console
    public static void bitSetToString(CustomBitSet code) {
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
