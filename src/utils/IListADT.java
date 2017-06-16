package utils;

import utils.exceptions.EmptyListException;

/**
 * Created by Alexandra on 02/11/2015.
 */
public interface IListADT<T> {
    void add(T item);
    T getLast() throws EmptyListException;

    // optional functions for this implementation
    int getSize();
    boolean isEmpty();
    T remove(int pos);
}
