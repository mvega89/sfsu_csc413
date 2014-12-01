/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;

import interpreter.CodeTable;
import static interpreter.CodeTable.codes;

/**
 *
 * @author Misha
 */
public class DCodeTable extends CodeTable {
    
     public static java.util.HashSet<String> debuggerCodes = new java.util.HashSet<String>();
    
    
    public static void init() {
        CodeTable.init();
        
        codes.put("LINE", "LineCode");
        codes.put("FORMAL", "FormalCode");
        codes.put("FUNCTION", "FunctionCode");
        
        debuggerCodes.add("LINE");
        debuggerCodes.add("FORMAL");
        debuggerCodes.add("FUNCTION");
        debuggerCodes.add("LIT");
        debuggerCodes.add("POP");
        debuggerCodes.add("CALL");
        debuggerCodes.add("RETURN");
        debuggerCodes.add("STORE");
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static boolean isDebuggerCode(String name){
        return debuggerCodes.contains(name);
    }
   
    /*
     *  codes.put("LINE", "LineCode");
        codes.put("FORMAL", "FormalCode");
        codes.put("FUNCTION", "FunctionCode");
     * 
     * 
     * 
     */
    
}
