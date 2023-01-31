package com.java.lib.dictionary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.function.Function;

public interface IDictionary {
    Map<String, Integer> loadDictionary(String path, IDictionaryCallback callback) throws IOException;
}
