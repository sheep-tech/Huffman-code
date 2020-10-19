package fontys.fhict.nl.domain;

import fontys.fhict.nl.datastructure.FrequencyDataStructure;

import java.util.*;

public class TextFrequencyAnalizer {
    private String text;
    private List<FrequencyDataStructure> frequency;

    public TextFrequencyAnalizer() {
        this.text = new String();
        this.frequency = new ArrayList<>();
    }

    public TextFrequencyAnalizer(String text) {
        this.text = text;
        this.frequency = new ArrayList<>();
    }

    public List<FrequencyDataStructure> calculateFrequency(String text) {
        this.text = text;
        int frequencyCharPostion = 0;
        Map<Character, Integer> charPosition = new HashMap<>();

        for (int i = 0; i < this.text.length(); i++) {
            char currChar = this.text.charAt(i);
            if(charPosition.containsKey(this.text.charAt(i))) {
                // update current frequency number value
                this.frequency.get(charPosition.get(currChar)).increaseFrequency();
            }
            else {
                this.frequency.add(new FrequencyDataStructure(currChar));

                // store the index where a char is in the frequency array
                charPosition.put(currChar, frequencyCharPostion++);
            }
        }

        //Collections.sort(this.frequency);

        return this.frequency;
    }

    // this function is slower than the above one
    public Map<Character, Integer> calculateFrequencyMap(String text) {
        this.text = text;
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (int i = 0; i < this.text.length(); i++) {
            char currenctCharacter = this.text.charAt(i);

            if(frequencyMap.containsKey(currenctCharacter)) {
                // update current frequency number value
                frequencyMap.put(currenctCharacter, ((frequencyMap.get(currenctCharacter)+1)));
            }
            else {
                frequencyMap.put(currenctCharacter, 1);
            }
        }

        return frequencyMap;
    }

    public String getText() {
        return text;
    }
}
