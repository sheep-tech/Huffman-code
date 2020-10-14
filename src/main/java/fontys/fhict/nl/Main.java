package fontys.fhict.nl;

import fontys.fhict.nl.domain.FrequencyDataStructure;
import fontys.fhict.nl.domain.HuffmanCode;
import fontys.fhict.nl.domain.TextFrequencyAnalizer;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String text = "Eerie eyes seen near lake.";

        TextFrequencyAnalizer analizer = new TextFrequencyAnalizer(text);
        List<FrequencyDataStructure> frequencyList = analizer.calculateFrequency();
//int i=0;
//        for (FrequencyDataStructure name: frequencyList){
//            System.out.println(i++ + " " + name.getCharacter() + ": " + name.getFrequency());
//        }
        System.out.println();
        HuffmanCode encoder = new HuffmanCode();

        encoder.insertFrequencyListToPriorityQueue(frequencyList);
    }
}
