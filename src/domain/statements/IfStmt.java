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
public class IfStmt implements IStmt, Serializable {
    Exp expr;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp exp, IStmt t, IStmt e) {
        expr = exp;
        thenS = t;
        elseS = e;
    }

    // getters for expression, then statement and else statement

    public Exp getExpr() {
        return expr;
    }

    public IStmt getThenS() {
        return thenS;
    }

    public IStmt getElseS() {
        return elseS;
    }

    /*
     * method that prints the statement into a string
     */
    public String toString(){
        return "IF(" + expr.toString() +") THEN(" + thenS.toString() + ") ELSE(" + elseS.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws VariableException, InvalidInputException, DivisionByZeroExcep {
        IStackADT<IStmt> stk = state.getExeStack();
        IDictionaryADT<String, Integer> symTbl = state.getSymTable();
        IListADT<String> out = state.getOut();
        IHeapADT<Integer, Integer> hp = state.getHeap();

        if(getExpr().eval(symTbl,hp)!= 0) {
            stk.push(getThenS());
        }
        else
            stk.push(getElseS());

        return null;
    }

}
