package domain.statements;

import domain.PrgState;
import domain.expressions.Exp;
import utils.IDictionaryADT;
import utils.IHeapADT;
import utils.IListADT;
import utils.IStackADT;
import utils.exceptions.DivisionByZeroExcep;
import utils.exceptions.InvalidAddressExcep;
import utils.exceptions.InvalidInputException;
import utils.exceptions.VariableException;

import java.io.Serializable;

/**
 * Created by Alexandra on 07/12/2015.
 */
public class WriteHeapStmt implements IStmt, Serializable {
    String var;
    Exp expr;

    public WriteHeapStmt(String v, Exp e) {
        var = v;
        expr = e;
    }

    @Override
    public String toString() {
        return "wH(" + var + "," + expr + ")";

    }

    @Override
    public PrgState execute(PrgState state) throws VariableException, InvalidInputException, DivisionByZeroExcep, InvalidAddressExcep {
        //IStackADT<IStmt> stk = state.getExeStack();
        IDictionaryADT<String, Integer> symTbl = state.getSymTable();
        //IListADT<String> out = state.getOut();
        IHeapADT<Integer, Integer> hp = state.getHeap();

        int addr = symTbl.lookUp(var);
        int val = expr.eval(symTbl, hp);

        if(!hp.isDefined(addr))
            throw new InvalidAddressExcep("The address is invalid!!");
        hp.updateValue(addr, val);

        return null;
    }
}
