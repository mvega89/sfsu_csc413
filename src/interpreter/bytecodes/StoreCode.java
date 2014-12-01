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
public class StoreCode extends ByteCode{

    
    int arg1;
    String arg2;
    int popTopInt;
    int index;
    
    
    public void init(Vector<String> args) {
      arg1 = Integer.parseInt(args.firstElement()); 
      arg2 = args.lastElement();
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        
        index = vm.storeRunStack(arg1);
        popTopInt = vm.popRunStack();
        vm.add(index, popTopInt);
        
        /*
        if(!vm.printOn()){
            ((DVirtualMachine)vm).putIntoSymTable(arg2, popTopInt);
            ((DVirtualMachine)vm).lastAddedSymbol(arg2);
        }
        */
        
        if(vm.takingDump()){
            System.out.println("STORE " + arg1 + " "+ arg2 + "   "+ arg2 + " " + "=" + " " + popTopInt);
        }else{
            if(vm.printOn()){
                System.out.println("STORE " + arg1 +" " + arg2);
            }
        }
        
    }

   
    
}
