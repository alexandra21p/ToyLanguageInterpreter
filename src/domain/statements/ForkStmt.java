package domain.statements;

import domain.PrgState;
import utils.*;
import utils.exceptions.DivisionByZeroExcep;
import utils.exceptions.InvalidAddressExcep;
import utils.exceptions.InvalidInputException;
import utils.exceptions.VariableException;
import java.io.*;

/**
 * Created by Alexandra on 16/01/2016.
 */
public class ForkStmt implements IStmt, Serializable {
    IStmt st;

    public ForkStmt(IStmt s) {
        st = s;
    }

    @Override
    public PrgState execute(PrgState state) throws VariableException, InvalidInputException, DivisionByZeroExcep, InvalidAddressExcep, IOException, ClassNotFoundException {
        IDictionaryADT<String, Integer> symTbl = state.getSymTable();
        IListADT<String> out = state.getOut();
        IHeapADT<Integer, Integer> hp = state.getHeap();
        Integer id = state.getId();
        IDictionaryADT symTbl2;
        IStackADT<IStmt> stk2 = new MyLibStack<>();
        ILockTableADT<Integer,Integer> lt = new LockTable<>();
        stk2.push(st);

        // copy the symTable by serializing
        FileOutputStream fileOut = new FileOutputStream("clone.ser");
        ObjectOutputStream o = new ObjectOutputStream(fileOut);
        o.writeObject(symTbl);
        o.close();
        fileOut.close();

        FileInputStream fileIn = new FileInputStream("clone.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        symTbl2 = (IDictionaryADT)in.readObject();
        in.close();
        fileIn.close();

        return new PrgState(id*10, stk2, symTbl2, out, hp, lt);
    }


    /*
     * method that prints the statement into a string
    */
    @Override
    public String toString() {
        return "fork(" + st.toString() + ")";
    }
}
