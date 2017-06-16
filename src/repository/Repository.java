package repository;

import domain.PrgState;
import domain.statements.IStmt;
import utils.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alexandra on 02/11/2015.
 */

public class Repository implements RepoInterface {
    List<PrgState> prgs;
    //int size;
    private static String fileName;

    // constructor
    public Repository() {
        fileName = "programState.ser";
        prgs = new ArrayList<PrgState>();

        IStackADT<IStmt> stk = new MyLibStack<>();
        IDictionaryADT<String, Integer> symtbl = new MyLibDictionary<>();
        IListADT<String> out = new MyLibList<>();
        IHeapADT<Integer, Integer> hp = new MyLibHeap<>();
        ILockTableADT<Integer, Integer> lt = new LockTable<>();
        PrgState crtPrgState = new PrgState(1, stk, symtbl, out, hp, lt);

        prgs.add(crtPrgState);
    }


    /*
     * adds a PrgState object into the list, initializing the exe stack, symbol table and the output list
     */
    public void savePrg() {
        //initializing the PrgState objects
        /*
        IStackADT<IStmt> stk = new MyArrayStack();
        IDictionaryADT<String, Integer> symtbl = new MyArrayDictionary();
        IListADT<String> out = new MyArrayList();
        */
        IStackADT<IStmt> stk = new MyLibStack<>();
        IDictionaryADT<String, Integer> symtbl = new MyLibDictionary<>();
        IListADT<String> out = new MyLibList<>();
        IHeapADT<Integer, Integer> hp = new MyLibHeap<>();
        ILockTableADT<Integer, Integer> lt = new LockTable<>();
        PrgState crtPrgState = new PrgState(1, stk, symtbl, out, hp, lt);

        prgs.add(crtPrgState);
    }


    /*
        method that returns the list of the program states
     */
    public List<PrgState> getPrgList() {
        return prgs;
    }

    /*
        replaces the existing list of program states from the repository with one given as parameter in this method
     */
    public void setPrgList(List<PrgState> repl) {
        prgs = repl;
    }

    /*
     * method that serializes the PrgState object to a file
     */
    public void serializare() {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            List<PrgState> p = this.getPrgList();
            out.writeObject(p);

        } catch (IOException e) {
            System.err.println("Error: " + e);
        } finally {
            if (out!=null)
                try {
                    out.close();
                } catch (IOException e) {
                    System.err.println("Error: " + e);
                }
        }
    }

    /*
     * method that deserializes the PrgState object
     */
    @Override
    public void deserializare() {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(fileName));
            List<PrgState> p = (List<PrgState>)in.readObject();
            prgs = p;



        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
        catch (ClassNotFoundException e) {
            System.err.println("Deserialization error: " + e);
        }finally {
            if (in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    System.err.println("Error: " + e);
                }

            }
        }
    }


    public void saveToFile(PrgState state) {

        Integer id = state.getId();
        IStackADT<IStmt> stk = state.getExeStack();
        IDictionaryADT<String, Integer> symtbl = state.getSymTable();
        IListADT<String> out = state.getOut();
        IHeapADT<Integer, Integer> hp = state.getHeap();
        ILockTableADT<Integer, Integer> lt = state.getLockTable();


        try {

            //String content = this.getCrtPrg().toStr();

            File file = new File("myfile.txt");

            // if file doesn't exist, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Program State: ");
            bw.newLine();

            bw.write("Program ID: ");
            bw.write(Integer.toString(id));
            bw.newLine();

            bw.write("ExeStack: ");
            bw.newLine();

            bw.write(stk.toString());
            bw.newLine();

            bw.write("SymTable: ");
            bw.newLine();

            bw.write(symtbl.toString());
            bw.newLine();

            bw.write("Out: ");
            bw.newLine();

            bw.write(out.toString());
            bw.newLine();

            bw.write("Heap: ");
            bw.newLine();

            bw.write(hp.toString());
            bw.newLine();

            bw.write("Lock Table: ");
            bw.newLine();

            bw.write(lt.toString());
            bw.newLine();

            bw.close();

            System.out.println("Contents of the current program state have been saved to file.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String toString(){
        String result = "";

        Iterator<PrgState> it = prgs.iterator();
        while (it.hasNext()) {
            result = result + it.next().toString() + "\n";
        }
        return result;
    }


}
