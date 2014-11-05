package com.vj.stringmatch;

public class TrieSymbolTable <Value> {

    private class Node {

        char key;
        Value value;
        Node left, mid, right;
        public Node(char c, Value v) {
            key = c;
            value = v;
        }

    }

    private Node root;



    public Value get(String key) {
        Node x = get(root, key, 0);

        if(x == null) return null;
        return x.value;
    }

    private Node get(Node x, String key, int d) {
        if(x == null) return null;

        char c = key.charAt(d);

        if (c < x.key) return get(x.left, key, d);
        if (c > x.key) return  get(x.right, key, d);

        if (d < key.length() -1) return  get(x.mid, key, d+1);

        return  x;
    }

    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, Value value, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node(c, null);
        }

        if      (c < x.key)         x.left = put(x.left, key, value, 0);
        else if (c > x.key)         x.right = put(x.right, key, value, 0);
        else if (d < key.length() -1)  x.mid = put(x.mid, key, value, d+1);
        else                        x.value = value;

        return  x;
    }

    public Value longestPrefix(String key) {
        if (key == null || key.length() == 0) return null;
        int length = 0;
        Node x = root;
        int i = 0;
        Value value = null;
        while (x != null && i < key.length()) {
            char c = key.charAt(i);
            if      (c < x.key) x = x.left;
            else if (c > x.key) x = x.right;
            else {
                i++;
                if (x.value != null)  {
                    length = i;
                    value = x.value;
                }
                x = x.mid;
            }
        }
        return  value;
    }

    public boolean remove(String key) {
        if (get(key) != null) {
            root = remove(root, key, 0);
            return true;
        }
        return false;
    }

    private Node remove(Node x, String key, int d) {
        if(x == null) return null;

        char c = key.charAt(d);


        if      (c < x.key) x.left =  remove(x.left, key, d);
        else if (c > x.key) x.right = remove(x.right, key, d);
        else if (d < key.length() -1) x.mid = remove(x.mid, key, d+1);

        // found the node
        //delete node if it has no children
        if (d == key.length() -1) {
            x.value = null;
        }

        if (x.left == null && x.right == null && x.mid == null) {
            return null;
        }
        return x;
    }
}
