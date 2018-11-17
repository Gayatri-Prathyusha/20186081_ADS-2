import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
/**
 * Class for solution.
 */
public class Solution {

    /**
     * Main function;
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();

        switch (cases) {
        case "loadDictionary":
            // input000.txt and output000.txt
            BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
            while (scan.hasNextLine()) {
                String key = scan.nextLine();
                System.out.println(hash.get(key));
            }
            break;

        case "getAllPrefixes":
            // input001.txt and output001.txt
            T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
            while (scan.hasNextLine()) {
                String prefix = scan.nextLine();
                for (String each : t9.getAllWords(prefix)) {
                    System.out.println(each);
                }
            }
            break;

        case "potentialWords":
            // input002.txt and output002.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            int count = 0;
            while (scan.hasNextLine()) {
                String t9Signature = scan.nextLine();
                for (String each : t9.potentialWords(t9Signature)) {
                    count++;
                    System.out.println(each);
                }
            }
            if (count == 0) {
                System.out.println("No valid words found.");
            }
            break;

        case "topK":
            // input003.txt and output003.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            Bag<String> bag = new Bag<String>();
            int k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                bag.add(line);
            }
            for (String each : t9.getSuggestions(bag, k)) {
                System.out.println(each);
            }

            break;

        case "t9Signature":
            // input004.txt and output004.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            bag = new Bag<String>();
            k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (String each : t9.t9(line, k)) {
                    System.out.println(each);
                }
            }
            break;

        default:
            break;

        }
    }

    /**
     * to read the file.
     *
     * @param      file  The file
     *
     * @return     { Strings of the read file. }
     */
    public static String[] toReadFile(final String file) {
        In in = new In(file);
        return in.readAllStrings();
    }

    /**
     * Loads a dictionary.
     *
     * @param      file  The file
     *
     * @return     { st. }
     */
    public static BinarySearchST<String, Integer> loadDictionary(final String file) {
            BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
            String[] message = toReadFile(file);
            for (String word : message) {
                word = word.toLowerCase();
                if (st.contains(word)) {
                    st.put(word, st.get(word) + 1);
                } else {
                    st.put(word, 1);
                }
            }
            return st;
        }

    }


/**
 * Class for T9.
 */
class T9 {
    /**
     * TST for the words.
     */
    private TST<Integer> words;
    /**
     * Constructs the object.
     *
     * @param      st    symbol table.
     */
    public T9(BinarySearchST<String, Integer> st) {
        words = new TST();
        for (String word : st.keys()) {
            words.put(word, st.get(word));
        }
    }


    /**
     * Gets all words.
     * get all the prefixes that match with given prefix.
     *
     * @param      prefix  The prefix
     *
     * @return     All words.
     */
    public Iterable<String> getAllWords(final String prefix) {
        return words.keysWithPrefix(prefix);
    }
    /**
     * Potential Words.
     *
     * @param      t9Signature  The t9 signature
     *
     * @return     { String Iterable. }
     */
    public Iterable<String> potentialWords(final String t9Signature) {
    // your code goes here
        ArrayList<String> list = new ArrayList<>();
        for (String each : words.keys()) {
            String[] stringarray = each.split("");
            String number = "";
            for (String ch : stringarray) {
                if (ch.equals("a") || ch.equals("b") || ch.equals("c")) {
                    number = number + "2";
                }
                if (ch.equals("d") || ch.equals("e") || ch.equals("f")) {
                    number = number + "3";
                }
                if (ch.equals("g") || ch.equals("h") || ch.equals("i")) {
                    number = number + "4";
                }
                if (ch.equals("j") || ch.equals("k") || ch.equals("l")) {
                    number = number + "5";
                }
                if (ch.equals("m") || ch.equals("n") || ch.equals("o")) {
                    number = number + "6";
                }
                if (ch.equals("p") || ch.equals("q") || ch.equals("r")
                        || ch.equals("s")) {
                    number = number + "7";
                }
                if (ch.equals("t") || ch.equals("u") || ch.equals("v")) {
                    number = number + "8";
                }
                if (ch.equals("w") || ch.equals("x") || ch.equals("y")
                        || ch.equals("z")) {
                    number = number + "9";
                }
            }
            if (number.equals(t9Signature)) {
                list.add(each);
            }
        }
        return list;
    }

    /**
     * Gets the suggestions.
     * return all possibilities(words), find top k with highest frequency.
     *
     * @param      words1  The words 1
     * @param      k       { parameter_description }
     *
     * @return     The suggestions.
     */
    public Iterable<String> getSuggestions(final Iterable<String> words1, final int k) {
        HashMap<Integer, String> wordstable = new HashMap<>();
        for (String w : words1) {
            for (String sw : getAllWords(w)) {
                int temp = words.get(sw);
                if (wordstable.containsKey(temp)) {
                    continue;
                } else {
                    wordstable.put(temp, sw);
                }
            }
        }
        Object[] keys = wordstable.keySet().toArray();
        Arrays.sort(keys);
        ArrayList<String> arraylist = new ArrayList<>();
        for (int i = keys.length - 1; i > keys.length - 1 - k; i--) {
            arraylist.add(wordstable.get(keys[i]));
        }
        Collections.sort(arraylist);
        return arraylist;
    }
    /**
     * T9.
     *
     * @param      t9Signature  The t9 signature.
     * @param      k            { parameter_description }
     *
     * @return     { String Iterable }.
     */
    public Iterable<String> t9(final String t9Signature, final int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}
