package fontys.fhict.nl.domain;

import fontys.fhict.nl.datastructure.FrequencyDataStructure;
import fontys.fhict.nl.datastructure.HuffmanTree;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HuffmanEncoderComplexityTest {
    ArrayList<String> loadDictionary() throws IOException {
        ArrayList<String> words = new ArrayList<>();

        File f1=new File("Dictionary.txt"); //Creation of File Descriptor for input file
        FileReader fr = null;  //Creation of File Reader object

        try {
            fr = new FileReader(f1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(fr); //Creation of BufferedReader object
        String line = "";

        while((line = br.readLine()) != null)
            words.add(line);

        return words;
    }

    void printEfficiency(int encodedHuffmanLength, int sourceTextLength) {
        float efficiency = ((float)encodedHuffmanLength / (sourceTextLength*8))*100;
        System.out.println("Efficiency: " + (int)efficiency + "%");
        System.out.flush();
    }

    @Test
    void performanceTestOn10KWords() {
        Random rand = new Random();

        ArrayList<String> dictionary = null;
        try {
            dictionary = this.loadDictionary();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int iter = 10000;
        String longText = new String();
        HuffmanEncoder encoder = new HuffmanEncoder();

        for (int i = 0; i < iter; i++) {

            longText += dictionary.get(rand.nextInt(69903));
        }

        long start = System.currentTimeMillis();

        String encodedMessage = encoder.encode(longText);

        long end = System.currentTimeMillis();
        long duration = (end - start);

        System.out.println("Time execution: " + duration + "ms");
        System.out.flush();

        printEfficiency(encodedMessage.length(), longText.length());
    }

    @Test
    void performanceTestOn1MWords() {
        Random rand = new Random();

        ArrayList<String> dictionary = null;
        try {
            dictionary = this.loadDictionary();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int iter = 1000000;
        String longText = new String();
        HuffmanEncoder encoder = new HuffmanEncoder();

        for (int i = 0; i < iter; i++) {

            longText += dictionary.get(rand.nextInt(69903));
        }

        long start = System.currentTimeMillis();

        String encodedMessage = encoder.encode(longText);

        long end = System.currentTimeMillis();
        long duration = (end - start);

        System.out.println("Time execution: " + duration + "ms");
        System.out.flush();

        printEfficiency(encodedMessage.length(), longText.length());
    }

    @Test
    void encodingTestWith100KbFile() throws IOException {
        ArrayList<Integer> unique = new ArrayList<Integer>();
        File f1=new File("100kB_data.txt"); //Creation of File Descriptor for input file
        FileReader fr = new FileReader(f1);  //Creation of File Reader object
        BufferedReader br = new BufferedReader(fr); //Creation of BufferedReader object

        HuffmanEncoder encoder = new HuffmanEncoder();
        int encodedLength = 0;
        int byteLength = 0;

        String encodedMessage, s, text = "";
        while ((s= br.readLine())!= null) {
            text += s;

            byteLength += s.length()*8;
        }
        encodedMessage = encoder.encode(text);
        encodedLength += encodedMessage.length();
        printEfficiency(encodedLength, byteLength);
    }

    @Test
    void decodingPerformanceTest() throws IOException {
        Random rand = new Random();

        ArrayList<String> dictionary = null;
        try {
            dictionary = this.loadDictionary();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int iter = 10;
        String longText = new String();
        HuffmanEncoder encoder = new HuffmanEncoder();
        HuffmanDecoder decoder = new HuffmanDecoder();

        for (int i = 0; i < iter; i++) {

            longText += dictionary.get(rand.nextInt(69903));
        }

        System.out.println("encoding done.");
        String encodedMessage = encoder.encode(longText);
        HuffmanTree tree= encoder.getTree();

        long start = System.currentTimeMillis();

        decoder.decode(encodedMessage, tree);

        long end = System.currentTimeMillis();
        long duration = (end - start);

        System.out.println("Time execution: " + duration + "ms");
        System.out.flush();
    }

    @Test
    void decodingPerformanceTestWith100KbFile() throws IOException {
        ArrayList<Integer> unique = new ArrayList<Integer>();
        File f1=new File("100kB_data.txt"); //Creation of File Descriptor for input file
        FileReader fr = new FileReader(f1);  //Creation of File Reader object
        BufferedReader br = new BufferedReader(fr); //Creation of BufferedReader object

        HuffmanEncoder encoder = new HuffmanEncoder();


        String encodedMessage, s, text = "";
        while ((s= br.readLine())!= null) {
            text += s;

        }
        encodedMessage = encoder.encode(text);
        HuffmanDecoder decoder = new HuffmanDecoder();
        HuffmanTree tree= encoder.getTree();

        long start = System.currentTimeMillis();

        String decoded = decoder.decode(encodedMessage, tree);

        long end = System.currentTimeMillis();
        long duration = (end - start);

        System.out.println("Time execution: " + duration + "ms");
        System.out.flush();
    }

    @Test
    void textFrequencyAnalizerPerformanceTest() {
        Random rand = new Random();

        ArrayList<String> dictionary = null;
        try {
            dictionary = this.loadDictionary();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int iter = 50000;
        String longText = new String();
        TextFrequencyAnalizer analizer = new TextFrequencyAnalizer();

        for (int i = 0; i < iter; i++) {

            longText += dictionary.get(rand.nextInt(69903));
        }
        System.out.println();
        long start = System.currentTimeMillis();

        analizer.calculateFrequency(longText);

        long end = System.currentTimeMillis();
        long duration = (end - start);

        System.out.println("Time execution: " + duration + "ms");
        System.out.flush();
    }

    @Test //using a map instead
    void textFrequencyAnalizerMapPerformanceTest() {
        Random rand = new Random();

        ArrayList<String> dictionary = null;
        try {
            dictionary = this.loadDictionary();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int iter = 50000;
        String longText = new String();
        TextFrequencyAnalizer analizer = new TextFrequencyAnalizer();

        for (int i = 0; i < iter; i++) {

            longText += dictionary.get(rand.nextInt(69903));
        }
        System.out.println();
        long start = System.currentTimeMillis();

        analizer.calculateFrequencyMap(longText);

        long end = System.currentTimeMillis();
        long duration = (end - start);

        System.out.println("Time execution: " + duration + "ms");
        System.out.flush();
    }

    @Test
    void buildTreePerformanceTest() {
        Random rand = new Random();

        ArrayList<String> dictionary = null;
        try {
            dictionary = this.loadDictionary();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int iter = 100000;
        String longText = new String();
        TextFrequencyAnalizer analyzer = new TextFrequencyAnalizer();
        HuffmanEncoder encoder = new HuffmanEncoder();
        List<FrequencyDataStructure> frequencyList = new ArrayList<>();

        for (int i = 0; i < iter; i++) {

            longText = dictionary.get(rand.nextInt(69903));
            frequencyList = analyzer.calculateFrequency(longText);
        }
        long start = System.currentTimeMillis();

        encoder.buildTree(frequencyList);

        long end = System.currentTimeMillis();
        long duration = (end - start);

        System.out.println("Time execution: " + duration + "ms");
        System.out.flush();
    }
}
