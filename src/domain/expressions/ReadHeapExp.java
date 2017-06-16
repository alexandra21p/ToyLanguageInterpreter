package domain.expressions;

import utils.IDictionaryADT;
import utils.IHeapADT;
import utils.exceptions.VariableException;

/**
 * Created by Alexandra on 06/12/2015.
 */
public class ReadHeapExp extends Exp {
    String varname;

    public ReadHeapExp(String v) {
        varname = v;
    }

    public Integer eval(IDictionaryADT<String,Integer> table, IHeapADT<Integer,Integer> heap) throws VariableException {
        int heap_addr = table.lookUp(varname);
        int content = heap.lookUp(heap_addr);
        return content;

    }

    public String toString() {
        return "rH(" + varname + ")";
    }
}
