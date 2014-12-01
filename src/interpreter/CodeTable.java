/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

/**
 *
 * @author Misha
 */
public class CodeTable {
    public static java.util.HashMap<String,String> codes = new java.util.HashMap<String,String>();
    
/*  Each ByteCode will also have an init() method, into which you can pass 
    arguments.So a StoreCode would expect two parameters in its init() method-- 
    the value being stored, and the variable the value belongs to.  Some classes
    can have a variable number of init() parameters, e.g. LitCode. */
   
    //will be called from ByteCoadLoader
    public static String get(String code){
        
        
        return codes.get(code);
        
    }
    
    public static void init() {
        codes.put("HALT", "HaltCode");
        codes.put("POP", "PopCode");
        codes.put("FALSEBRANCH", "FalseBranchCode");
        codes.put("GOTO", "GotoCode");
        codes.put("STORE", "StoreCode");
        codes.put("LOAD", "LoadCode");
        codes.put("LIT", "LitCode");
        codes.put("ARGS", "ArgsCode");
        codes.put("CALL", "CallCode");
        codes.put("RETURN", "ReturnCode");
        codes.put("BOP", "BopCode");
        codes.put("READ", "ReadCode");
        codes.put("WRITE", "WriteCode");
        codes.put("LABEL", "LabelCode");
        codes.put("DUMP", "DumpCode");
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
