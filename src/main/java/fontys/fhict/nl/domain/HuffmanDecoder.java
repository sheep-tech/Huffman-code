package fontys.fhict.nl.domain;

import fontys.fhict.nl.datastructure.HuffmanLeaf;
import fontys.fhict.nl.datastructure.HuffmanNode;
import fontys.fhict.nl.datastructure.HuffmanTree;

public class HuffmanDecoder {
    private String encodedMessage;
    private String decodedMessage = new String();
    private int index = 0;

    public HuffmanDecoder() {}

    public String decode(String encodedMessage, HuffmanTree tree) {
        this.encodedMessage = encodedMessage;
        for (index = 0; index < this.encodedMessage.length(); index++) {
            getCharFromTree(tree);
        }

        return this.decodedMessage;
    }

    private void getCharFromTree(HuffmanTree tree) {
        if(index >= this.encodedMessage.length()) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;

            this.decodedMessage += leaf.getCharacter();

            return;
        }

        char bit = this.encodedMessage.charAt(this.index);

        if(tree.getCharacter() != '\u0000') {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;

            this.decodedMessage += leaf.getCharacter();
            index--;
            return;
        }

        else {
            HuffmanNode node = (HuffmanNode)tree;
            this.index++;

            if (bit == '1') {
                getCharFromTree(node.getRightNode());
            } else {
                getCharFromTree(node.getLeftNode());
            }
        }
    }
}
