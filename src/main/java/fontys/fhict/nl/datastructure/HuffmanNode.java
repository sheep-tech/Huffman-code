package fontys.fhict.nl.datastructure;

public class HuffmanNode extends HuffmanTree {
    // this object contains both character and its frequency
    private HuffmanTree leftNode, rightNode;

    public HuffmanNode(HuffmanTree l, HuffmanTree r) {
        super(l.getFrequency() + r.getFrequency());
        leftNode = l;
        rightNode = r;
    }

    @Override
    public String toString() {
        return  "node , frequency: " + frequency;
    }

    public HuffmanTree getLeftNode() {
        return leftNode;
    }
    public HuffmanTree getRightNode() {
        return rightNode;
    }
}
