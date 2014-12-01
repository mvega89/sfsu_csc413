/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecodes;

import interpreter.VirtualMachine;
import interpreter.debugger.DVirtualMachine;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Misha
 */
public class ReadCode extends ByteCode {
    
    int arg1;
    String line;
    public void init(Vector<String> args) {
        
    }
    
    
    
    public void execute(VirtualMachine vm) {
        
        String line = JOptionPane.showInputDialog("Please input an integer");
        
        if(!vm.printOn() && !((DVirtualMachine)vm).isTraceOn()){
            System.out.print("Inputed integer: ");
            //System.out.println();
        } 
        
        if(!vm.printOn() && ((DVirtualMachine)vm).isTraceOn()){
            System.out.println("Inputed integer: " + line);
            System.out.print("exit: Read: ");
        }
            
        try{
            arg1 = Integer.parseInt(line);
        } catch (Exception e){
            return;
        }
        
        vm.pushRunStack(arg1);
        
        if(vm.printOn()){
            System.out.println("READ");
        }
        
    }
    
}
