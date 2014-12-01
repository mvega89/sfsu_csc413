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
public class PopCode extends interpreter.bytecodes.PopCode {

    int arg1;
    
    public void init(Vector<String> args) {
        super.init(args);
        
        arg1 = Integer.parseInt(args.firstElement());   
        
    }

 
    public void execute(VirtualMachine vm) {
        super.execute(vm);
        
        for(int i=0;i<arg1;i++){
            ((DVirtualMachine)vm).popSymbolTable();
        } 
        
    }
    
}
