import java.util.Set;
import java.util.TreeSet;


public class BoggleSolver {
	private TrieST<Integer> dictionarytrie;
	private static final char Q_LETTER = 'Q';
    private static final String QU_STRING = "QU";

	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	public BoggleSolver(String[] dictionary) {
		dictionarytrie = new TrieST<Integer>();
		int[] points = {0, 0, 0, 1, 1, 2, 3, 5, 11};
		for (String word : dictionary) {
			if (word.length() >= 8) {
				dictionarytrie.put(word, 11);
			} else {
				dictionarytrie.put(word, points[word.length()]);
			}


		}

	}

   // Returns the set of all valid words in the given Boggle board, as an Iterable.
   public Iterable<String> getAllValidWords(BoggleBoard board) {
      if (board == null) 
         throw new java.lang.IllegalArgumentException("board is null");
      Set<String> foundWords = new TreeSet<String>();
      for (int row = 0; row < board.rows(); row++) {
         for (int col = 0; col < board.cols(); col++) {
            String charSequence = addLetter("", board.getLetter(row, col));
            boolean marked[][] = new boolean[board.rows()][board.cols()];
            marked[row][col] = true;
            dfs(foundWords, charSequence, marked, row, col, board);
         }
      }
      return foundWords;
   }

   // depth first search for searching valid words
   private void dfs(Set<String> foundWords, String charSequence, boolean[][] marked,
         int startRow, int startCol, BoggleBoard board) {
      if (isValidWord(charSequence) ) foundWords.add(charSequence);
      for (int row = Math.max(0, startRow - 1); row <= Math.min(board.rows() - 1, startRow + 1); row++) {
         for (int col = Math.max(0, startCol - 1); col <= Math.min(board.cols() - 1,startCol + 1); col++) {
            if (marked[row][col]) {
            	continue;
            }
            if (!dictionarytrie.hasPrefix(charSequence)) {
            	continue;
            }
            marked[row][col] = true;
            dfs(foundWords, addLetter(charSequence, board.getLetter(row, col)), marked, row, col, board);
            marked[row][col] = false;
         }
      }
   }
    private String addLetter(String to, char letter) {
      if (letter == Q_LETTER) {
      	return to + QU_STRING;
      } else {
      	return to + letter;
      }
   }
   
    private boolean isValidWord(String currentWord) {
      if (currentWord == null) {
           return false;
       }
      if (dictionarytrie.contains(currentWord) && currentWord.length() > 2) {
      	return true;
      } else {
      return false;
      }
    }


	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(String word) {
		if(dictionarytrie.contains(word)) {
			return dictionarytrie.get(word);
		} else {
		    return 0;
	    }

	}
}