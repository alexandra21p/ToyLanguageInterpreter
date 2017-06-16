package domain.expressions;

import utils.IDictionaryADT;
import utils.IHeapADT;
import utils.exceptions.DivisionByZeroExcep;
import utils.exceptions.InvalidInputException;
import utils.exceptions.VariableException;

/**
 * Created by Alexandra on 07/11/2015.
 */
public class NotExpr extends Exp {
    Exp exp1;
    String op;

    public NotExpr(Exp e, String o) {
        exp1 = e;
        op = o;
    }

    @Override
    public Integer eval(IDictionaryADT<String,Integer> table, IHeapADT<Integer,Integer> heap) throws VariableException, DivisionByZeroExcep, InvalidInputException {
        int res = 0;
        if (op.equals("!")) {
            if (exp1.eval(table,heap) != 0) {
                return 0;
            }
            else return 1;
        }
        return res;
    }

    @Override
    public String toString() {
        return op + "(" + exp1.toString() + ") ";
    }
}
