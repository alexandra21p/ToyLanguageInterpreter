package domain.statements;

import domain.PrgState;
import domain.expressions.Exp;
import domain.statements.IStmt;
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
public class PrintStmt implements IStmt, Serializable {
    public Exp expr;

    // constructor
    public PrintStmt(Exp expr) {
        this.expr = expr;
    }

    /*
     * method that prints the statement into a string
     */
    public String toString(){
        return "print(" + expr.toString()+ ")";
    }

    @Override
    public PrgState execute(PrgState state) throws VariableException, InvalidInputException, DivisionByZeroExcep {
        IStackADT<IStmt> stk = state.getExeStack();
        IDictionaryADT<String, Integer> symTbl = state.getSymTable();
        IListADT<String> out = state.getOut();
        IHeapADT<Integer, Integer> hp = state.getHeap();

        String res = "";
        res += expr.eval(symTbl, hp);
        out.add(res);

        return null;
    }


}
