package com.java;

import com.java.lib.corrector.Corrector;
import com.java.lib.corrector.ICorrector;
import com.java.lib.dictionary.Dictionary;
import com.java.lib.dictionary.IDictionary;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        ICorrector corrector = new Corrector();
        IDictionary dictionary = new Dictionary();

            Map<String, Integer> dict = dictionary.loadDictionary("dictionary.txt", corrector.getTree());
            corrector.loadDictionary(dict);
            Date date = new Date();
            String wrongText = "Hellı, mu name is John";
            String correctText = corrector.correctText(wrongText);
            System.out.println("wrongText: " + wrongText);
            System.out.println("suggest text: " + correctText);
            System.out.println("time: " + (new Date().getTime() - date.getTime()) + " ms");

    }
}