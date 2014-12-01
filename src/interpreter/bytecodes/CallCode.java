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
public class CallCode extends ByteCode {

    
    String labelName;
    
        
       
        
        
    
    
    @Override
    public void init(Vector<String> args) {
        labelName = args.firstElement();
    }

    
    @Override
    public void execute(VirtualMachine vm) {
        if(vm.takingDump()){
            //char [] arraytest = labelName.toCharArray();
            System.out.print("CALL " + labelName + "   ");
            
            for(int i = 0;  i < labelName.length();i++){
                if(labelName.charAt(i) != '<' && labelName.charAt(i) != '>' && !Character.isDigit( (labelName.charAt(i)) ) )
                {
                    System.out.print(labelName.charAt(i));
                }
            }
            
            System.out.print("(");
            vm.printCall();
            System.out.print(")");
            System.out.println();
        }
        else if(vm.printOn()) {
            System.out.println("CALL " + labelName);
        }
        
    }

    public String getLabelName(){
        return labelName;
    }

    
    
}
