/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecodes.debuggerByteCodes;

import interpreter.VirtualMachine;
import interpreter.debugger.DVirtualMachine;
import interpreter.bytecodes.ByteCode;
import java.util.Vector;

/**
 *
 * @author Misha
 */

//added abstract ?
public class FunctionCode extends ByteCode {
    
    String arg1;
    int arg2;
    int arg3;
           

    
    public void init(Vector<String> args) {
        arg1 = args.firstElement();
        arg2 = Integer.parseInt(args.get(1));
        arg3 = Integer.parseInt(args.lastElement());
        
    }

 
    //original was VirtualMachine vm ?
    public void execute(VirtualMachine vm) {
        
      ((DVirtualMachine)vm).setEvnRecordFunction(arg1, arg2, arg3);
      //((DVirtualMachine)vm).lastAddedSymbol(arg1);
      
      //implement dump 
      
    }
    
    
    
}
