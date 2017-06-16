package utils;

import utils.exceptions.VariableException;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Alexandra on 19/01/2016.
 */
public class LockTable<K,V> implements ILockTableADT<K,V>, Serializable  {
    HashMap<K,V> heap = new HashMap<>();


    @Override
    /*
     * adds a new element pair entry(key, value) into the dictionary
     * pre: key - string, value - int
     * post: element is added
     * throws Exception if the key already exists
     */
    public void add(K key, V value) throws VariableException {
        if (heap.containsKey(key)) {
            throw new VariableException("It already exists!!");
        }
        heap.put(key, value);

    }

    @Override
     /*
     * returns the value of an entry from the dictionary
     * throws Exception if element is not found
     * pre: id - string
     * post: returns the value - int, matching the key
     */
    public V lookUp(K key) throws VariableException {
        if (!heap.containsKey(key)) {
            throw new VariableException("Variable is not defined!");
        }
        return heap.get(key);
    }

    @Override
    /*
	 * boolean method that checks if an entry with a given id exists in the dictionary
	 * pre: id - int
	 * post:returns true if the variable is defined, false otherwise
	 */
    public boolean isDefined(K key) {
        return heap.containsKey(key);
    }

    @Override
    /*
     * method that updates an entry in the dictionary
     * pre: id - int
     * post: updates the value corresponding to the given id
     */
    public void updateValue(K key, V value) {
        heap.replace(key,value);

    }

    /*
 * method that prints into a string the contents of the dictionary entries
 * returns the result, str of type String
 */
    public String toString() {
        final String[] str = {""};
        heap.forEach( (K,V) -> str[0] += K + "->" + V + "\n");
        str[0] += "\n";
        return str[0];
    }

}
