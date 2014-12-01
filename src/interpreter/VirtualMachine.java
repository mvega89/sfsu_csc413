/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import interpreter.bytecodes.CallCode;
import interpreter.bytecodes.ByteCode;
import interpreter.bytecodes.GotoCode;
import interpreter.bytecodes.ReturnCode;
import interpreter.bytecodes.FalseBranchCode;
import interpreter.debugger.DVirtualMachine;
import java.util.Stack;


/**
 *
 * @author Misha
 */
public class VirtualMachine {
    
    
    //all public for access in DVM
    public RunTimeStack runStack;
    public int pc;
    public Stack<Integer> returnAddrs;
    public boolean isRunning, doDump;
    public Program program;
    
    
    
    public VirtualMachine(Program program1) {
        program = program1;
        
        pc = 0;
        runStack = new RunTimeStack();
        
        returnAddrs = new Stack<Integer>();
        
    }
    
    
    public void executeProgram() {
        
        
        isRunning = true;
        doDump = false;
        
        
        while(isRunning)
        {
            ByteCode code = program.getCode(pc);
            
            if(doDump){
                
                runStack.dump();
            }
            //catch breakpoint
            executeCode(code);
            
        }
    }
    //added for debugger
    public int getElementAt(int offset){return runStack.elementAt(offset);}
    //public int storeGetElementAt(int offset){return runStack.storeElementAt(offset);}
    public int getRunStackSize(){return runStack.getSize();}        
    public int pushRunStack(int i){return runStack.push(i);}
    public int popRunStack(){return runStack.pop();}
    public int peekRunStack(){return runStack.peek();}
    public int peekFramePointers(){return runStack.framePointers.lastElement() ;}
    public void newFrameAt(int offset){runStack.newFrameAt(offset);}
    public void popFrame(){runStack.popFrame();}
    public int storeRunStack(int offset){return runStack.store(offset);}
    public void add(int index, int var){runStack.add(index, var);}
    public int loadRunStack(int offset){return runStack.load(offset);}
    public Integer pushRunStack(Integer i){return runStack.push(i);}
    public boolean halt(){return isRunning = false;}
    //dump methods
    public boolean takeDump(){return doDump = true;}
    public boolean doNotTakeDump(){return doDump = false;}
    public boolean takingDump(){return doDump;}
    //flag for -d arg
    public boolean printOn() {return true;}
    
    public RunTimeStack printTest(){ return runStack;}
    public void printCall(){runStack.printForCall();}
    
    public void executeCode(ByteCode code) {
        
        if(code instanceof GotoCode){
            String labelName = ((GotoCode)code).getLabelName();
            pc = program.resolveAddress(labelName);
            code.execute(this);
            if(printOn()){
                System.out.println(pc);
            }
        }
        else if(code instanceof FalseBranchCode){
            if(popRunStack() == 0){
                String labelName = ((FalseBranchCode)code).getLabelName();
                
                pc = program.resolveAddress(labelName);
                code.execute(this);
                if(printOn()){
                    System.out.println(pc);
                }
                
            }else{
                String labelName = ((FalseBranchCode)code).getLabelName();
                int pc1 = program.resolveAddress(labelName);
                code.execute(this);
                if(printOn()){
                    System.out.println(pc1);
                }
                pc ++;
            }
        }
        
        else if(code instanceof CallCode){
            String labelName = ((CallCode)code).getLabelName();
            
            returnAddrs.push(pc+1);
            pc = program.resolveAddress(labelName);
            
            code.execute(this);
            
        }
        
        else if(code instanceof ReturnCode){
            code.execute(this);
            pc = returnAddrs.pop();
        }
        
        else{
            
            code.execute(this);
            pc++;
        }
    }
    
}


