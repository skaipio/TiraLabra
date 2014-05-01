/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.hashfunctions;

/**
 * Hash function implementation based on TirA-material.
 * Uses longs instead of BigIntegers which makes lookups
 * quite a bit faster.
 * @author skaipio
 */
public class GeneralHashFuncForStrings extends HashFunction<String> {
    /**
     * Calculates normal hash value without number of tries taken into account.
     * @param s String to calculate a hash for.
     * @return The hash value of string s.
     */
    @Override
    public int getHash(String s) {
        long hash = 0;
        for (int i = 0; i < s.length(); i++){
            // lower case ASCII char values start at 97
            
            // same as when using BigIntegers, -97 results in a few hundred more collisions
            // -96 just works better
            long charVal = s.charAt(i)-96;  
            // 26 characters in use
            hash += charVal*Math.pow(26, i);
            // the power becomes quite large, but the hash value stays well
            // under the maximum of long (2^63-1) with 8 character strings
        }
        return (int)(hash % Integer.MAX_VALUE);
    }

    /**
     * Calculates hash value for a string with number of tries taken into account.
     * @param s String to calculate a hash for.
     * @param i The ith try to take into account.
     * @return The hash value of string s.
     */
    @Override
    public int getHash(String s, int i) {
       return this.getHash(s)+i;
    }
}
