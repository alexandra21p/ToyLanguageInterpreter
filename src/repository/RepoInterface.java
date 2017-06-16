package repository;

import domain.PrgState;

import java.util.List;

/**
 * Created by Alexandra on 02/11/2015.
 */
public interface RepoInterface {
    void savePrg();
    void setPrgList(List<PrgState> repl);
    List<PrgState> getPrgList();
    void serializare();
    void deserializare();
    void saveToFile(PrgState state);
}
