package com.java.lib.corrector;

import com.java.lib.tree.element.ITree;
import java.util.Map;

public interface ICorrector {
    ITree getTree();

    ICorrector loadDictionary(Map<String, Integer> dictionary);
    String correctText(String text);
    String suggestSimilarWord (String word);

}
