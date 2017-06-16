package utils;

/**
 * Created by Alexandra on 02/11/2015.
 */
public class DictEntry<K,V> {
    K key;  // variable name
    V value;

    public DictEntry(K k, V v) {
        key = k;
        value = v;
    }

    // getters for id and value
    public V getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }

    // setter for value
    public void setValue(V value) {
        this.value = value;
    }
}
