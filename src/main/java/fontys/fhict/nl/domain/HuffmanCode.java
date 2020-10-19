//package fontys.fhict.nl.domain;
//
//import fontys.fhict.nl.datastructure.CustomBitSet;
//import fontys.fhict.nl.datastructure.FrequencyDataStructure;
//import fontys.fhict.nl.datastructure.HuffmanNode;
//
//import java.util.*;
//
//public class HuffmanCode {
//    Comparator<HuffmanNode> frequencyComparator = (s1, s2) -> {
//        if (s1.getCharacterFrequency().getFrequency() < s2.getCharacterFrequency().getFrequency()) {
//            return -1;
//        } else if(s1.getCharacterFrequency().getFrequency() > s2.getCharacterFrequency().getFrequency())
//            return 1;
//
//        return 0;
//    };
//
//    private Map<Character, CustomBitSet> huffmanEncodingMap;
//
//    private HuffmanNode huffmanRootTree;
//
//    private PriorityQueue<HuffmanNode> priorityQueue;
//    private CustomBitSet encodingBit = new CustomBitSet();
//
//    private int index = 0;
//    public HuffmanCode() {
//        this.huffmanEncodingMap = new HashMap<>();
//        this.priorityQueue = new PriorityQueue<>(frequencyComparator);
//    }
//
//    public Map<Character, CustomBitSet> genrateHuffmanCodeMap(String text) {
//        TextFrequencyAnalizer analizer = new TextFrequencyAnalizer(text);
//        List<FrequencyDataStructure> frequencyList = analizer.calculateFrequency();
//
//        this.buildHuffmanCodeTree(frequencyList);
//
//        this.huffmanCodeTreeTraversal(this.huffmanRootTree);
//
//        return this.huffmanEncodingMap;
//    }
//
//    public void buildHuffmanCodeTree(List<FrequencyDataStructure> frequencyList) {
//        // Set the priority tree
//        this.insertFrequencyListToPriorityQueue(frequencyList);
//
//        while(this.priorityQueue.size() >= 2) {
//            HuffmanNode node = new HuffmanNode(this.priorityQueue.poll(), this.priorityQueue.poll());
//
//            int totalFrequency = node.getLeftNode().getCharacterFrequency().getFrequency() + node.getRightNode().getCharacterFrequency().getFrequency();
//
//            node.setCharacterFrequency(new FrequencyDataStructure(totalFrequency));
//
//            this.priorityQueue.add(node);
//        }
//
//        this.huffmanRootTree = this.priorityQueue.poll();
//    }
//
//    private void huffmanCodeTreeTraversal(HuffmanNode node) {
//        if(node.getCharacterFrequency().getCharacter() != '\u0000') {
//            CustomBitSet bitcode = new CustomBitSet();
//            bitcode.setData(this.encodingBit.getData());
//            bitcode.setDataPosition(this.encodingBit.getDataPosition());
//
//            this.huffmanEncodingMap.put(node.getCharacterFrequency().getCharacter(), bitcode);
//            this.encodingBit.unsetLastBit(--index);
//
//            return ;
//        }
//
//        // going to left child
//        this.encodingBit.set(index++, false); // 0 for left side
//        huffmanCodeTreeTraversal(node.getLeftNode());
//
//        // going to right child
//        this.encodingBit.set(index++, true); // 1 for right side
//        huffmanCodeTreeTraversal(node.getRightNode());
//
//        // return back parent node
//        if(index > 0)
//            this.encodingBit.unsetLastBit(--index);
//    }
//
//    public void insertFrequencyListToPriorityQueue(List<FrequencyDataStructure> frequencyList) {
//        for(FrequencyDataStructure frequency : frequencyList) {
//            this.priorityQueue.add(new HuffmanNode(frequency));
//        }
//
////        while(!this.priorityQueue.isEmpty())
////            System.out.println(this.priorityQueue.poll().toString());
//
//    }
//
//    public HuffmanNode getHuffmanRootTree() {
//        return huffmanRootTree;
//    }
//}
