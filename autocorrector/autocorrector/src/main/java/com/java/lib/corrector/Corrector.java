package com.java.lib.corrector;

import com.java.lib.dictionary.IDictionary;
import com.java.lib.dictionary.IDictionaryCallback;
import com.java.lib.tree.element.ITree;
import com.java.lib.tree.element.Tree;
import com.java.lib.tree.node.ITreeNode;

import java.util.*;

public class Corrector implements ICorrector {
    private ITree tree;
    private Map<String, Integer> dictionary;
    private List<String> invalidWords;

    public Corrector() {
        this.tree = new Tree();
        this.invalidWords = Arrays.asList("lol", "abcdefghijklmnopqrstuvwxyz");
    }

    @Override
    public ITree getTree() {
        return this.tree;
    }

    @Override
    public ICorrector loadDictionary(Map<String, Integer> dictionary) {
        this.dictionary = dictionary;
        return this;
    }

    @Override
    public String correctText(String text) {
        String[] words = text.split(" ");
        for (String word : words) {
            if(!this.correctWord(word)) {
                String similarWord = this.suggestSimilarWord(word);
                if (similarWord != null) {
                    text = text.replace(word, similarWord);
                    if(word.substring(0, 1).equals(word.substring(0, 1).toUpperCase())) {
                        text = text.replace(similarWord, similarWord.substring(0, 1).toUpperCase() + similarWord.substring(1));
                    }
                }
            }
        }
        return text;
    }

    private boolean correctWord(String word) {
        return this.dictionary.containsKey(word.toLowerCase());
    }

    public String suggestSimilarWord(String word) {
        if (word == null || word.length() == 0 || this.invalidWords.contains(word.toLowerCase())) return null;
        String wordLower = word.toLowerCase();
        String res = null;
        TreeMap<Integer, TreeMap<Integer, TreeSet<String>>> map = new TreeMap<>();
        ITreeNode node = this.tree.find(wordLower);
        if (node == null) {
            for (String w : this.dictionary.keySet()) {
                int dist = this.editDistance(w, wordLower);
                TreeMap<Integer, TreeSet<String>> similarWords = map.getOrDefault(dist, new TreeMap<>());
                int freq = this.dictionary.get(w);
                TreeSet<String> set = similarWords.getOrDefault(freq, new TreeSet<>());
                set.add(w);
                similarWords.put(freq, set);
                map.put(dist, similarWords);
            }
            if (map.size() > 0) {
                res = map.firstEntry().getValue().lastEntry().getValue().first();
                if (word.charAt(word.length() - 1) == '.' || word.charAt(word.length() - 1) == ',') {
                    res += word.charAt(word.length() - 1);
                }
                if (Character.isUpperCase(word.charAt(0))) {
                    res = res.substring(0, 1).toUpperCase() + res.substring(1);
                }
            }
        }else {
            res = wordLower;
        }
        return res;
    }

    private int editDistance(String firstWord, String secondWord) {
        int n = firstWord.length();
        int m = secondWord.length();
        int dp[][] = new int[n+1][m+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0)
                    dp[i][j] = j;
                else if (j == 0)
                    dp[i][j] = i;
                else if (firstWord.charAt(i-1) == secondWord.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else if (i>1 && j>1  && firstWord.charAt(i-1) == secondWord.charAt(j-2)
                        && firstWord.charAt(i-2) == secondWord.charAt(j-1))
                    dp[i][j] = 1+Math.min(Math.min(dp[i-2][j-2], dp[i-1][j]), Math.min(dp[i][j-1], dp[i-1][j-1]));
                else
                    dp[i][j] = 1 + Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1]));
            }
        }
        return dp[n][m];
    }
}
