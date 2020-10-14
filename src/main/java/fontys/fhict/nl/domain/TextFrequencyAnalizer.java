package fontys.fhict.nl.domain;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

public class TextFrequencyAnalizer {
    private String text;
    private List<FrequencyDataStructure> frequency;

    public TextFrequencyAnalizer(String text) {
        this.text = text;
        this.frequency = new ArrayList<>();
    }

    public List<FrequencyDataStructure> calculateFrequency() {
        Map<Character, Integer> charPosition = new HashMap<>();
        int frequencyCharPostion = 0;
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

        Collections.sort(this.frequency);

        return this.frequency;
    }

    public String getText() {
        return text;
    }
}
