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
 * Created by Alexandra on 06/11/2015.
 */
public class WhileStmt implements IStmt, Serializable {
    Exp cond;
    IStmt st;

    public WhileStmt(Exp c, IStmt s) {
        cond = c;
        st = s;
    }

    public Exp getExpr() {
        return cond;
    }

    public IStmt getSt() {
        return st;
    }

    public String toString() {
        return "while " + "(" + cond.toString() + ")" + st.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws VariableException, InvalidInputException, DivisionByZeroExcep {
        IStackADT<IStmt> stk = state.getExeStack();
        IDictionaryADT<String, Integer> symTbl = state.getSymTable();
        IListADT<String> out = state.getOut();
        IHeapADT<Integer, Integer> hp = state.getHeap();

        if(getExpr().eval(symTbl,hp) != 0) {
            stk.push(this);
            stk.push(getSt());
        }
        return null;
    }


}
