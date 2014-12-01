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
public class CallCode extends interpreter.bytecodes.CallCode {

    
    String functionName;
    int printSpace;
    
    
    public void init(Vector<String> args) {
        super.init(args);
        
        functionName = args.firstElement();
        
    }

   public void execute(VirtualMachine vm) {
        super.execute(vm);
        
        printSpace = vm.getRunStackSize();
        
        ((DVirtualMachine)vm).pushNewEvnRecord();
        
        if (((DVirtualMachine)vm).isTraceOn()){
            //todo: print the arg with identation according to envStack
            /*if(functionName.equals("Read") || functionName.equals("Write")){
            
            }
            else{*/
                    if(printSpace>1){
                        for( int j = 1; j < printSpace; j++){
                            System.out.print(" ");
                        }
                    }
                    
            for(int i = 0;  i < functionName.length();i++){
                if(functionName.charAt(i) != '<' && functionName.charAt(i) != '>' && !Character.isDigit( (functionName.charAt(i)) ) )
                {
                    System.out.print(functionName.charAt(i));
                }
            }
            
            System.out.print("(");
            vm.printCall();
            System.out.print(")");
            System.out.println();
            
        }
        //}
       
    }
    
}
