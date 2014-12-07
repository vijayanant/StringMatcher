package com.vj.stringmatch;

public class StringMatcher {

    TrieSymbolTable ST = new TrieSymbolTable<Integer>();

    public void add_exact_match(String exact_str, int id) {
        ST.put(exact_str, id);
    }

    public void add_prefix_match(String prefix_str, int id) {
        ST.put(prefix_str, id);
    }

    public int lookup(String input) {
        Integer i = (Integer) ST.get(input);
        if (i == null) {
            i = (Integer) ST.longestPrefix(input);
        }

        if (i == null) {
            return -1;
        }
        return i;
    }

    public boolean delete_exact_match(String exact_str) {
        return ST.remove(exact_str);
    }

    public boolean delete_prefix_match(String prefix_str) {
        return ST.remove(prefix_str);
    }
};