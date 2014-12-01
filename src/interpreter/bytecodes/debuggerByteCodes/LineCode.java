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


//make abstract ? - no

public class LineCode extends ByteCode {

   int arg1;
   
    @Override
    public void init(Vector<String> args) {
        
        arg1 = Integer.parseInt(args.firstElement());
        
    }

    
    //change to DVirtualMachine - no
    
    public void execute(VirtualMachine vm) {
        
        ((DVirtualMachine)vm).toSetCurrentLine(arg1);
        
    }
}
