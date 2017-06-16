package domain.expressions;

import utils.IDictionaryADT;
import utils.IHeapADT;
import utils.exceptions.VariableException;

import java.util.Scanner;

/**
 * Created by Alexandra on 06/11/2015.
 */
public class ReadExpr extends Exp {
    Scanner scan;

    public ReadExpr() {
        scan = new Scanner(System.in);
    }

    @Override
    public Integer eval(IDictionaryADT<String,Integer> table, IHeapADT<Integer,Integer> heap) throws VariableException {
        System.out.println("\n...Introduces an integer for ToyLanguage: ");
        int input = scan.nextInt();
        return input;
    }

    public String toString() {
        return " read()";
    }
}
