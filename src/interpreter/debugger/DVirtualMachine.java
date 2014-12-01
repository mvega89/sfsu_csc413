/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;

import interpreter.Program;
import interpreter.VirtualMachine;
import interpreter.bytecodes.ByteCode;
import interpreter.bytecodes.HaltCode;
import interpreter.bytecodes.LabelCode;
import interpreter.bytecodes.ReadCode;
import interpreter.bytecodes.debuggerByteCodes.*;
import static interpreter.debugger.FunctionEnvironmentRecord.name;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Misha
 */
public class DVirtualMachine extends VirtualMachine {

    
    
    class SourceLine {
        String sourceLine;
        Boolean isBreakPtSet;
        
        public SourceLine(String line){
            sourceLine = line;
            isBreakPtSet = false;
        }
        
        public void setBreakPt(){
            isBreakPtSet = true;
        }
        
        public void clearBreakPoint(){
            isBreakPtSet = false;
        }
        
        public String getSourceLine() {
            return sourceLine;
        }
        
        public Boolean isBreakPtSet() {
            return isBreakPtSet;
        }
    }
    
    
    //user will store break point number in
    ArrayList<SourceLine> lineCodeArray = new ArrayList<SourceLine>();
    
    Stack<FunctionEnvironmentRecord> stckOfEnvRecord = new Stack<FunctionEnvironmentRecord>();
    
    
    public DVirtualMachine(Program program, String sourceCode) {
        
        super(program);
        
        stckOfEnvRecord.push(new FunctionEnvironmentRecord());
        
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(sourceCode));
            
            String sCurrentLine;
            
            //the following lets the first line be at index 1
            lineCodeArray.add(new SourceLine(""));
            
            while((sCurrentLine = br.readLine())!=null){
                lineCodeArray.add(new SourceLine(sCurrentLine));
            }
        } catch (IOException ex) {
            Logger.getLogger(DVirtualMachine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int getStckOfEnvRecordSize(){return stckOfEnvRecord.size(); }
    
    public String getSourceLine(int lineNumber){
        
        return lineCodeArray.get(lineNumber).getSourceLine();
    }
    
    public int getSourceLineCount(){
        return (lineCodeArray.size()-1);
    }
    
    public boolean isBreakPtSet(int lineNumber){
        return lineCodeArray.get(lineNumber).isBreakPtSet();
    }
    
    
    public void setBreakPoint(int lineNumber){
        
        lineCodeArray.get(lineNumber).setBreakPt();
    }
    
    public boolean isBrkPointAllowed(int lineNumber){
        String sourceLine = lineCodeArray.get(lineNumber).getSourceLine().trim();
        if(sourceLine.equals("{") || sourceLine.equals("}") || sourceLine.equals("else")){
            return false;
        }
        return true;
    }
    
    public void clearBreakPoint(int lineNumber){
        
        lineCodeArray.get(lineNumber).clearBreakPoint();
    }
    
    public int getCurrentLine() {
        return stckOfEnvRecord.peek().getCurrentLine();
    }
    
    
    @Override
    public void executeCode(ByteCode code){
        //after catching new bytecode, then
        if(code instanceof LineCode){
           
            code.execute(this);
            
            int lineNum = getCurrentLine();
            
            if( (lineNum != -1) && lineCodeArray.get(lineNum).isBreakPtSet()){
                isRunning = false;
            }
            pc++;
        } 
        else {
            super.executeCode(code);
        }
    }
    
    
    public void executeStepInto() {
        
        boolean insideFunction = true;
        
        //use execute code scheme
        while(insideFunction){
            ByteCode code = program.getCode(pc);
            executeCode(code);
            
            //was formal
            if(code instanceof FunctionCode){
                
                //executeProgram();
                insideFunction = false;
                code = program.getCode(pc);
                if(!(code instanceof ReadCode)){
                    executeStepOver();
                }
                
            }
        }
    }
     
    public void executeStepOver() {
        boolean insideFunction = true;
        
        //use execute code scheme
        while(insideFunction){
            ByteCode code = program.getCode(pc);
            executeCode(code);
            
            if(code instanceof LineCode){
                insideFunction = false;
                
            }
        }
        /*
         * if(code instanceof CallCode){
         * executeStepOut();
         * }
         */
    } 
    
     public void executeStepOut() {
         //use execute code scheme
        boolean insideFunction = true;
        
        while(insideFunction){
            //int envSize = getStckOfEnvRecordSize();
            ByteCode code = program.getCode(pc);
            
            //catch breakpoint
            executeCode(code);
            
              if(code instanceof ReturnCode){
              insideFunction = false;
              }
             
        }
     }
     
     
    public boolean printOn() {return false;}
  
    
    public void pushNewEvnRecord(){
        stckOfEnvRecord.push(new FunctionEnvironmentRecord());
    }
    
    
    
    public void popTopEvnRecord(){
        stckOfEnvRecord.pop();
        
    }
    
    public void setEvnRecordFunction(String name, int start, int end){
        stckOfEnvRecord.peek().setFunction(name, start, end);
    }
    
    public int getStartLine(){
        return stckOfEnvRecord.peek().getStartLine();
    }
    
    public int getEndLine(){
        return stckOfEnvRecord.peek().getEndLine();
    }
    
    public void putIntoSymTable(String key, int value){
        stckOfEnvRecord.peek().putSymbolTable(key, value);
    }
    
    public void lastAddedSymbol(String key){
        stckOfEnvRecord.peek().addStringName(key);
    }
    
    public void popSymbolTable(){
        stckOfEnvRecord.peek().popSymbol();
        
    }
    
    public void toSetCurrentLine(int currentLine){
        stckOfEnvRecord.peek().setCurrentLine(currentLine);
    }
    
    public void printCallStack(){
        
        for(int i = stckOfEnvRecord.size()-1; i >= 0 ; i--){
            System.out.println(i + "." );
            for(int line = stckOfEnvRecord.elementAt(i).getStartLine(); line <= stckOfEnvRecord.elementAt(i).getEndLine(); line++ ){
                String lineToPrint = getSourceLine(line);
                
                System.out.println(lineToPrint);
            }
        }
    }
    
    
    public void getVariables(){
        
            
        ArrayList name = stckOfEnvRecord.peek().getNames();
        Table symbolTable = stckOfEnvRecord.peek().getSymbolTable();
        
        
        if(name.size() == 1 ){
            if(name.get(0).equals("dummyFormal")){
                
            }else{
                System.out.println(name.get(0).toString() + ": " + runStack.elementAt(runStack.getSize()-1));
            }
        }
        if(name.size() > 1){
            
            for(int i = 0; i < name.size()-1; i++){
                //System.out.println(name.get(i) + ": " + runStack.elementAt(Integer.parseInt(symbolTable.get(name.get(i).toString()).toString())));
                if(name.get(i).equals("dummyFormal")){
                    
                }else{
                
                System.out.println(name.get(i) + ": " + runStack.elementAt(i));
                }
            }
        }
    
        
        
    }
    
   
          
    
    boolean traceOn;
    
    public boolean isTraceOn() {return traceOn;}

    public void setTraceOn() { traceOn = true;}

    public void setTraceOff() {traceOn = false;}
    
    
}
