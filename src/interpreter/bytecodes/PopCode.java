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
public class PopCode extends ByteCode{

    private int arg1;
    
    public void init(Vector<String> args) {
        
        arg1 = Integer.parseInt(args.firstElement());        
    }

    
    
    public void execute(VirtualMachine vm) {
        
        for(int i=0;i<arg1;i++){
            vm.popRunStack();
        }
        if(vm.printOn()){
            System.out.println("POP " + arg1);
        }
    }
   
    
}
