package utils;

import utils.exceptions.EmptyListException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Alexandra on 07/11/2015.
 */
public class MyLibList<T> implements IListADT<T>, Serializable {
    private ArrayList<T> list = new ArrayList<>();

    @Override
    public void add(T item) {
        list.add(item);
    }

    public T getLast() throws EmptyListException{
        // throw exception if the list is empty
        if (list.isEmpty()) {
            throw new EmptyListException("The list is empty!!");
        }

        // return the last item
        return list.get(list.size()-1);
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public T remove(int pos) {
        return list.remove(pos);
    }

    public String toString() {
        final String[] str = {""};
        list.forEach( (T) -> str[0] += T + "\n");
        str[0] += "\n";
        return str[0];
    }
}
