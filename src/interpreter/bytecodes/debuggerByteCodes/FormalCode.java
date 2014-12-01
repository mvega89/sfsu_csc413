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
//added abstract  ?
public class FormalCode extends ByteCode {
    
    String arg;
    int arg2;

    @Override
    public void init(Vector<String> args) {
        
        arg = args.firstElement();
        arg2 = Integer.parseInt(args.lastElement());
        
    }

    
    public void execute(VirtualMachine vm) {
        
        ((DVirtualMachine)vm).putIntoSymTable(arg, vm.getElementAt(arg2));
        ((DVirtualMachine)vm).lastAddedSymbol(arg);
        
    }
    
}
