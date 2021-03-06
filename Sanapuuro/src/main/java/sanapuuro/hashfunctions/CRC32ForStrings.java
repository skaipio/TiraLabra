package sanapuuro.hashfunctions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.zip.CRC32;

/**
 * HashFunction that uses Java-implementation of CRC32.
 * http://www.jonh.net/~jonh/md5/CRC32.java
 * @author skaipio
 */
public class CRC32ForStrings extends HashFunction<String> {
    private final CRC32 crc = new CRC32();

    /**
     * Calculates normal hash value without number of tries taken into account.
     * @param o String to calculate a hash for.
     * @return The hash value of string s.
     */
    @Override
    public int getHash(String o) {
        crc.update(o.getBytes());
        return (int) (crc.getValue() % Integer.MAX_VALUE);
    }
}
