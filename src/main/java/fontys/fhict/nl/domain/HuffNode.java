package fontys.fhict.nl.domain;

public class HuffNode {
    // this object contains both character and its frequency
    private FrequencyDataStructure characterFrequency;
    private HuffNode leftNode, rightNode;

    public HuffNode() {
        this.characterFrequency = new FrequencyDataStructure();
    }

    @Override
    public String toString() {
        String text = "";
        if(characterFrequency.getCharacter() == '\u0000')
            text += "node";
        else
            text += "char: '" + characterFrequency.getCharacter() + "'";

        text += " , frequency: " + characterFrequency.getFrequency();
        return  text +
                /*"\nleftNode: " + leftNode.characterFrequency.getCharacter() + ", " + leftNode.characterFrequency.getFrequency() +
                "\nrightNode=" + rightNode.characterFrequency.getCharacter() + ", " + rightNode.characterFrequency.getFrequency() + */"\n";
    }

    public HuffNode(FrequencyDataStructure characterFrequency) {
        this.characterFrequency = characterFrequency;
    }

    public HuffNode(HuffNode leftNode, HuffNode rightNode) {
        this.characterFrequency = new FrequencyDataStructure();
        this.leftNode = leftNode;
        this.rightNode = rightNode;
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
        return rightNode;
    }

    public void setRightNode(HuffNode rightNode) {
        this.rightNode = rightNode;
    }
}
