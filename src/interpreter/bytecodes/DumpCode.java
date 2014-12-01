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
public class DumpCode extends ByteCode {

    String setDump;
    
    public void init(Vector<String> args) {
        
        setDump = args.firstElement();
       
    }

    
    @Override
    public void execute(VirtualMachine vm) {
       if("ON".equals(setDump)){
           vm.takeDump();
       }else{
           vm.doNotTakeDump();
       }
      
       if(vm.printOn()){
       System.out.println("DUMP " + setDump);
       }
    }

    
}
