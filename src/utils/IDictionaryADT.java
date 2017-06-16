package utils;

import utils.exceptions.VariableException;

/**
 * Created by Alexandra on 02/11/2015.
 */
public interface IDictionaryADT<K,V> {
    void add(K key, V value) throws VariableException;
    V lookUp(K key) throws VariableException;
    boolean isDefined(K key);
    void updateValue(K key, V value);
}

