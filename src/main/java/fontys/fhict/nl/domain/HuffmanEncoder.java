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

    public HuffmanTree buildTree(List<FrequencyDataStructure> frequencyList) {
        // Set the priority queue
        for(FrequencyDataStructure frequency : frequencyList) {
            this.priorityQueue.add(new HuffmanLeaf(frequency.getFrequency(), frequency.getCharacter()));
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
        this.treeTraversal(tree, this.encodingBit);
        return this.encodingMap;
    }

    public void treeTraversal(HuffmanTree tree, CustomBitSet encodingBit) {
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;

            CustomBitSet bitcode = new CustomBitSet();
            bitcode.setData(this.encodingBit.getData());
            bitcode.setDataPosition(this.encodingBit.getDataPosition());

            this.encodingMap.put(leaf.getCharacter(), bitcode);

        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;

            // go to left
            this.encodingBit.set(index++, false);
            treeTraversal(node.getLeftNode(), encodingBit);
            this.encodingBit.unsetLastBit(--index);

            // go to right
            this.encodingBit.set(index++, true);
            treeTraversal(node.getRightNode(), encodingBit);
            this.encodingBit.unsetLastBit(--index);
        }
    }


    public String encode(String message) {
        String encodedMessage = "";
        TextFrequencyAnalizer analizer = new TextFrequencyAnalizer();

        // analyze char frequency
        List<FrequencyDataStructure> frequencyList = analizer.calculateFrequency(message);
        // build huffman tree
        this.tree = this.buildTree(frequencyList);
        // generate coding map
        this.generateEncodingMap(this.tree);
        // encode char based on huffmanEncodingMap
        int lastBitPosition = 0;
        // encoding
        for (int i = 0; i < message.length(); i++) {
            CustomBitSet code = this.encodingMap.get(message.charAt(i));
            // right shift data in a mask
            //BitSet maskedCode = this.maskedBitSet(code.getData(),lastBitPosition, code.getDataPosition().cardinality());
            encodedMessage += parseBitSet(code.getData(), code.getDataPosition().cardinality());
        }

//        return fromString(encodedMessage);
        return encodedMessage;
    }

    public String parseBitSet(BitSet code, int length) {
        String parsed = "";
        for (int i = 0; i < length; i++) {
            if (code.get(i))
                parsed += "1";
            else
                parsed += "0";
        }
        return parsed;
    }

    private static BitSet fromString(String binary) {
        BitSet bitset = new BitSet(binary.length());
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                bitset.set(i);
            }
        }
        return bitset;
    }

    private BitSet maskedBitSet(BitSet b2, int b1Length, int b2Length) {
        BitSet newBitSet = new BitSet();
        for (int i = 0; i < b1Length+b2Length; i++) {
            if (i < b1Length)
                newBitSet.set(i, false);
            else
                newBitSet.set(i, b2.get(i));
        }

        return newBitSet;
    }

    public HuffmanTree getTree() {
        return this.tree;
    }

    public Map<Character, CustomBitSet> getEncodingMap() {
        return encodingMap;
    }
}
