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
public class ReturnCode extends interpreter.bytecodes.ReturnCode {

    String functionName;
    boolean oneArg = false;
    int printSpace;
    
    
    @Override
    public void init(Vector<String> args) {
        super.init(args);
        
        if(args.size()==1){
            functionName = args.firstElement();
            oneArg = true;
        }
        
    }

    @Override
    public void execute(VirtualMachine vm) {
        super.execute(vm);
        ((DVirtualMachine)vm).popTopEvnRecord();
        printSpace = vm.getRunStackSize();
        
        if(((DVirtualMachine)vm).isTraceOn()){
            
            if(oneArg){
                
                    if(printSpace>1){
                    for( int j = 1; j < printSpace; j++){
                        System.out.print(" ");
                    }
                
                }
                    System.out.print("exit: ");
                    for(int i = 0;  i < functionName.length();i++){
                        if(functionName.charAt(i) != '<' && functionName.charAt(i) != '>' && !Character.isDigit( (functionName.charAt(i)) ) )
                        {
                            System.out.print(functionName.charAt(i));
                        }
                    }
                    
                    System.out.println(": " + vm.peekRunStack());
                
            
            }
        }
    }
    
}
