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

import java.io.*;

/**
 * Created by Alexandra on 06/12/2015.
 */
public class AllocHeapStmt implements IStmt, Serializable {
    String var;
    Exp expr;

    public AllocHeapStmt(String v, Exp e) {
        var = v;
        expr = e;
    }

    @Override
    public String toString() {
        return "new(" + var  + ", " +  expr + ")";

    }

    @Override
    public PrgState execute(PrgState state) throws VariableException, InvalidInputException, DivisionByZeroExcep {
        IStackADT<IStmt> stk = state.getExeStack();
        IDictionaryADT<String, Integer> symTbl = state.getSymTable();
        IListADT<String> out = state.getOut();
        IHeapADT<Integer, Integer> hp = state.getHeap();

        int free_loc = 1;
        while(hp.isDefined(free_loc)) {
            free_loc++;
        }
        int val = expr.eval(symTbl, hp); // evaluate the expression

        if(symTbl.isDefined(var))
            symTbl.updateValue(var, free_loc);
        else
            symTbl.add(var, free_loc);

        hp.add(free_loc, val);

        return null;
    }
}
