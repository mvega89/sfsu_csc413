/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author Misha
 */
public class RunTimeStack {
    
    Vector<Integer> runStack;
    Stack<Integer> framePointers;
    
    public RunTimeStack() {
        runStack = new Vector();
        framePointers = new Stack();
        framePointers.add(0);
        
    }
    
    /**
     * add method for STORE
     * @param index, Integer
     */
    public void add(int index,int var){
        runStack.remove(index);
        runStack.add(index, var);
    }
    
    
    public int getSize(){return runStack.size();}
    
    /**
     *
     * @param i
     * @return
     */
    public void printForCall(){
       for(int i = framePointers.lastElement() ;i <= runStack.size()-1 ; i++){
           System.out.print(runStack.elementAt(i));
           if(i<runStack.size()-1){
              System.out.print(",");
           }
        
    }
    }    
    
    
    public void dump() {
        
        int closeBrace = runStack.size();
        int frameNum = 1;
        
        if(framePointers.size() > 1){
            closeBrace = framePointers.get(1);
            frameNum = 2;
        }
        
        System.out.print("[");
        
        if(runStack.isEmpty()){
            System.out.print("]");
            System.out.println();
            
            return;
        }
        
        
        for(int i=0;i<runStack.size();i++){
            System.out.print(runStack.elementAt(i));
            if(i+1==closeBrace | i == closeBrace){
                System.out.print("]");
                
                if(i+1 < runStack.size() ){
                    System.out.print("[");
                    
                    if(framePointers.size() > frameNum){
                        closeBrace = framePointers.get(frameNum++);
                    }else{
                        closeBrace = runStack.size();
                    }
                }
            }else{
                System.out.print(",");
            }
        }
        System.out.println();
        
        
    }
    
    //for debugger 
    public int elementAt(int offset){
        return runStack.elementAt(offset);
       
    }
    
    /**
     *
     * @return the top item on the runtime stack
     */
    public int peek(){//catch if vector is empty
        
        return runStack.elementAt(runStack.size()-1);
    }
    
    /**
     *pop the top item from the runtime stack
     * @return that item
     */
    public int pop(){//catch if vector is empty
        int i = runStack.get(runStack.size()-1);
        runStack.remove(runStack.size()-1);
        return i;
    }
    
    /**
     *i - push this item on the runtime stack
     * @return that item just pushed
     */
    public int push(int i){
        
        runStack.add(i);
        return i;
    }
    
    /**
     *start new frame
     *
     * @param offset - indicates the number of slots down from the top of
     * RunTimeStack for starting the new frame
     *
     */
    public void newFrameAt(int offset){
        
        
        framePointers.push(runStack.size()-offset);
        
    }
    /**
     * We pop the top frame when we return from a function; before popping, the
     * functions return value is at the top of the stack so we'll save the value,
     * pop the top frame and then push the return value
     */
    public void popFrame(){
        //save the value
        int i = runStack.lastElement();
        
        //pop top frame
        int j = framePointers.pop();
        for(int k = runStack.size()-1;k >= j;k--){
            runStack.remove(k);
        }
        //push return value
        runStack.add(i);
        
    }
    /**
     * Used to store into variables
     *
     * @param offset
     * @return
     */
    public int store(int offset){
        //has to be offset + top number of framePointers; number added is the number
        //saved to the char argument from LIT
        
        
        int i = framePointers.lastElement() + offset;
        
        
        return i;
    }
    
    /**
     * Used to load variables onto the stack
     * @param offset
     * @return
     */
    public int load(int offset){
        //has to be offset + top number of framePointers; number added is the number
        //saved to the char argument from LIT
        
        int i = runStack.get(framePointers.lastElement()+offset);
        
        
        return i;
    }
    /**
     * Used to load literals onto the stack - e.g. for LIT 5 we'll call push with 5
     * @param i
     * @return
     */
    public Integer push(Integer i){
        runStack.add(i);
        return i;
    }

    
}
