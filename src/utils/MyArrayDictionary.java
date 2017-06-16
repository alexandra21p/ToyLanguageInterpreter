package utils;

import utils.exceptions.VariableException;

/**
 * Created by Alexandra on 02/11/2015.
 */
public class MyArrayDictionary implements IDictionaryADT<String, Integer> {
    private static Integer MAXSIZE = 50;
    DictEntry<String,Integer>[] elems;
    Integer size;
    // constructor for dictionary
    public MyArrayDictionary() {
        elems = new DictEntry[MAXSIZE];
        size = 0;
    }

    /*
     * adds a new element pair entry(key, value) into the dictionary
     * pre: key - string, value - int
     * post: element is added
     * throws Exception if the key already exists
     */
    public void add(String key, Integer value) throws VariableException {
        for (int i = 0; i < size; i++) {
            String current = elems[i].getKey();
            if (current.equals(key)) {
                throw new VariableException("It already exists!!");
            }
        }
        DictEntry<String, Integer> entry = new DictEntry<>(key, value);
        elems[size] = entry;
        size++;
    }


    /*
     * returns the value of an entry from the dictionary
     * throws Exception if element is not found
     * pre: id - string
     * post: returns the value - int, matching the key
     */
    public Integer lookUp(String key) throws VariableException {
        int value = 0;
        for (int i = 0; i < size; i++) {
            String current = elems[i].getKey();
            if (current.equals(key)) {
                value = elems[i].getValue();
            }
            /*
            else{
                throw new Exception("Variable is not defined!");
            }
            */
        }
        return value;
    }

    /*
	 * boolean method that checks if an entry with a given id exists in the dictionary
	 * pre: id - int
	 * post:returns true if the variable is defined, false otherwise
	 */
    public boolean isDefined(String key) {
        boolean res = false;
        for (int i = 0; i < size; i++) {
            String current = elems[i].getKey();
            if (current.equals(key)) {
                res = true;
            }
        }
        return res;
    }

    /*
     * method that updates an entry in the dictionary
     * pre: id - int
     * post: updates the value corresponding to the given id
     */
    public void updateValue(String key, Integer value) {
        for (int i = 0; i < size; i++) {
            String current = elems[i].getKey();
            if (current.equals(key)) {
                elems[i].setValue(value);
            }
            else
                System.out.println("Variable is not defined!");
            }
        }

    /*
     * method that prints into a string the contents of the dictionary entries
     * returns the result, str of type String
     */
    public String toString() {
        String str = "{ ";
        for(int i = 0;i < size;i++) {
            DictEntry<String, Integer> entry = elems[i];
            str += entry.getKey() + "->" + entry.getValue() + ", ";
        }
        str += "} \n";
        return str;
    }

}
