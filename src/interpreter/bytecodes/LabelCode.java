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
public class LabelCode extends ByteCode {

    String arg;
    
    public void init(Vector<String> args) {
        arg = args.firstElement();
    }

    
    public void execute(VirtualMachine vm) {
        if(vm.printOn()){
            System.out.println("LABEL " + arg);
        }
    }

    
    
}
