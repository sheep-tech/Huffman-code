package fontys.fhict.nl.domain;

import java.nio.charset.CharacterCodingException;
import java.util.*;

public class HuffmanCode {
    Comparator<HuffNode> frequencyComparator = (s1, s2) -> {
        if (s1.getCharacterFrequency().getFrequency() < s2.getCharacterFrequency().getFrequency()) {
            return -1;
        } else if(s1.getCharacterFrequency().getFrequency() > s2.getCharacterFrequency().getFrequency())
            return 1;

        return 0;
    };

    private Map<Character, BitSet> huffmanEncodingMap;
    private HuffNode huffmanRootTree;
    private PriorityQueue<HuffNode> priorityQueue;

    private BitSet encodingBit = new BitSet();
    private int index = 0;

    public HuffmanCode() {
        this.huffmanEncodingMap = new HashMap<>();
        this.priorityQueue = new PriorityQueue<>(frequencyComparator);
    }

    public void buildHuffmanCodeTree(List<FrequencyDataStructure> frequencyList) {
        // Set the priority tree
        this.insertFrequencyListToPriorityQueue(frequencyList);

        while(this.priorityQueue.size() >= 2) {
            HuffNode node = new HuffNode(this.priorityQueue.poll(), this.priorityQueue.poll());

            int totalFrequency = node.getLeftNode().getCharacterFrequency().getFrequency() + node.getRightNode().getCharacterFrequency().getFrequency();

            node.setCharacterFrequency(new FrequencyDataStructure(totalFrequency));

            this.priorityQueue.add(node);
        }

        this.huffmanRootTree = this.priorityQueue.poll();

        System.out.println(priorityQueue.size());
        System.out.println(huffmanRootTree.toString());
    }

    public Map<Character, BitSet> encode() {
        huffmanCodeTreeTraversal(this.huffmanRootTree);

        return this.huffmanEncodingMap;
    }

    private void huffmanCodeTreeTraversal(HuffNode node) {
//        if(node == null) {
//            --index;
//            return;
//        }

        if(node.getCharacterFrequency().getCharacter() != '\u0000') {
            BitSet bitcode = this.encodingBit.get(0, index);
            this.huffmanEncodingMap.put(node.getCharacterFrequency().getCharacter(), bitcode);
            this.encodingBit.set(--index, false);

            return ;
        }

        // going to left child
        this.encodingBit.set(index++, false); // 0 for left side
        huffmanCodeTreeTraversal(node.getLeftNode());

        // going to right child
        this.encodingBit.set(index++, true); // 1 for right side
        huffmanCodeTreeTraversal(node.getRightNode());

        // return back parent node
        if(index > 0)
            this.encodingBit.set(--index, false);
    }

    public void insertFrequencyListToPriorityQueue(List<FrequencyDataStructure> frequencyList) {
        for(FrequencyDataStructure frequency : frequencyList) {
            this.priorityQueue.add(new HuffNode(frequency));
        }
        while(!this.priorityQueue.isEmpty())
            System.out.println(this.priorityQueue.poll().toString());

    }
}
