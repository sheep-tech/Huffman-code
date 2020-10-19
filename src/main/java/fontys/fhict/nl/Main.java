package fontys.fhict.nl;

import fontys.fhict.nl.domain.HuffmanEncoder;

public class Main {
    public static void main(String[] args) {
        String text = "Eerie eyes seen near lake.";

        System.out.println();
        HuffmanEncoder encoder = new HuffmanEncoder();
//        BitSet encodedMessage = encoder.encode(text);
        String encodedMessage = encoder.encode(text);

        System.out.println(encodedMessage);

//        HuffmanDecoder decoder = new HuffmanDecoder(encodedMessage, encoder.getTree());
//
//       String decoded = decoder.decode();

       // System.out.println(decoded);
    }
}
