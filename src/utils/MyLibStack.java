package utils;

import utils.exceptions.EmptyStackExcep;

import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by Alexandra on 07/11/2015.
 */
public class MyLibStack<T> implements IStackADT<T>, Serializable {
    private Stack<T> stack = new Stack<>();


    public boolean isEmpty() {
        return stack.empty();
    }

    public void push(T item) {
        stack.push(item);
    }

    /**
     * Pops the top item off of this stack and return it.
     * @return the top item on the stack
     * @throws Exception if the stack is empty
     */
    public T pop() throws EmptyStackExcep{
        if (stack.empty()) {
            throw new EmptyStackExcep("Attempted to pop from an empty stack!!");
        }
        return stack.pop();
    }

    /**
     * Returns the top item off of this stack without removing it.
     * @return the top item on the stack
     * @throws Exception if the stack is empty
     */
    public T peek() throws EmptyStackExcep{
        if (stack.empty()) {
            throw new EmptyStackExcep("Attempted to peek into an empty stack!!");
        }
        return stack.peek();
    }

    public Integer getSize() {
        return stack.size();
    }

    /*
    * method that prints the structure into a string
    */
    public String toString() {
        String str = "";
        for (int i = stack.size() - 1; i >= 0; i--) {
            str += stack.get(i).toString() + "\n ";
        }
        str += "\n";
        return str;
    }

}
