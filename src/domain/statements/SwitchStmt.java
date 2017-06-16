package domain.statements;

import domain.PrgState;
import domain.expressions.Exp;
import domain.expressions.VarExp;
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
public class SwitchStmt implements IStmt, Serializable {
    Exp var;
    Exp exp1;
    Exp exp2;
    IStmt st1;
    IStmt st2;
    IStmt st3;

    public SwitchStmt(Exp v, Exp e1, Exp e2, IStmt s1, IStmt s2, IStmt s3) {
        var = v;
        exp1 = e1;
        exp2 = e2;
        st1 = s1;
        st2 = s2;
        st3 = s3;
    }

    public Exp getExp1() {
        return exp1;
    }

    public Exp getExp2() {
        return exp2;
    }

    public IStmt getSt1() {
        return st1;
    }

    public IStmt getSt2() {
        return st2;
    }

    public IStmt getSt3() {
        return st3;
    }

    public Exp getVar() {
        return var;
    }

    public String toString() {
        // Switch (varname) case (expression1): stmt1 case (expression2): stmt2 default: stmt3
        return "switch (" + var.toString() + ") case (" + exp1.toString() + "): " + st1.toString() + " case (" + exp2.toString() + "): " +
                st2.toString() + " default: " + st3.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws VariableException, InvalidInputException, DivisionByZeroExcep {
        IStackADT<IStmt> stk = state.getExeStack();
        IDictionaryADT<String, Integer> symTbl = state.getSymTable();
        IListADT<String> out = state.getOut();
        IHeapADT<Integer, Integer> hp = state.getHeap();

        int res = getVar().eval(symTbl,hp) - getExp2().eval(symTbl,hp);
        if (res != 0) {
            if (getVar().eval(symTbl,hp) - getExp1().eval(symTbl,hp) != 0) {
                stk.push(getSt3());
            }
            else stk.push(getSt1());
        }
        else stk.push(getSt2());

        return null;
    }

}
