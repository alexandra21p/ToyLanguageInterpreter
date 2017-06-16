package domain.expressions;

import utils.IDictionaryADT;
import utils.IHeapADT;
import utils.exceptions.VariableException;

/**
 * Created by Alexandra on 02/11/2015.
 */
public class VarExp extends Exp {
    String id;

    // constructor
    public VarExp(String i) {
        id = i;
    }

    @Override
    public Integer eval(IDictionaryADT<String,Integer> table, IHeapADT<Integer,Integer> heap) throws VariableException{
            return table.lookUp(id);
    }

    // method that prints the expression into a string
    public String toString() {
        return id;
    }
}
