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
public class FalseBranchCode extends ByteCode {

    
    String labelName;
    @Override
    public void init(Vector<String> args) {
        labelName = args.firstElement();
    }

    
    @Override
    public void execute(VirtualMachine vm) {
        if(vm.printOn()){
        System.out.print("FALSEBRANCH ");
        }
       
    }

    public String getLabelName(){
        return labelName;
    }

    
}
