import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Boggle {

    private static Trie dictionaryTrie;
    public static String[] findWords(char[][] board, String[] dictionary) {

        ArrayList<String> goodWords = new ArrayList<String>();

        // TODO: Complete the function findWords(). Add all words that are found both on the board
        //  and in the dictionary.

        dictionaryTrie = new Trie();
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

        //until we hit end of trie/stack is empty
        while (!locations.empty()){
            //north
            if (isValid(curx, cury-1, valid)){

            }
            //east
            if (isValid(curx+1, cury, valid)){

            }
            //south
            if (isValid(curx, cury+1, valid)){

            }
            //west
            if (isValid(curx-1, cury, valid)){

            }


        }



        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }

    public void dfs (int x, int y, String word, boolean[][] valid){
        if (!isValid(x,y,valid)){
            return;
        }
        //if all node children are null/end of trie
        if (){
            return;
        }


    }

    public static boolean isValid (int x, int y, boolean[][] valid){
        if (x < 0 || x > valid.length){
            return false;
        }
        else if (y < 0 || y > valid[1].length){
            return false;
        }
        return true;
    }
}
