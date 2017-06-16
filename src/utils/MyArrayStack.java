package utils;

import domain.statements.IStmt;
import utils.exceptions.EmptyStackExcep;

/**
 * Created by Alexandra on 02/11/2015.
 */
public class MyArrayStack implements IStackADT<IStmt> {
    private static int capacity = 20;   // This is the total SIZE of the MyArrayStack
    //private int size;
    private IStmt[] items;   // Data type for the MyArrayStack
    private int top = 0;

    // constructor
    public MyArrayStack() {

        items = new IStmt[capacity];
    }

    public boolean isEmpty() {

        return (top == 0);
    }


    public void push(IStmt item) {
        if(top < capacity) {
            items[top] = item;
            top++;
        }
        else System.out.println("MyArrayStack is full!!");
    }

    public IStmt pop() throws EmptyStackExcep {
        if (!isEmpty()) {
            //IStmt temp = items[top];
            top--;
            return items[top];
        }
        else {
            throw new EmptyStackExcep("Stack is empty!!!");
        }
    }

    public IStmt peek() {
        if (top == 0) {
            System.out.println("MyArrayStack is empty");
        }
        return items[top];
    }

    @Override
    public Integer getSize() {
        return null;
    }


    /*
     * method that prints the structure into a string
     */
    public String toString(){
        String str = "{ ";
        for(int i = top-1;i >= 0; i--)
        {
            str += items[i].toString() + " | ";
        }

        str += "} \n";
        return str;

    }

    public String toStr() { return "";}
}


