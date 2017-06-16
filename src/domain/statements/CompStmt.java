package domain.statements;

import domain.PrgState;
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
public class CompStmt implements IStmt, Serializable {
    IStmt first;
    IStmt snd;

    // constructor for CompStmt
    public CompStmt(IStmt first, IStmt snd) {
        this.first = first;
        this.snd = snd;
    }

    // getters
    public IStmt getFirst() {
        return first;
    }

    public IStmt getSecond() {
        return snd;
    }


    /*
     * method that prints the statement into a string
    */
    public String toString() {
        return "(" + first.toString() + "; " + snd.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws VariableException, InvalidInputException, DivisionByZeroExcep {
        IStackADT<IStmt> stk = state.getExeStack();
        IDictionaryADT<String, Integer> symTbl = state.getSymTable();
        IListADT<String> out = state.getOut();
        IHeapADT<Integer, Integer> hp = state.getHeap();

        IStmt first = getFirst();
        IStmt snd = getSecond();
        stk.push(snd);
        stk.push(first);
        return null;
    }

}