import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Boggle {

    private static char[][] charBoard;
    private static Trie dictionaryTrie;
    private static ArrayList<String> goodWords;
    private static Trie goodTrie;
    public static String[] findWords(char[][] board, String[] dictionary) {


        // TODO: Complete the function findWords(). Add all words that are found both on the board
        //  and in the dictionary.
        //intialize Arraylist to hold all valid non duplicate words
        goodWords = new ArrayList<String>();
        //intialize board as a global variable so I can access it in my dfs method
        charBoard = board;
        //create a trie to hold all words in dictionary
        dictionaryTrie = new Trie();
        //create a trie to hold all good words to check for duplicate words
        goodTrie = new Trie();
        //insert all words from dictionary into the dictionary trie
        for (String words : dictionary){
            dictionaryTrie.insert(words);
        }
        //create a sepearte duplicate board to save whetehr a space has been visted or not
        boolean[][] valid = new boolean[board.length][board[1].length];
        for (int i = 0; i < board.length; i ++){
            for (int j = 0; j< board[1].length; j ++){
                valid[i][j] = true;
            }
        }
        //use dfs on all the spots in the board
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[1].length; j++){
                dfs(i,j,"",valid);
            }
        }

        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }

    public static void dfs (int x, int y, String word, boolean[][] valid){
        //if the spot is not valid/out of bounds or already visted end
        if (!isValid(x,y,valid)){
            return;
        }
        //add the new charcter to teh current prefix
        word += charBoard[x][y];
        //if all node children are null/end of trie
        if (!dictionaryTrie.isNotNull(word)){
            return;
        }
        //if the word is in the dictionary check if it's duplicate if not then add to good words
        if (dictionaryTrie.isWord(word)){
            if (!goodTrie.lookUp(word)){
                goodTrie.insert(word);
                goodWords.add(word);
            }

        }
        //set location to visted
        valid[x][y] = false;
        //recurse up, down, left, right with updated word
        dfs(x,y-1,word,valid);
        dfs(x+1,y,word,valid);
        dfs(x,y+1,word,valid);
        dfs(x-1,y,word,valid);
        //mark this square as not visited
        valid[x][y] = true;




    }

    public static boolean isValid (int x, int y, boolean[][] valid){
       //if square is out of bounds return false
        if (x < 0 || x > valid.length-1){
            return false;
        }
        else if (y < 0 || y > valid[1].length-1){
            return false;
        }
        //return if square is visted or not
        else return valid[x][y];
    }
}
