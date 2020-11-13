package fontys.fhict.nl.domain;

import fontys.fhict.nl.datastructure.*;

import java.util.*;

public class HuffmanEncoder {
    private PriorityQueue<HuffmanTree> priorityQueue;
    private CustomBitSet encodingBit;
    private HuffmanTree tree;
    private Map<Character, CustomBitSet> encodingMap;

    private int index = 0; // used for tree traversal

    public HuffmanEncoder() {
        priorityQueue = new PriorityQueue<>();
        encodingBit = new CustomBitSet();
        encodingMap = new HashMap<>();
    }

    public CustomBitSet encode(String message) {
        TextFrequencyAnalyzer analyzer = new TextFrequencyAnalyzer();

        // analyze char frequency
        Map<Character, Integer> frequencyList = analyzer.calculateFrequency(message);
        // build huffman tree
        this.tree = this.buildTree(frequencyList);
        // generate coding map
        this.generateEncodingMap(this.tree);
        // encode char based on huffmanEncodingMap
        int[] lastBitPosition = new int[1];
        CustomBitSet codedMessage = new CustomBitSet();

        // encoding
        for (int i = 0; i < message.length(); i++) {
            CustomBitSet code = this.encodingMap.get(message.charAt(i));
            // right shift data in a mask
            CustomBitSet maskedCode = this.maskedBitSet(code, lastBitPosition);
            codedMessage.or(maskedCode);
            //encodedMessage += parseBitSet(code.getData(), code.getDataPosition().cardinality());
        }

//        return fromString(encodedMessage);
        return codedMessage;
    }

    public HuffmanTree buildTree(Map<Character, Integer> frequencyList) {
        // Set the priority queue
        for(Map.Entry<Character, Integer> frequency : frequencyList.entrySet()) {
            this.priorityQueue.add(new HuffmanLeaf(frequency.getValue(), frequency.getKey()));
        }

//        while(!this.priorityQueue.isEmpty())
//            System.out.println(this.priorityQueue.poll().toString());

        while(this.priorityQueue.size() >= 2) {
            HuffmanNode node = new HuffmanNode(this.priorityQueue.poll(), this.priorityQueue.poll());

            this.priorityQueue.add(node);
        }

        return this.priorityQueue.poll();
    }

    public Map<Character, CustomBitSet> generateEncodingMap(HuffmanTree tree) {
        this.treeTraversal(tree);
        return this.encodingMap;
    }

    public void treeTraversal(HuffmanTree tree) {
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;

            CustomBitSet bitCode = new CustomBitSet();
            bitCode.setData(this.encodingBit.getData());
            bitCode.setDataPosition(this.encodingBit.getDataPosition());

            this.encodingMap.put(leaf.getCharacter(), bitCode);

        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;

            // go to left
            this.encodingBit.set(index++, false);
            treeTraversal(node.getLeftNode());
            this.encodingBit.unsetLastBit(--index);

            // go to right
            this.encodingBit.set(index++, true);
            treeTraversal(node.getRightNode());
            this.encodingBit.unsetLastBit(--index);
        }
    }

    public CustomBitSet maskedBitSet(CustomBitSet b2, int[] b1Length) {
        CustomBitSet newBitSet = new CustomBitSet();
        int b2Length = b2.getDataPosition().cardinality();

        for (int i = 0; i < b1Length[0]+b2Length; i++) {
            if (i < b1Length[0])
                newBitSet.set(i, false);
            else
                newBitSet.set(i, b2.getData().get(i - b1Length[0]));
        }
        b1Length[0] += b2Length;

        return newBitSet;
    }

    public HuffmanTree getTree() {
        return this.tree;
    }

    public Map<Character, CustomBitSet> getEncodingMap() {
        return encodingMap;
    }
}
