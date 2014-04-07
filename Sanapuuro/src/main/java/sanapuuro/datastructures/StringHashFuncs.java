/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.datastructures;

import java.math.BigInteger;

/**
 *
 * @author skaipio
 */
public class StringHashFuncs implements HashFuncs<String> {
    /**
     * Calculates normal hash value for a string.
     * @param s String to calculate a hash for.
     * @return The hash value of string s.
     */
    @Override
    public int getNormalHash(String s, int m){
        BigInteger hash = new BigInteger("0");
        for (int i = 0; i < s.length(); i++){
            int charVal = s.charAt(i);
            BigInteger ascii = new BigInteger(charVal + "");
            BigInteger multiplier = new BigInteger("128");
            multiplier = multiplier.pow(i);
            hash.add(ascii.multiply(multiplier));
        }
        BigInteger remainder = hash.mod(new BigInteger(m+""));
        return remainder.intValue();
    }
    
    /**
     * Calculates hash value for a string with number of tries taken into account.
     * @param s String to calculate a hash for.
     * @return The hash value of string s.
     */
    @Override
    public int getHash(String s, int numberOfTry, int m){
       int hash = this.getNormalHash(s, m);
       return hash + numberOfTry;
    }
    
    @Override
    public int calculateM(int numberOfKeys, double desiredLoadRate) {
        int estimatedTableSize = (int) (numberOfKeys / desiredLoadRate);
        int[] primesCloseToTableSize = Util.findPrimesCloseTo(estimatedTableSize);
        return Util.pickNumberThatIsFarthestFromPowerOfTwo(primesCloseToTableSize);
    }
}
