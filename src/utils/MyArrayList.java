package utils;

import utils.exceptions.EmptyListException;

/**
 * Created by Alexandra on 02/11/2015.
 */
public class MyArrayList implements IListADT<String> {
    private static int MAXSIZE = 100; // fixed-size array
    private String[] items; // the items in the List
    private int size;   // the number of items in the List


    // constructor
    public MyArrayList() {
        size = 0;
        items = new String[MAXSIZE];
    }



    public void add(String item) {
        // if array is full, get new array of double size,
        // and copy items from old array to new array
        if (items.length == size) {
            System.out.println("The list is full!!");
        }

        // add new item; update size
        items[size] = item;
        size++;
    }

    public String getLast() throws EmptyListException{
        // throw exception if the list is empty
        if (size == 0) {
            throw new EmptyListException("The list is empty!!");
        }

        // return the last item
        return items[size-1];
    }


    /*
     * returns a string representation of the contents of the list
     */
    public String toString() {
        String str = "";
        for(int i = 0; i < size; i++) {
            str += items[i] + "\n";
        }
        return str;

    }



    // not necessary

    public boolean isEmpty() {

        return (size == 0);
    }



    public String remove(int pos) {
        // check for bad pos
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }

        // get the item to be removed from pos
        String ob = items[pos];

        // move items over to fill removed pos
        for (int k = pos; k < size-1; k++) {
            items[k] = items[k+1];
        }

        // decrease the number of items
        size--;

        // return the removed item
        return ob;
    }



    public int getSize() {

        return size;
    }


}
