package domain.expressions;

import utils.IDictionaryADT;
import utils.IHeapADT;
import utils.exceptions.DivisionByZeroExcep;
import utils.exceptions.InvalidInputException;
import utils.exceptions.VariableException;

/**
 * Created by Alexandra on 02/11/2015.
 */

// class that defines an arithmetic expression
public class ArithExp extends Exp {
    Exp e1;
    Exp e2;
    int op; // operand


    // constructor for arithmetic expression
    public ArithExp(Exp e1, Exp e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Integer eval(IDictionaryADT<String,Integer> table, IHeapADT<Integer,Integer> heap) throws VariableException, DivisionByZeroExcep, InvalidInputException {
        if (op == 1) { // 1 stands for +
            return (e1.eval(table,heap) + e2.eval(table,heap));
        }
        else if (op == 2) { // 2 stands for -
            return (e1.eval(table,heap) - e2.eval(table,heap));
        }
        else if (op == 3) { // 3 stands for *
            return (e1.eval(table,heap) * e2.eval(table,heap));
        }
        else if (op == 4) { // 4 stands for /
            if (e2.eval(table,heap) == 0) {
                throw new DivisionByZeroExcep("Cannot divide by ZERO!!!");
            }
            return (e1.eval(table,heap) / e2.eval(table,heap));
        }
        else throw new InvalidInputException("Operand not recognized!");

    }

    /*
     * method that prints the expression into a string
     */
    public String toString() {
        String res = "";
        String[] arr = {"+", "-", "*", "/"};
        for (int i = 0; i < arr.length; i++) {
            if ((i+1) == op)
                res =  e1.toString() + arr[i] + e2.toString();
        }
        return res;
    }


}