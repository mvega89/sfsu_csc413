/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;
import interpreter.debugger.DCodeTable;
import interpreter.debugger.DVirtualMachine;
import interpreter.debugger.ui.UIObject;
import java.io.*;


/**
 *
 * @author Misha
 */
public class Interpreter {

    ByteCodeLoader bcl;
    String sourceCode;
    //private char ch;

    //constructor called without -d mode
    public Interpreter(String codeFile) {
        CodeTable.init();
        try {
            
            bcl = new ByteCodeLoader(codeFile);
            //ch = bcl.read();
            
        } catch (IOException e) {
            System.out.println("**** " + e);
        }
    }
    //codeFile - bytecodes
    //this constructor only called with debugger -d flag
    public Interpreter(String codeFile,String sourceCode) {
        
        DCodeTable.init();
        
        this.sourceCode = sourceCode;
        
        try {
            
            bcl = new ByteCodeLoader(codeFile);
            //ch = bcl.read();
            
        } catch (IOException e) {
            System.out.println("**** " + e);
		}
	}
        
	void run() {
		Program program = bcl.loadCodes();
                
		VirtualMachine vm = new VirtualMachine(program);
		vm.executeProgram();
	}
        
        void debugRun(){
            
            Program program = bcl.loadCodes();
            
            DVirtualMachine vm = new DVirtualMachine(program, sourceCode);
            
            (new UIObject(vm)).run();
            
            //put int deb.ui
            //vm.executeProgram();
            
            
        }

	public static void main(String args[]) {
            if (args.length == 0) {
                System.out.println("***Incorrect usage, try: java interpreter.Interpreter <file>");
                System.exit(1);
            }
            
            else if(args[0].equals("-d")){
                if(args[1] == null){
                    System.out.println("Please include base name");
                    System.exit(1);
                }
                String baseName = args[1];
                
                //switched to debugRun
                (new Interpreter(baseName.concat(".x.debug.cod"), baseName.concat(".x"))).debugRun();
                
            }
            
            else{
                (new Interpreter(args[0])).run();
            }
                //new Interpreter(args[0]);
	}
}
