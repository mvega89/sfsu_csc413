/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.ui;

import interpreter.debugger.DVirtualMachine;
import interpreter.debugger.Table;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Misha
 */
public class UIObject {
    
    DVirtualMachine machine;
    ArrayList<Integer> breakPoints = new ArrayList();
    
    public UIObject(DVirtualMachine vm){
        
        machine = vm;
        
    }

    public void run() {
        
        printSource();
        
        System.out.println();
        
        
        
       Scanner scan = new Scanner(System.in);
       
       String command;
       StringTokenizer commandTokens;
       
       do{
           
           System.out.println("Type ? for help");
           System.out.print(">>");
           
           command = scan.nextLine();
           
           
           
           if(command.equals("h")){
               System.out.println("******Execution Halted*******");
               break;
           }
           
           else if(command.equals("?")){
               System.out.println(" __________________________________ ");
               System.out.println("|KEY OF COMMANDS                   |");
               System.out.println("|----------------------------------|");
               System.out.println("|Command : Type in                 |");
               System.out.println("|----------------------------------|");
               System.out.println("|Set breakpoint: sb (line number)  |");
               System.out.println("|Clear breakpoint: cb (line number)|");
               System.out.println("|Clear all breakpoints: all        |");
               System.out.println("|List breakpoints: l               |");
               System.out.println("|Display current function: d       |");
               System.out.println("|Print call stack: cs              |");
               System.out.println("|Display variables: dv             |");
               System.out.println("|Continue execution: c             |");
               System.out.println("|Step out: out                     |");
               System.out.println("|Step into: into                   |");
               System.out.println("|Step over: over                   |");
               System.out.println("|Halt execution: h                 |");
               System.out.println("|Trace on: tron                    |");
               System.out.println("|Trace off: troff                  |");
               System.out.println("|__________________________________|");
               
               System.out.println();
               //TODO: add all commands
               
               //print out help menu
               
           }
           else if(command.equals("c")){
               System.out.println();
               machine.executeProgram();
               //displayCurrentFunction();
               printSource();
               System.out.println();
               
           }
           
           else if(command.equals("over")){
               System.out.println();
               ((DVirtualMachine)machine).executeStepOver();
               displayCurrentFunction();
               //printSource();
               System.out.println();
               
           }
           
            else if(command.equals("into")){
              System.out.println();
               ((DVirtualMachine)machine).executeStepInto();
               printSource();
               //displayCurrentFunction();
               System.out.println();
               
           }
           
           else if(command.equals("out")){
               System.out.println();
               ((DVirtualMachine)machine).executeStepOut();
               displayCurrentFunction();
               //printSource();
               System.out.println();
               
               
           }
           
           else if(command.equals("l")){
               
               System.out.print("Breakpoints: ");
               printBreakPoints();
               System.out.println();
               
           }
           
            else if(command.equals("cs")){
                System.out.println();
               machine.printCallStack();
               System.out.println();
               
           }
           
           else if(command.startsWith("sb")){
               
               
               
               commandTokens = new StringTokenizer(command);
               commandTokens.nextToken();
               
               if(!commandTokens.hasMoreElements()){
                   System.out.println("Please input a line number!");
                   System.out.println();
                   
               }else{
                   
                   int brkPtLine = Integer.parseInt(commandTokens.nextToken());
                   breakPoints.add(brkPtLine);
                   
                   if( ((DVirtualMachine)machine).isBrkPointAllowed(brkPtLine)){
                       machine.setBreakPoint(brkPtLine);
                       System.out.println("Breakpoint set: "+brkPtLine);
                       System.out.println();
                       
                   }else {
                       System.out.println("Breakpoint not allowed!");
                   }
               }
               
           }
           
           else if(command.equals("d")){
               System.out.println();
               displayCurrentFunction();
               System.out.println();
               
           }
           else if(command.equals("tron")){
               ((DVirtualMachine)machine).setTraceOn();
               System.out.println("Trace on");
               System.out.println();
           }
           
           else if(command.equals("troff")){
               ((DVirtualMachine)machine).setTraceOff();
               System.out.println("Trace off");
               System.out.println();
           }
           
           else if(command.equals("dv")){
               System.out.println();
               ((DVirtualMachine)machine).getVariables();
               System.out.println();
               
           }
           
           else if(command.startsWith("cb")){
               
               commandTokens = new StringTokenizer(command);
               commandTokens.nextToken();
               
               if(!commandTokens.hasMoreElements()){
                   System.out.println("Please input a line number!");
                   System.out.println();
                   
               }else{
                   
                   int clrBrkPoint = Integer.parseInt(commandTokens.nextToken());
                   breakPoints.remove(Integer.valueOf(clrBrkPoint));
                   machine.clearBreakPoint(clrBrkPoint);
                   
                   System.out.println("Breakpoint cleared: "+ clrBrkPoint);
                   System.out.println();
               }
           }
           else if(command.equals("all")){
               
               System.out.print("Breakpoints cleared: ");
               printBreakPoints();
               System.out.println();
               
               for(int i = 0; i< breakPoints.size();i++){
                   
                   machine.clearBreakPoint(breakPoints.get(i));
               }
               breakPoints.clear();
           }
           
           
           else{
               System.out.println("Invalid command: please type in ? for help");
               System.out.println();
           }
           
       }while(true);
          
    }
    
    public void printSource(){
        
        if(machine.isTraceOn()){
        System.out.println();
        }
        
        int currLine = machine.getCurrentLine();
        for(int i = 1;i <= machine.getSourceLineCount();i++){
            
            
            String lineToPrint = machine.getSourceLine(i);
            Boolean breakPtSet = machine.isBreakPtSet(i);
            
            if(breakPtSet){
                System.out.print("*");
            }
            System.out.print(i + ".");
            System.out.print(lineToPrint);
            if (i == currLine){
                System.out.print("<-------");
            }
            System.out.println();
            
        }
        
    }
    
    public void displayCurrentFunction(){
        if(machine.isTraceOn()){
        System.out.println();
        }
        int currLine = machine.getCurrentLine();
        int j = machine.getEndLine();
        for(int i = machine.getStartLine() ;i <= j ;i++){
            
           
            
            String lineToPrint = machine.getSourceLine(i);
            Boolean breakPtSet = machine.isBreakPtSet(i);
            
            if(breakPtSet){
                System.out.print("*");
            }
            System.out.print(i + ".");
            System.out.print(lineToPrint);
            if (i == currLine){
                System.out.print("<-------");
            }
            System.out.println();
        }
    }
    
    public void printBreakPoints(){
        
        for(int i = 0;i < breakPoints.size();i++){
            System.out.print(breakPoints.get(i));
            if(i>=0 && (i < breakPoints.size())){
                System.out.print(" ");
            }
        }
        System.out.println();
    }
    
    
    
}
