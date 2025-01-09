public class Trie {

    private final static int radix = 26;
    private Node root = new Node();


    private static class Node {
        private boolean isword = false;
        private Node[] next = new Node[radix];
    }

    public boolean lookUp(String toLookUp){
        return nodeGet(root,toLookUp,0);
    }

    public boolean isNotNull(String toLookUp){
        Node node = nodeGet2(root, toLookUp, 0);
        if (node == null){
            return false;
        }
        return true;
    }

    public boolean isWord(String toLookup){
        Node node = nodeGet2(root,toLookup,0);
        return node.isword;
    }

    private Node nodeGet2 (Node currentNode, String toLookUp, int depth){
        if (depth == toLookUp.length()){
            return currentNode;
        }
        else if (currentNode == null){
            return null;
        }
        return nodeGet2(currentNode.next[(int)toLookUp.charAt(depth) - 'a'], toLookUp, depth+1);
    }

    private Boolean nodeGet(Node currentNode, String toLookUp, int depth){
        if (currentNode == null){
            return false;
        }
        if (depth == toLookUp.length() && currentNode.isword){
            return true;
        }
        if (depth == toLookUp.length()){
            return false;
        }

        return nodeGet(currentNode.next[(int)toLookUp.charAt(depth) - 'a'], toLookUp, depth+1);
    }

    public void insert(String toPut){
        put(root, toPut, 0);
    }

    private void put(Node currentNode, String toPut, int depth){
        if (depth == toPut.length()){
            currentNode.isword = true;
            return;
        }
        if (currentNode.next[(int)toPut.charAt(depth) -'a'] == null){
            currentNode.next[(int)toPut.charAt(depth) - 'a'] = new Node();
        }
        put(currentNode.next[(int)toPut.charAt(depth) - 'a'], toPut, depth+1);
    }
}

