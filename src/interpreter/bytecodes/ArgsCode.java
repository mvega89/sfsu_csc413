/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecodes;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author Misha
 */
public class ArgsCode extends ByteCode{

    int arg1;
    
    @Override
    public void init(Vector<String> args) {
        
        arg1 = Integer.parseInt(args.firstElement());
       
    }
    
    @Override
    public void execute(VirtualMachine vm) {
         vm.newFrameAt(arg1);
                 if(vm.printOn()) {
         System.out.println("ARGS " + arg1);
                 }
    }

    
    
}
