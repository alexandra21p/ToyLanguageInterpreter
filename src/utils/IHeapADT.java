package utils;

import utils.exceptions.VariableException;

/**
 * Created by Alexandra on 06/12/2015.
 */
public interface IHeapADT<K,V> {
    void add(K key, V value) throws VariableException;
    V lookUp(K key) throws VariableException;
    boolean isDefined(K key);
    void updateValue(K key, V value);
}
