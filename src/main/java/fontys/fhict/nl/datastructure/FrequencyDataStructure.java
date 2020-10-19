package fontys.fhict.nl.datastructure;

public class FrequencyDataStructure implements Comparable<FrequencyDataStructure> {
    private char character;
    private int frequency;

    public FrequencyDataStructure() {}

    public FrequencyDataStructure(char character) {
        this.character = character;
        this.frequency = 1;
    }

    public FrequencyDataStructure(int frequency) {
        this.frequency = frequency;
        this.character = '\u0000'; // set default null value
    }

//    private FrequencyDataStructure(char character, int frequency) {
//        this.character = character;
//        this.frequency = frequency;
//    }

    public void increaseFrequency() {
        ++this.frequency;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public char getCharacter() {
        return character;
    }

        @Override
        public int compareTo(FrequencyDataStructure o) {
            if (this.getFrequency() < o.getFrequency())
                return -1;
            else if(this.getFrequency() >o.getFrequency())
                return 1;
            else
                return 0;
        }

    @Override
    public String toString() {
        return  character + ": " + frequency;
    }
}
