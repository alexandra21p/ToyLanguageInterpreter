package domain.statements;

import domain.PrgState;
import domain.expressions.Exp;
import utils.IDictionaryADT;
import utils.IHeapADT;
import utils.IListADT;
import utils.IStackADT;
import utils.exceptions.DivisionByZeroExcep;
import utils.exceptions.InvalidInputException;
import utils.exceptions.VariableException;

import java.io.Serializable;

/**
 * Created by Alexandra on 02/11/2015.
 */
public class AssignStmt implements IStmt, Serializable {
    String key;  // variable name
    Exp expr;

    public AssignStmt(String k, Exp e) {
        key = k;
        expr = e;
    }


    public PrgState execute(PrgState state) throws VariableException, InvalidInputException, DivisionByZeroExcep {
        IStackADT<IStmt> stk = state.getExeStack();
        IDictionaryADT<String, Integer> symTbl = state.getSymTable();
        IListADT<String> out = state.getOut();
        IHeapADT<Integer, Integer> hp = state.getHeap();

        String key = getKey();
        Exp exp = getExpr();
        int val = 0;
        val = exp.eval(symTbl, hp);

        if (symTbl.isDefined(key)) {
            symTbl.updateValue(key, val);
        }
        else {
            symTbl.add(key, val);
        }
        return null;
    }


    // getters for id and expression
    public String getKey() {
        return key;
    }

    public Exp getExpr() {
        return expr;
    }

    /*
     * method that prints the statement into a string
     */
    public String toString(){
        return key + " = " + expr.toString();
    }

}
