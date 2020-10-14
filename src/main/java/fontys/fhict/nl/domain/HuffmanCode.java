package fontys.fhict.nl.domain;

import java.nio.charset.CharacterCodingException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class HuffmanCode {
//    Comparator<HashMap<Character, Integer>> mapfrequencyComparator = (s1, s2) -> {
////        return s1.getFrequency() - s2.getFrequency();
//        if(s1.get() == s2.getFrequency())
//            return 0;
//        else if (s1.getFrequency() > s2.getFrequency())
//            return -1;
//        else
//            return 1;
//    };

    Comparator<FrequencyDataStructure> frequencyComparator = (s1, s2) -> {
        if (s1.getFrequency() < s2.getFrequency()) {
            return -1;
        } else if(s1.getFrequency() > s2.getFrequency())
            return 1;

            return 0;

    };

    private PriorityQueue<FrequencyDataStructure> priorityQueue = new PriorityQueue<>(frequencyComparator);

    public HuffmanCode() {
    }

    public void insertFrequencyListToPriorityQueue(List<FrequencyDataStructure> frequencyList) {
        for(FrequencyDataStructure frequency : frequencyList) {
            this.priorityQueue.add(frequency);
        }
        //this.priorityQueue.add(new FrequencyDataStructure('u', 5));

        System.out.println(priorityQueue);


//        for (FrequencyDataStructure frequency : this.priorityQueue) {
//            System.out.println(frequency.toString());
//            System.out.flush();
//        }
        System.out.println("Poll: " +this.priorityQueue.peek().toString());

    }
}
