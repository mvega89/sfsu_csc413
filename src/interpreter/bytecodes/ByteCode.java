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
public abstract class ByteCode{
    //abstract methods

    //
    /**
     *
     * @param args
     */
    public abstract void init(Vector<String> args);
     
    //virtual machine will call back
    public abstract void execute(VirtualMachine vm); 
    
   
}
