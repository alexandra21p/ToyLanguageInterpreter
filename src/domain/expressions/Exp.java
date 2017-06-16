package domain.expressions;

import utils.IDictionaryADT;
import utils.IHeapADT;
import utils.exceptions.DivisionByZeroExcep;
import utils.exceptions.InvalidInputException;
import utils.exceptions.VariableException;

import java.io.Serializable;

/**
 * Created by Alexandra on 02/11/2015.
 */

// class that defines an expression
public class Exp implements Serializable {
    public Integer eval(IDictionaryADT<String,Integer> table, IHeapADT<Integer,Integer> heap) throws VariableException, DivisionByZeroExcep, InvalidInputException {
        return 0;
    }

    public String toString() {
        return "";
    }
}
