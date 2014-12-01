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
public class LitCode extends interpreter.bytecodes.LitCode {

    int arg1;
    String arg2;
    boolean twoArgs = false;
    
    @Override
    public void init(Vector<String> args) {
        super.init(args);
        
        arg1 = Integer.parseInt(args.firstElement());
        arg2 = args.lastElement();
        
        if(args.size()==2){
            twoArgs = true;
        }
        
        
        
    }

    
    public void execute(VirtualMachine vm) {
        super.execute(vm);
        
        
        
        if(twoArgs){
        ((DVirtualMachine)vm).putIntoSymTable(arg2, arg1);
        ((DVirtualMachine)vm).lastAddedSymbol(arg2);
        }
        
        
    
    }
}
