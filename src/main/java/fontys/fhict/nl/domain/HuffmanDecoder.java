package fontys.fhict.nl.domain;

import fontys.fhict.nl.datastructure.CustomBitSet;
import fontys.fhict.nl.datastructure.HuffmanLeaf;
import fontys.fhict.nl.datastructure.HuffmanNode;
import fontys.fhict.nl.datastructure.HuffmanTree;

public class HuffmanDecoder {
    private CustomBitSet encodedMessage;
    private String decodedMessage = "";
    private int index = 0;

    public HuffmanDecoder() {}

    public String decode(CustomBitSet encodedMessage, HuffmanTree tree) {
        this.encodedMessage = encodedMessage;
        for (index = 0; index < this.encodedMessage.getDataPosition().cardinality(); index++) {
            getCharFromTree(tree);
        }

        return this.decodedMessage;
    }

    private void getCharFromTree(HuffmanTree tree) {
        if(index >= this.encodedMessage.getDataPosition().cardinality()) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;

            this.decodedMessage += leaf.getCharacter();

            return;
        }

        boolean bit = this.encodedMessage.getData().get(this.index);

        if(tree.getCharacter() != '\u0000') {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;

            this.decodedMessage += leaf.getCharacter();
            index--;
            return;
        }

        else {
            HuffmanNode node = (HuffmanNode)tree;
            this.index++;

            if (bit) {
                // go right
                getCharFromTree(node.getRightNode());
            } else {
                // go left
                getCharFromTree(node.getLeftNode());
            }
        }
    }
}
