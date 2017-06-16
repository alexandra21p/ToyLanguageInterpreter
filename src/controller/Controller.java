package controller;

import domain.PrgState;
import domain.expressions.*;
import domain.statements.*;
import repository.RepoInterface;
import utils.IDictionaryADT;
import utils.IListADT;
import utils.IStackADT;
import utils.exceptions.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by Alexandra on 02/11/2015.
 */
public class Controller {
    RepoInterface repo;
    String flag;

    // constructor
    public Controller(RepoInterface repo) {
        this.repo = repo;
        flag = "console";
        //repo.savePrg();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String m) {
        flag = m;
    }


    /*
     * method that adds a new statement into the execution stack of the current program state
     * pre: statement s - IStmt, state - PrgState (the current prog state)
     * post: statement s is added to the stack
     */
    public void addStatement(IStmt s) throws Exception {
        PrgState state = repo.getPrgList().get(0); // returns the current prog state
        IStackADT<IStmt> stack = state.getExeStack();
        stack.push(s);
    }


    /*
        method that takes a list of PrgState as input , removes all PrgState for which isNotCompleted
         returns false and then returns as result a list where all PrgState are not completed
     */
    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }


    public void printProgramState(List<PrgState> pr) throws IOException
    {

        if (flag.equals("file"))
        {
            pr.forEach(repo::saveToFile);
            System.out.println("State saved to file. \n");


        }
        if (flag.equals("console")) {
            System.out.println(repo.toString());

        }


    }


    /*
        method which executes one step for each existing PrgState (namely each thread)
     */
    public void oneStepForAllPrg(List<PrgState> prgList) throws Exception {

        printProgramState(prgList);

        //prgList.forEach(p -> System.out.println(p.toString()));

        ExecutorService executor;
        executor = new ForkJoinPool();

        try {
        List<Callable<PrgState>> callList = prgList.stream()
                        .map(                                //cast
                                (PrgState program) -> (Callable<PrgState>)(
                                        () -> {
                                            try{
                                                return program.oneStep();
                                            } catch (Exception e) {
                                                return (null);
                                            }
                                        }
                                )
                        )
                        .collect(Collectors.toList());


        List<PrgState> newProgramList = executor.invokeAll(callList).stream()
                .map(
                        (list) -> {
                            try {
                                return list.get();
                            } catch (Exception e) {
                                return (null);
                            }
                        }
                )
                .filter(program -> program != null)
                .collect(Collectors.toList());

        //add the new created threads to the list of existing threads
        prgList.addAll(newProgramList);

            printProgramState(prgList);
            this.repo.setPrgList(prgList);


        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }



        /*
        List<Callable<PrgState>> callList = prgList.stream()
                .map(p -> ((Callable<PrgState>) p::oneStep))
                .collect(Collectors.toList());

        ExecutorService executor = new ForkJoinPool();
        List<PrgState> newPrgList =
                executor.invokeAll(callList). stream()

                        . map(future -> {
                            PrgState state = null;
                            try {
                                state = future.get();
                            } catch (InterruptedException | ExecutionException e) {
                                System.err.println(e.getMessage());
                                e.printStackTrace();
                            }
                            return state;
                        })
                        .filter(p -> p != null)
                        .collect(Collectors.toList());
        executor.shutdown();


        prgList.addAll(newPrgList);
        repo.setPrgList(prgList);
    */

    }
    /*
     * method that implements the complete evaluation
     */
    public void allStep() throws Exception {
        while(true){
            //remove the completed programs
            List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
            if (prgList.size() == 0)
                return; //complete the execution of all threads
            else {
                    oneStepForAllPrg(prgList);

            }
        }
    }


    public void serializareObj() {
        repo.serializare();
    }

    public void deserializareObj() {
        repo.deserializare();
    }

    public void writeToFile(PrgState prog) {
       repo.saveToFile(prog);
   }


    public List<PrgState> getPrograms() {
        return repo.getPrgList();
    }

    /*
     * method that prints the current program state into a string
     * returns a string with the representation
     */
    public String toString(){
        return repo.toString();
    }


}


