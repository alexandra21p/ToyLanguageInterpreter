package domain.statements;

import domain.PrgState;
import utils.IDictionaryADT;
import utils.IHeapADT;
import utils.ILockTableADT;
import utils.IStackADT;
import utils.exceptions.DivisionByZeroExcep;
import utils.exceptions.InvalidInputException;
import utils.exceptions.VariableException;

import java.io.Serializable;

/**
 * Created by Alexandra on 19/01/2016.
 */
public class LockStmt implements IStmt, Serializable {
    String var;

    public LockStmt(String v) {
        var = v;
    }

    @Override
    public PrgState execute(PrgState state) throws VariableException, InvalidInputException, DivisionByZeroExcep {
        IStackADT<IStmt> stk = state.getExeStack();
        IDictionaryADT<String, Integer> symTbl = state.getSymTable();
        //IListADT<String> out = state.getOut();
        IHeapADT<Integer, Integer> hp = state.getHeap();
        ILockTableADT<Integer, Integer> lt = state.getLockTable();
        Integer id = state.getId();
        int foundIndex;
        if (!symTbl.isDefined(var)) {
            System.out.println("Error. The index was not found!");
            return null;
        }
        else {
            foundIndex = symTbl.lookUp(var);
        }

        if (!lt.isDefined(foundIndex)) {
            System.out.println("Error. The index was not found!");
            return null;
        }
        else if (lt.lookUp(foundIndex) == -1) {
            int res = lt.lookUp(foundIndex);
            lt.updateValue(res,id);
        }
        else  {
            //push back the lock statement(that means other PrgState holds the lock)
            stk.push(this);
        }
        return null;

    }


    public String toString() {
        return "lock(" + var + ") ";
    }
}
