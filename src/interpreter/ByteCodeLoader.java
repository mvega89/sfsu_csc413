/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;
import interpreter.bytecodes.ByteCode;
import interpreter.debugger.DCodeTable;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;


/**
 *
 * @author Misha
 */
public class ByteCodeLoader extends Program{
    
    ArrayList<ByteCode> array1;
    java.util.HashMap<String,Integer> labels = new java.util.HashMap<String,Integer>();

    
    
    
    /**
     * Construct a new ByteCodeLode
     */
    
    public ByteCodeLoader(String sourceFile)throws IOException{
        //System.out.println("Source file:" + sourceFile);
        //System.out.println("user.dir: " + System.getProperty("user.dir"));
        
        BufferedReader br = new BufferedReader(new FileReader(sourceFile));
        try{
            array1 = new ArrayList<ByteCode>();
            Vector<String> args = new Vector<String>();
            
            String sCurrentLine, codeClass, str;
            StringTokenizer st;
            
            
            ByteCode bytecode; 
            
            int countLine = 0; 
            
            while((sCurrentLine = br.readLine())!=null){
                args.clear();
                st = new StringTokenizer(sCurrentLine);//ie LIT 3 is going to tokenize into st
                //if(st.hasMoreElements())
                str = st.nextToken();//just LIT
                
                //look for instance LABEL: for resolve adresss : to match for GOTO, FALSEBRANCH, CALL 
                boolean flagLabel = ("LABEL".equals(str));
                
                codeClass = CodeTable.get(str);
                
                //catches for debug bytecodes 
                if(DCodeTable.isDebuggerCode(str)){
                    
                    bytecode = (ByteCode)(Class.forName("interpreter.bytecodes.debuggerByteCodes."+codeClass).newInstance());
                    
                }else{
                //byteCode is now an isntance on LitCode
                bytecode = (ByteCode)(Class.forName("interpreter.bytecodes."+codeClass).newInstance());
                
               
                }
                
                if(flagLabel){
                     //before storing LABEL instance arguments, create a hashmap with
                     //its arguement and line number and store to Program
                    String strLabel = st.nextToken();
                    labels.put(strLabel, countLine);
                     //store arguments into a vector
                    args.add(strLabel);
                }else{
                    while(st.hasMoreElements()){
                        //store arguments(not of LABEL) into vector
                        args.add(st.nextToken());
                        
                    }
                }

                //store vector of arguments into instance
                bytecode.init(args);
                //store instance into arraylist
                array1.add(bytecode);
                //increment for LABEL line number
               countLine++; 
            }
            //resolveAddresses();
            
            
        } catch( IOException | InstantiationException | ClassNotFoundException | IllegalAccessException e){
            e.printStackTrace();
        }
        
        
    }
    //from interpreter class,storing instances and hashmap for Program
    
    public Program loadCodes(){
        
        Program p = new Program();
        
        p.setArray(array1);
        p.setLabelMap(labels);
        
        return p;
    }
    
}

