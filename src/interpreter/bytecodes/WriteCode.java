/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecodes;

import interpreter.VirtualMachine;
import interpreter.debugger.DVirtualMachine;
import java.util.Vector;


/**
 *
 * @author Misha
 */
public class WriteCode extends ByteCode {

    
    public void init(Vector<String> args) {
        
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        
        if(vm.printOn()){
            
            System.out.println(vm.peekRunStack());//need to print this when trace is on
            
            System.out.println("WRITE");
        }
        else{
            if( ((DVirtualMachine)vm).isTraceOn()){
                System.out.println("exit: Write: " + vm.peekRunStack());
                System.out.println();
            }
        }
        
    }
}
