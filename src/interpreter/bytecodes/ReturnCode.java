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
public class ReturnCode extends ByteCode{

    
    String labelName;
    boolean oneArg = false;
    
    public void init(Vector<String> args){
        
        
        if(args.size()==1){
            labelName = args.firstElement();
            oneArg = true;
        }
    }

    
    public void execute(VirtualMachine vm) {
        vm.popFrame();
        if(oneArg){
            if(vm.printOn()){
                System.out.print("RETURN " + labelName);
                if(vm.takingDump()){
                    System.out.print("   exit ");
                    for(int i = 0;  i < labelName.length();i++){
                        if(labelName.charAt(i) != '<' && labelName.charAt(i) != '>' && !Character.isDigit( (labelName.charAt(i)) ) )
                        {
                            System.out.print(labelName.charAt(i));
                        }
                    }
                    
                    System.out.print(": " + vm.peekRunStack());
                }
                System.out.println();
            }
            
        }
        
        else{
            if(vm.printOn()){
                System.out.println("RETURN");
            }
         
            if(!vm.printOn() && !((DVirtualMachine)vm).isTraceOn() ){
                System.out.println(vm.peekRunStack());
                System.out.println();
            }
            
            if(!vm.printOn() && ((DVirtualMachine)vm).isTraceOn() ){
                System.out.println(vm.peekRunStack());
                
            }
            
        }
    }

    
}
