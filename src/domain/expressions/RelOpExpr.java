package domain.expressions;

import utils.IDictionaryADT;
import utils.IHeapADT;
import utils.exceptions.DivisionByZeroExcep;
import utils.exceptions.InvalidInputException;
import utils.exceptions.VariableException;

/**
 * Created by Alexandra on 03/11/2015.
 */
public class RelOpExpr extends Exp {
    Exp exp1;
    Exp exp2;
    String op;

    public RelOpExpr(Exp e1, Exp e2, String o) {
        exp1 = e1;
        exp2 = e2;
        op = o;
    }

    public Integer eval(IDictionaryADT<String,Integer> table, IHeapADT<Integer,Integer> heap) throws VariableException, DivisionByZeroExcep, InvalidInputException {
        int res = 0;
        if (op.equals("<")) {
            int ok = exp1.eval(table,heap) - exp2.eval(table,heap);

            if (ok < 0) {
                return 1;
            }
            if (ok >= 0) {
                return 0;
            }
        }

        if (op.equals("<=")){
            int ok = exp1.eval(table,heap) - exp2.eval(table,heap);
            if (ok <= 0) {
                return 1;
            }
            if (ok > 0) {
                return 0;
            }
        }


        if (op.equals("==")) {
            int ok = exp1.eval(table,heap) - exp2.eval(table,heap);
            if (ok == 0) {
                return 1;
            }
            else
                return 0;

        }

        if (op.equals("!=")) {
            if (exp1.eval(table,heap) != (exp2.eval(table,heap))) {
                return 1;
            }
            else return 0;
        }

        if (op.equals(">")) {
            int ok = exp1.eval(table,heap) - exp2.eval(table,heap);

            if (ok > 0) {
                return 1;
            }
            if (ok <= 0) {
                return 0;
            }
        }

        if (op.equals(">=")) {
            int ok = exp1.eval(table,heap) - exp2.eval(table,heap);
            if (ok >= 0) {
                return 1;
            }
            if (ok < 0) {
                return 0;
            }
        }

    return res;
    }

    public String toString() {
        return exp1.toString() + op + exp2.toString();
    }

}
