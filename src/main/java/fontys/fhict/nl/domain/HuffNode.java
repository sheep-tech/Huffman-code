package fontys.fhict.nl.domain;

public class HuffNode {
    // this object contains both character and its frequency
    private FrequencyDataStructure characterFrequency;
    private HuffNode leftNode, RightNode;

    public HuffNode(FrequencyDataStructure characterFrequency, HuffNode leftNode, HuffNode rightNode) {
        this.characterFrequency = characterFrequency;
        this.leftNode = leftNode;
        RightNode = rightNode;
    }

    public FrequencyDataStructure getCharacterFrequency() {
        return characterFrequency;
    }

    public void setCharacterFrequency(FrequencyDataStructure characterFrequency) {
        this.characterFrequency = characterFrequency;
    }

    public HuffNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(HuffNode leftNode) {
        this.leftNode = leftNode;
    }

    public HuffNode getRightNode() {
        return RightNode;
    }

    public void setRightNode(HuffNode rightNode) {
        RightNode = rightNode;
    }
}
