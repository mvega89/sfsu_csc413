/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecodes;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author Misha
 */
public class BopCode extends ByteCode{
    
    String operator;
    int topLevel;
    int secondLevel;
    int returnValue;
    
    public void init(Vector<String> args) {
        operator = args.firstElement();
    }
    
    
    public void execute(VirtualMachine vm) {
        topLevel = vm.popRunStack();
        secondLevel = vm.popRunStack();
        
        if("+".equals(operator))
        {
            returnValue = secondLevel + topLevel;
            vm.pushRunStack(returnValue);
            
        }
        else if("-".equals(operator)){
            returnValue = secondLevel - topLevel;
            vm.pushRunStack(returnValue);
            
        }
        else if("/".equals(operator)){
            returnValue = secondLevel / topLevel;
            vm.pushRunStack(returnValue);
            
        }
        else if("*".equals(operator)){
            returnValue = secondLevel * topLevel;
            vm.pushRunStack(returnValue);
            
        }
        
        else if("==".equals(operator)){
            if(secondLevel != topLevel) {
                vm.pushRunStack(0);
            } else {
                vm.pushRunStack(1);
            }
            
        }
        
        else if("!=".equals(operator)){
            if(secondLevel != topLevel) {
                vm.pushRunStack(1);
            } else {
                vm.pushRunStack(0);
            }
            
        }
        
        else if("<=".equals(operator)){
            if(secondLevel <= topLevel) {
                vm.pushRunStack(1);
            } else {
                vm.pushRunStack(0);
            }
            
        }
        
        else if(">=".equals(operator)){
            if(secondLevel >= topLevel) {
                vm.pushRunStack(1);
            } else {
                vm.pushRunStack(0);
            }
            
        }
        
        else if(">".equals(operator)){
            if(secondLevel > topLevel) {
                vm.pushRunStack(1);
            } else {
                vm.pushRunStack(0);
            }
        }
        
        else if("<".equals(operator)){
            if(secondLevel < topLevel) {
                vm.pushRunStack(1);
            } else {
                vm.pushRunStack(0);
            }
        }
        
        else if("|".equals(operator)){
            
            vm.pushRunStack(topLevel);
        }
        
        else /*("&".equals(operator))*/{
            if(secondLevel != topLevel) {
                vm.pushRunStack(0);
            } else {
                vm.pushRunStack(1);
            }
        }
        
        if(vm.printOn()){
        System.out.println("BOP " + operator);
        }
    }
    
    
}
