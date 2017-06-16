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
 * Created by Alexandra on 07/11/2015.
 */
public class IfThenStmt implements IStmt, Serializable {
    Exp cond;
    IStmt thenS;

    // class constructor
    public IfThenStmt(Exp c, IStmt t) {
        cond = c;
        thenS = t;
    }

    // getters
    public Exp getCond() {
        return cond;
    }

    public IStmt getThenS() {
        return thenS;
    }

    @Override
    public String toString() {
        return "IF " + cond.toString() + " THEN " + thenS.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws VariableException, InvalidInputException, DivisionByZeroExcep {
        IStackADT<IStmt> stk = state.getExeStack();
        IDictionaryADT<String, Integer> symTbl = state.getSymTable();
        IListADT<String> out = state.getOut();
        IHeapADT<Integer, Integer> hp = state.getHeap();

        Exp exp = getCond();
        IStmt stmt = getThenS();
        IfStmt ifStmt = new IfStmt(exp,stmt,new SkipStmt());
        stk.push(ifStmt);

        return null;
    }


}
