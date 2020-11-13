package fontys.fhict.nl.domain;

import java.util.*;

public class TextFrequencyAnalyzer {
    private String text;

    public TextFrequencyAnalyzer() {
        this.text = "";
    }

    public Map<Character, Integer> calculateFrequency(String text) {
        // map each word with its frequency.
        HashMap<Character, Integer> frequency = new HashMap<>();

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            if(frequency.containsKey(character)){
                frequency.put(character, frequency.get(character) + 1);
            }
            else
                frequency.put(character, 1);
        }

        return frequency;
    }

}
