package domain.expressions;

import utils.IDictionaryADT;
import utils.IHeapADT;
import utils.exceptions.DivisionByZeroExcep;
import utils.exceptions.InvalidInputException;
import utils.exceptions.VariableException;

/**
 * Created by Alexandra on 06/11/2015.
 */
public class LogicalOpExpr extends Exp {
    Exp exp1;
    Exp exp2;
    String op;

    public LogicalOpExpr(Exp e1, Exp e2, String o) {
        exp1 = e1;
        exp2 = e2;
        op = o;
    }

    @Override
    public Integer eval(IDictionaryADT<String,Integer> table, IHeapADT<Integer,Integer> heap) throws VariableException, DivisionByZeroExcep, InvalidInputException {
        int res = 0;
        if (op.equals("&&")) {
            if ((exp1.eval(table,heap) != 0)  && (exp2.eval(table,heap) != 0)) {
                return 1;
            }
            else return 0;
        }

        if (op.equals("||")) {
            if ((exp1.eval(table,heap) != 0)  || (exp2.eval(table,heap) != 0)) {
                return 1;
            }
            else return 0;
        }
        return res;
    }

    public String toString() {
        String str = "";
        if (op.equals("&&") || op.equals("||")) {
            str = exp1.toString() + " " + op + " " + exp2.toString();
        }
        return str;
    }
}
