/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.fileio;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import sanapuuro.words.WordList;

/**
 * Holds and reads valid English words from a file.
 * @author skaipio
 */
public final class WordReader implements WordList {
    private final String englishWordListPath = "words/english_words";
    private final Set<String> words;
    
    public WordReader(){
        this.words = this.getWords();
    }

    /**
     * @return All valid English words given by a file.
     */
    public Set<String> getWords() {
        try {
            InputStream file = ClassLoader.getSystemResourceAsStream(englishWordListPath);
            Scanner reader = new Scanner(file);
            Set<String> scannedWords = new HashSet<>();
            while (reader.hasNext()) {
                String word = reader.nextLine();
                scannedWords.add(word);
            }
            return scannedWords;
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return new HashSet<>();
    }

    /**
     * @param word Word to check.
     * @return True, if the word was found in the English word list, false otherwise.
     */
    @Override
    public boolean hasWord(String word) {
        return word.length() >= 2 && words.contains(word);
    }
}
