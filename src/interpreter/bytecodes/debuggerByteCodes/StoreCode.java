/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecodes.debuggerByteCodes;
import interpreter.VirtualMachine;
import interpreter.bytecodes.ByteCode;
import interpreter.debugger.DVirtualMachine;
import java.util.Vector;

/**
 *
 * @author Misha
 */
public class StoreCode extends interpreter.bytecodes.StoreCode {
    int arg1;
    String arg2;
    int elementAt;
    
    
    public void init(Vector<String> args){
        super.init(args);
        
        arg1 = Integer.parseInt(args.firstElement());
        arg2 = args.lastElement();
        
        
    }
    
    public void execute(VirtualMachine vm){
        super.execute(vm);
        //store ... also on formal code
        elementAt = vm.getElementAt(arg1);
        
        
        ((DVirtualMachine)vm).putIntoSymTable(arg2, elementAt);
        ((DVirtualMachine)vm).lastAddedSymbol(arg2);
    }
    
    
}
