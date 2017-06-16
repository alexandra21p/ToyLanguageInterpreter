package utils;

import domain.statements.IStmt;
import utils.exceptions.EmptyStackExcep;

/**
 * Created by Alexandra on 02/11/2015.
 */
public interface IStackADT<T> {
    boolean isEmpty();
    void push(T item);
    T pop() throws EmptyStackExcep;
    T peek() throws Exception;
    Integer getSize();



}
