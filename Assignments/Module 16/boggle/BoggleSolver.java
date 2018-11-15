import java.util.Set;
import java.util.TreeSet;
/**
 * Class for boggle solver.
 */
public class BoggleSolver {
    /**
     * Dictionary with the Trie property.
     */
    private TrieST<Integer> dictionarytrie;
    /**
     * Q character to be appended.
     */
    private static final char Q_LETTER = 'Q';
    /**
     * QU string to be appended.
     */
    private static final String QU_STRING = "QU";
    /**
     * magic number eliminating.
     */
    private static final int ELEVEN = 11;
    /**
     * magic number eliminating.
     */
    private static final int EIGHT = 8;
    /**
     * magic number eliminating.
     */
    private static final int FIVE = 5;
    /**
     * magic number eliminating.
     */
    private static final int THREE = 3;
    /**
     *
     * Constructs the object.
     * Initializes the data structure using the given
     * array of strings as the dictionary.
     * (You can assume each word in the dictionary contains
     * only the uppercase letters A through Z.)
     * @param      dictionary  The dictionary
     */
    public BoggleSolver(final String[] dictionary) {
        dictionarytrie = new TrieST<Integer>();
        int[] points = {0, 0, 0, 1, 1, 2, THREE, FIVE, ELEVEN};
        for (String word : dictionary) {
            if (word.length() >= EIGHT) {
                dictionarytrie.put(word, ELEVEN);
            } else {
                dictionarytrie.put(word, points[word.length()]);
            }
        }
    }
    /**
     * Gets all valid words.
     *
     * @param      board  The board
     *
     * @return     All valid words.
     */
    public Iterable<String> getAllValidWords(final BoggleBoard board) {
        if (board == null) {
             throw new java.lang.IllegalArgumentException("board is null");
        }
        Set<String> foundWords = new TreeSet<String>();
        for (int row = 0; row < board.rows(); row++) {
           for (int col = 0; col < board.cols(); col++) {
               String charSequence = addLetter("", board.getLetter(row, col));
               boolean[][] marked = new boolean[board.rows()][board.cols()];
               marked[row][col] = true;
               dfs(foundWords, charSequence, marked, row, col, board);
            }
        }
        return foundWords;
    }

    /**
     * depth first search for searching valid words.
     *
     * @param      foundWords    The found words
     * @param      charSequence  The character sequence
     * @param      marked        The marked
     * @param      startRow      The start row
     * @param      startCol      The start col
     * @param      board         The board
     */
    private void dfs(final Set<String> foundWords, final String charSequence,
        final boolean[][] marked, final int startRow, final int startCol,
        final BoggleBoard board) {
            if (isValidWord(charSequence)) {
                foundWords.add(charSequence);
            }
            for (int row = Math.max(0, startRow - 1); row <= Math.min(
                board.rows() - 1, startRow + 1); row++) {
                for (int col = Math.max(0, startCol - 1); col <= Math.min(
                    board.cols() - 1, startCol + 1); col++) {
                    if (marked[row][col]) {
                        continue;
                    }
                if (!dictionarytrie.hasPrefix(charSequence)) {
                    continue;
                }
            marked[row][col] = true;
            dfs(foundWords, addLetter(charSequence, board.getLetter(
                row, col)), marked, row, col, board);
            marked[row][col] = false;
            }
        }
    }
    /**
     * Adds a letter.
     *
     * @param      to      { parameter_description }
     * @param      letter  The letter
     *
     * @return     { description_of_the_return_value }
     */
    private String addLetter(final String to, final char letter) {
        if (letter == Q_LETTER) {
            return to + QU_STRING;
        } else {
            return to + letter;
        }
    }

    /**
     * Determines if valid word.
     *
     * @param      currentWord  The current word
     *
     * @return     True if valid word, False otherwise.
     */
    private boolean isValidWord(final String currentWord) {
        if (currentWord.length() < (THREE)) {
           return false;
        }
        return dictionarytrie.contains(currentWord);
    }
    /**
     * Calculates the Score for the entered word.
     * Returns the score of the given word if it is in the
     * dictionary, zero otherwise.
     * You can assume the word contains only the
     * uppercase letters A through Z.)
     *
     * @param      word  The word
     *
     * @return     { Score }.
     */
    public int scoreOf(final String word) {
        if (dictionarytrie.contains(word)) {
            return dictionarytrie.get(word);
        } else {
            return 0;
        }

    }
}
