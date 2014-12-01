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
public class LoadCode extends ByteCode {

    int n;
    int arg1;
    String arg2;
    
    public void init(Vector<String> args) {
        
          arg1 = Integer.parseInt(args.firstElement());
          arg2 = args.lastElement();
    }

    
    @Override
    public void execute(VirtualMachine vm) {
        
        n = vm.loadRunStack(arg1);
        vm.pushRunStack(n);
        
        if(vm.takingDump()){
            System.out.println("LOAD " + arg1 + " "+ arg2 + "   <load "+ arg2 + ">");
        }else{
            if(vm.printOn()){
                System.out.println("LOAD " + arg1 +" " + arg2);
            }
        }
    }
}
