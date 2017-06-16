package utils;

import utils.exceptions.VariableException;

/**
 * Created by Alexandra on 19/01/2016.
 */
public interface ILockTableADT<K,V> {
    void add(K key, V value) throws VariableException;
    V lookUp(K key) throws VariableException;
    boolean isDefined(K key);
    void updateValue(K key, V value);
}
