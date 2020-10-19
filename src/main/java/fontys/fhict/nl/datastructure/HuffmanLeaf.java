package fontys.fhict.nl.datastructure;

public class HuffmanLeaf extends HuffmanTree{

    public HuffmanLeaf(int frequency, char character) {
        super(frequency, character);
    }

    @Override
    public String toString() {
        String text = "char: '" + character + "'";

        text += " , frequency: " + this.frequency;
        return  text;
    }
}
