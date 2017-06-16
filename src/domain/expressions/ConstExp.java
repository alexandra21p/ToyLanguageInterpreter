package domain.expressions;

import utils.IDictionaryADT;
import utils.IHeapADT;

/**
 * Created by Alexandra on 02/11/2015.
 */
public class ConstExp extends Exp {
    int number;

    // constructor
    public ConstExp(int nr) {
        number = nr;
    }

    @Override
    public Integer eval(IDictionaryADT<String,Integer> table, IHeapADT<Integer,Integer> heap) {
        return number;
    }

    /*
     * method that prints the expression into a string
     */
    public String toString(){
        return Integer.toString(number);
    }
}
