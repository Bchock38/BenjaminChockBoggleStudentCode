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
        goodWords = new ArrayList<String>();
        charBoard = board;
        dictionaryTrie = new Trie();
        goodTrie = new Trie();
        for (String words : dictionary){
            dictionaryTrie.insert(words);
        }

        int curx = 0;
        int cury = 0;
        Stack<int[]> locations = new Stack<>();
        int[] curLoc = new int[2];
        curLoc[0] = curx;
        curLoc [1] = cury;
        locations.push(curLoc);

        boolean[][] valid = new boolean[board.length][board[1].length];
        for (int i = 0; i < board.length; i ++){
            for (int j = 0; j< board[1].length; j ++){
                valid[i][j] = true;
            }
        }
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
        if (!isValid(x,y,valid)){
            return;
        }
        word += charBoard[x][y];
        //if all node children are null/end of trie
        if (!dictionaryTrie.isNotNull(word)){
            return;
        }
        if (dictionaryTrie.isWord(word)){
            if (!goodTrie.lookUp(word)){
                goodTrie.insert(word);
                goodWords.add(word);
            }

        }
        valid[x][y] = false;
        dfs(x,y-1,word,valid);
        dfs(x+1,y,word,valid);
        dfs(x,y+1,word,valid);
        dfs(x-1,y,word,valid);
        valid[x][y] = true;
      //  dfs(row, col, word):
       // if we have been here before, return
        //if this word is not a valid prefix, return
         //       mark this square as visited
        //recurse up, down, left, right with updated word
        //mark this square as not visited


    }

    public static boolean isValid (int x, int y, boolean[][] valid){
        if (x < 0 || x > valid.length-1){
            return false;
        }
        else if (y < 0 || y > valid[1].length-1){
            return false;
        }
        else return valid[x][y];
    }
}
