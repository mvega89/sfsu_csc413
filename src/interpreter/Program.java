/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import interpreter.bytecodes.ByteCode;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Misha
 */
public class Program {
    
    private ArrayList<ByteCode> array;
    HashMap<String,Integer> labels;
    
 
    public void setArray(ArrayList<ByteCode> a){
        array = a;
    }
    
    public ByteCode getCode(int i){
        
        return array.get(i);
        
    }
    
    public void setLabelMap(HashMap<String,Integer> label1){
        
        labels = label1;
        
    }
    
    
    public int resolveAddress(String labelName) {
        
        return labels.get(labelName);
        
    }
    
    
}
    
   
