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
public class LitCode extends ByteCode{
    
    private int arg1;
    private String arg2;
    boolean twoArgs  = false;
    
    public LitCode(){
    }
    
    @Override
    public void init(Vector<String> args) {
        
        arg1 = Integer.parseInt(args.firstElement());
        arg2 = args.lastElement();
        if(args.size()==2){
            twoArgs = true;
        }
    }
    //if a char is included in argument,need to save arg1 to char variable
    //and have access to it for LOAD
    public void execute(VirtualMachine vm) {
        
        vm.pushRunStack(arg1);
        
        if(vm.takingDump()){
            if(twoArgs){
                System.out.println("LIT " + arg1 + " " + arg2 + "   int " + arg2);
            }else{
                System.out.println("LIT " + arg1 + "   int " + arg1);
            }
        }else{
            if(twoArgs){
                if(vm.printOn()){
                    System.out.println("LIT " + arg1 + " " + arg2);
                }
            }else{
                if(vm.printOn()){
                    System.out.println("LIT " + arg1);
                }
            }
            
        }
        
    }
    
}