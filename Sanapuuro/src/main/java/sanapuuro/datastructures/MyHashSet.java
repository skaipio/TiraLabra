/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.datastructures;

import sanapuuro.hashfunctions.HashFunction;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * A fixed size hash set for holding any kind of objects. The hash value for an
 * object is determined by the hash function supplied for this set. The actual
 * size of the table is determined by the m-value calculated by the hash
 * function.
 *
 * @author skaipio
 */
public class MyHashSet<T> implements Set<T>{

    private Object[] table;
    public final int tableSize;
    private final HashFunction<T> hashFunction;
    private int numberOfItems = 0;
    private int maxNumberOfItems;

    /**
     * Creates a fixed size hash set with the desired load rate.
     *
     * @param numberOfKeys Minimum number of keys this set should be able to
     * hold.
     * @param hashFunction Function used to determine hash value for an object of type T.
     * @param desiredLoadRate Desired load rate of the table, the smaller the value is the less there will be conflicts.
     */
    public MyHashSet(int numberOfKeys, HashFunction<T> hashFunction, double desiredLoadRate) {
        this.hashFunction = hashFunction;
        this.tableSize = this.calculateM(numberOfKeys, desiredLoadRate);
        this.table = new Object[this.tableSize];
        this.maxNumberOfItems = numberOfKeys;
    }
    
    /**
     * Creates a fixed size hash set with an approximate load rate of 0.33 when
     * filled with maximum amount of objects.
     *
     * @param numberOfKeys Minimum number of keys this set should be able to
     * hold.
     * @param hashFunction Function used to determine hash value for an object.
     */
    public MyHashSet(int numberOfKeys, HashFunction<T> hashFunction) {
        this(numberOfKeys, hashFunction, 0.33);
    }

    @Override
    public int size() {
        return this.numberOfItems;
    }

    @Override
    public boolean isEmpty() {
        return numberOfItems == 0;
    }

    @Override
    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }

        int numberOfTries = 0;
        while (numberOfTries < this.tableSize) {
            int hash = this.getTableIndex((T)obj, numberOfTries);
            if (this.table[hash] == null) {
                return false;
            } else if (this.table[hash].equals(obj)) {
                return true;
            } else {
                numberOfTries++;
            }
        }
        return false;
    }

    /**
     * This iterator does not create a copy of the hash table and does not
     * support removing.
     *
     * @return Iterator for traversing this set.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iter<>();
    }

    /**
     * Adds an object to this set. If two objects have the same hash value, uses
     * equals() method to determine if the objects are same. If they are, then
     * the given object will not be added to the set.
     *
     * @param obj Object to add.
     * @return True if no equal object is in the set and the set is not full,
     * false otherwise.
     */
    @Override
    public boolean add(Object obj) {
        if (obj == null || this.numberOfItems == this.maxNumberOfItems) {
            return false;
        }

        int numberOfTries = 0;
        while (numberOfTries < this.tableSize) {
            int hash = this.getTableIndex((T)obj, numberOfTries);
            if (this.table[hash] == null || this.table[hash] == Del.DEL) {
                this.table[hash] = obj;
                this.numberOfItems++;
                return true;
            } else if (this.table[hash].equals(obj)) {
                return false;
            }
            numberOfTries++;
        }
        return false;
    }

    /**
     * Removes the given object from the set.
     * @param obj Object to remove.
     * @return True if object was found in the set, false otherwise.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj == null || this.numberOfItems == 0) {
            return false;
        }

        int numberOfTries = 0;
        while (numberOfTries < this.tableSize) {
            int hash = this.getTableIndex((T)obj, numberOfTries);
            if (this.table[hash] == null) {
                return false;
            } else if (this.table[hash].equals(obj)) {
                this.table[hash] = Del.DEL;
                this.numberOfItems--;
                return true;
            } else {
                numberOfTries++;
            }
        }
        return false;
    }

    /**
     * Replaces the hash table with a new empty table with the same size.
     */
    @Override
    public void clear() {
        this.table = new Object[tableSize];
    }

    // The rest of these methods are not mandatory for TiraLabra datastructure implementation.
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Helper method for getting the index position of an object in the hash table.
     * @param o Object to calculate an index position for.
     * @param i The ith number of try.
     * @return The index position of object o.
     */
    private int getTableIndex(T o, int i) {
        return Math.abs(this.hashFunction.getHash(o, i)%this.tableSize);
    }
    
    /**
     * Calculates the optimal M value based on the number of keys and desired
     * load or fill rate of the hash table.
     *
     * @param numberOfKeys Max number of keys the hash table will be holding.
     * @param desiredLoadRate Desired fill or load rate. Generally a smaller fill rate leads to less collisions.
     * @return A suitable value for hash table size.
     */
    private int calculateM(int numberOfKeys, double desiredLoadRate) {
        int estimatedTableSize = (int) (numberOfKeys / desiredLoadRate);
        int[] primesCloseToTableSize = PrimeNumberUtils.findPrimesCloseTo(estimatedTableSize);
        return PrimeNumberUtils.pickNumberThatIsFarthestFromPowerOfTwo(primesCloseToTableSize);
    }
    
    /**
     * Iterator for iterating over all the elements of this set.
     * @param <T> The type of the iterator which will be the same as the set's.
     */
    private class Iter<T> implements Iterator<T> {

        private int i = 0;

        @Override
        public boolean hasNext() {
            int j = i;
            while (j < tableSize) {
                if (table[j] == null) {
                    j++;
                } else {
                    return true;
                }
            }
            return false;
        }

        @Override
        public T next() {
            int j = i;
            while (j < tableSize) {
                if (table[i] == null) {
                    i++;
                } else {
                    i = j + 1;
                    return (T) table[j];
                }
            }
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
    
    

    /**
     * Special Del value for marking hash table slots where an object has been removed from.
     */
    private enum Del {
        DEL
    }
}
