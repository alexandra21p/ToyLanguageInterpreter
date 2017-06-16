package domain;

import domain.statements.IStmt;
import utils.*;
import utils.exceptions.*;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Alexandra on 02/11/2015.
 */

public class PrgState implements Serializable {
    Integer id;
    IStackADT<IStmt> exeStack; // execution stack
    IDictionaryADT<String, Integer> symTable; // symbol table
    IListADT<String> out; // output list
    IHeapADT<Integer, Integer> heap; // the heap
    ILockTableADT<Integer, Integer> lockTbl;
    //IStmt originalProgram; // optional

    // constructor for program state
    public PrgState(Integer i, IStackADT<IStmt> stk, IDictionaryADT<String, Integer> symtbl, IListADT<String> ot, IHeapADT<Integer, Integer> h,  ILockTableADT<Integer, Integer> lockT) {
        id = i;
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        heap = h;
        lockTbl = lockT;
        //originalProgram = prg;
        //stk.push(prg);
    }


    /*
    * method that implements the one step execution of a program
    * using the statement evaluation rules
    */
    public PrgState oneStep() throws EmptyStackExcep, VariableException, EmptyListException, DivisionByZeroExcep, InvalidInputException, InvalidAddressExcep, IOException, ClassNotFoundException {

        if (exeStack.isEmpty()) {
            throw new EmptyStackExcep("The execution stack is empty!!");
        }
        IStmt crtStmt = exeStack.pop(); // pop the statement to be evaluated from the stack
        return crtStmt.execute(this);
    }


    /*
       method that returns true when the exeStack is not empty and false otherwise
    */
    public Boolean isNotCompleted() {
        if(exeStack.getSize() == 0)
            return false;
        else
            return true;
    }



    // getters for exe stack, symbol table and output list
    public IStackADT<IStmt> getExeStack() {
        return exeStack;
    }

    public IDictionaryADT<String, Integer> getSymTable() {
        return symTable;
    }

    public IListADT<String> getOut() {
        return out;
    }

    public IHeapADT<Integer, Integer> getHeap() {
        return heap;
    }

    public Integer getId() {
        return id;
    }

    public ILockTableADT<Integer, Integer> getLockTable() { return lockTbl;}


    // setters for exe stack, symbol table and output list
    public void setExeStack(IStackADT<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymTable(IDictionaryADT<String, Integer> symTable) {
        this.symTable = symTable;
    }

    public void setOut(IListADT<String> out) {
        this.out = out;
    }

    public void setHeap(IHeapADT<Integer, Integer> heap) {
        this.heap = heap;
    }



    /*
         * method that prints the state of the program, with execution stack, symbol table, heap
         * and output list into a string
         * returns the result, str, of type String
         */
    public String toString() {
        String str = "[\t PROGRAM STATE\t  ] ";
        str += "\n ----------------------- \n";
        str += "\tProgram ID: " + Integer.toString(id);
        str += "\n ----------------------- \n";
        str += "\tExecution Stack\t ";
        str += "\n ----------------------- \n";
        str += exeStack.toString();
        str += "\n ----------------------- \n";
        str += "\tTable of Symbols\t ";
        str += "\n ----------------------- \n";
        str += symTable.toString();
        str += "\n ----------------------- \n";
        str += "\tOutput\t ";
        str += "\n ----------------------- \n";
        str += out.toString();
        str += "\n ----------------------- \n";
        str += "\tHeap\t ";
        str += "\n ----------------------- \n";
        str += heap.toString();
        str += "\tLock Table\t ";
        str += "\n ----------------------- \n";
        str += lockTbl.toString();


        return str;
    }



    /*
    // string representation for the saveToFile method
    public String toStr() {
        String str = "Program State: \n";
        str += "Program ID: " + id +"\n";
        str +=  "ExeStack: \n";
        str +=  exeStack.toString() + "\n";
        str += "\nSymTable: \n";
        str += symTable.toString() + "\n";
        str += "\nOut: \n";
        str += out.toString() + "\n";
        str += "\nHeap: \n";
        str += heap.toString() + "\n";

        return str;
     }
     */

}
