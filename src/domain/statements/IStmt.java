package domain.statements;

import domain.PrgState;
import utils.exceptions.DivisionByZeroExcep;
import utils.exceptions.InvalidAddressExcep;
import utils.exceptions.InvalidInputException;
import utils.exceptions.VariableException;

import java.io.IOException;

/**
 * Created by Alexandra on 02/11/2015.
 */
public interface IStmt {
    String toString();
    PrgState execute(PrgState state) throws VariableException, InvalidInputException, DivisionByZeroExcep, InvalidAddressExcep, IOException, ClassNotFoundException;
}
