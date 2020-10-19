package fontys.fhict.nl.datastructure;

public abstract class HuffmanTree implements Comparable<HuffmanTree> {
    protected final int frequency;
    protected final char character;

    public HuffmanTree(int frequency, char character) {
        this.frequency = frequency;
        this.character = character;
    }

    public HuffmanTree(int frequency) {
        this.frequency = frequency;
        this.character = '\u0000';
    }

    @Override
    public int compareTo(HuffmanTree tree) {
        return this.frequency - tree.frequency;
    }

    public int getFrequency() {
        return frequency;
    }

    public char getCharacter() {
        return character;
    }
    /*
    @Override
    public int compareTo(HuffmanNode o) {
        if (this.characterFrequency.getFrequency() < o.characterFrequency.getFrequency())
            return -1;
        else if(this.characterFrequency.getFrequency() > o.characterFrequency.getFrequency())
            return 1;
        else
            return 0;
    }
    * **/
}

