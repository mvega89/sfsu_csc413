/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 *
 * @author Misha
 */
public class FunctionEnvironmentRecord {
    
    String functionName;
    int startLine;
    int endLine;
    int currentLine;
    Table symbolTable;//= new Table();
    static ArrayList<String> name; //= new ArrayList();
    
    public FunctionEnvironmentRecord(){
        currentLine = 0; 
        startLine = 0;
        endLine = 0;
        functionName = "";
        symbolTable = new Table();
        name = new ArrayList();
    }
    
    public void addStringName(String arg){
        name.add(arg);
    }
    
    public void putSymbolTable(String key, int value){
        symbolTable.put(key, value);
    }
    
    public Table getSymbolTable(){
        return symbolTable;
    }
    
    public ArrayList getNames(){
        return name;
    }
    
    public void setFunction(String name, int start, int end){
        functionName = name;
        startLine = start;
        endLine = end;
        
        
    }
    public String getFunctionName(){
        
        return functionName;
        
    }
    
    public int getStartLine(){
        
        return startLine;
    }
    public int getEndLine(){
        return endLine;
    }
    
    public void setCurrentLine(int n){
        currentLine = n;
    }
    
    public int getCurrentLine(){
        return currentLine;
    }
    
    
    
    
    
    public void popSymbol(){
     
            symbolTable.popSymbol();
            
            name.remove(name.size() - 1);
      
    }
}
    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String[] args) {
        
        FunctionEnvironmentRecord fctEnvRecord = new FunctionEnvironmentRecord(); //new FunctionEnvironmentRecord();
        Table binder = null; //new Table();
        StringTokenizer commandTokens;
        String command;
        String values = "";
        
      
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Please type in exact commands from 'Command Issued' column given in Milestone 2 - Test Case page"
                + "(and 'exit' when finished) ");
        
        do{
            command = scan.nextLine();
            if(command.equals("exit")){
                break;
            }
            if(command.startsWith("BS")){
                
                 //fctEnvRecord = new FunctionEnvironmentRecord();
                binder = new Table();
                binder.beginScope();
                
                //fctEnvRecord = new  FunctionEnvironmentRecord();
                //System.out.println("test");
                
            }
            
            if(command.startsWith("Function")){
                
                commandTokens = new StringTokenizer(command);
                commandTokens.nextToken();
                fctEnvRecord.setFunction(commandTokens.nextToken(),Integer.parseInt(commandTokens.nextToken()), Integer.parseInt(commandTokens.nextToken()));
                
                    System.out.println("("+ "<" + values + ">" +","+ fctEnvRecord.getFunctionName() +"," +
                            fctEnvRecord.getStartLine() +","+ fctEnvRecord.getEndLine()+","+fctEnvRecord.getCurrentLine()+")");
                
                
                
                
            }
            
            if(command.startsWith("Line")){
                commandTokens = new StringTokenizer(command);
                commandTokens.nextToken();
                fctEnvRecord.setCurrentLine(Integer.parseInt(commandTokens.nextToken()));
                
                System.out.println("("+ "<" + values + ">" + ","+ fctEnvRecord.getFunctionName() +"," +
                        fctEnvRecord.getStartLine() +","+ fctEnvRecord.getEndLine()+","+fctEnvRecord.getCurrentLine()+")");
                
            }
            
            if(command.startsWith("Enter")){
                
                
                
                commandTokens = new StringTokenizer(command);
                commandTokens.nextToken();
                String key = commandTokens.nextToken();
                binder.put(key, Integer.parseInt(commandTokens.nextToken()));
                name.add(key);
                values = "";
                
                
                if(name.size() == 1 ){
                    
                    values += (name.get(0) + "/" + binder.get(name.get(0)));
                }
                
                if(name.size() > 1){
                    
                    values += (name.get(name.size()-1) + "/" + binder.get(name.get(name.size()-1)));
                    
                    for(int i = 0; i < name.size()-1; i++){
                        
                        if(name.get(name.size()-1).equals(name.get(i))){
                            
                        }else{
                            values+=",";
                            values += (name.get(i) + "/" + binder.get(name.get(i)));
                        }
                    }
                }
                
                
                System.out.println("("+ "<"+values+">"+","+ fctEnvRecord.getFunctionName() +"," +
                        fctEnvRecord.getStartLine() +","+ fctEnvRecord.getEndLine()+","+fctEnvRecord.getCurrentLine()+")");
                
            }
        


            if(command.startsWith("Pop")){
                commandTokens = new StringTokenizer(command);
                commandTokens.nextToken();
                int arg = Integer.parseInt(commandTokens.nextToken());
                
                for(int k = 0;k<arg;k++){
                    binder.popSymbol();
                    
                    name.remove(name.size() - 1);
                    
                }
                
                values = "";
                
                if(name.isEmpty()){
                    values = "";
                }
                
                else if(name.size() == 1 ){
                    
                    
                    values += (name.get(0) + "/" + binder.get(name.get(0)));
                    
                }
                else{
                    
                    values += (name.get(name.size()-1) + "/" + binder.get(name.get(name.size()-1)));
                    
                    for(int i = 0; i < name.size()-1; i++){
                        if(name.get(name.size()-1).equals(name.get(i))){
                            
                        }else{
                            values+=",";
                            values += (name.get(i) + "/" + binder.get(name.get(i)));
                        }
                    }
                }
                
                System.out.println("("+ "<" +values+">"+","+ fctEnvRecord.getFunctionName() +"," +
                        fctEnvRecord.getStartLine() +","+ fctEnvRecord.getEndLine()+","+fctEnvRecord.getCurrentLine()+")");
                
                //System.out.println("("+ fctEnvRecord.get()+","+ fctEnvRecord.getFunctionName() +"," + fctEnvRecord.getStartLine() +","+ fctEnvRecord.getEndLine()+","+fctEnvRecord.getCurrentLine()+")");
            }
            
        }while(true);
        
        
        
        // TODO code application logic here
    }
}
*/