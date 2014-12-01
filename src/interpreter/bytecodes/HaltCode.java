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
public class HaltCode extends ByteCode{
    
    

   
    public void init(Vector<String> args) {
         //To change body of generated methods, choose Tools | Templates.
    }

    
    public void execute(VirtualMachine vm) {
        if(vm.printOn()){
            System.out.println("HALT");
        }
        vm.halt();
    }
    
 
    
}
