/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasrsa.kdc;

/**
 *
 * @author Lucas Rosa at https://github.com/lucasrsa
 */
public class Auth {
    
    private static int function0(int noonce){
        return noonce;
    }
    
    private static int function1(int noonce){
        return noonce;
    }
    
    private static int function2(int noonce){
        return noonce;
    }
    
    private static int function3(int noonce){
        return noonce;
    }
    
    private static int function4(int noonce){
        return noonce;
    }
    
    public static int getFunction(int op, int noonce){
        switch (op % 5) {
            case 0:
                return function0(noonce);
            case 1:
                return function1(noonce);
            case 2:
                return function2(noonce);
            case 3:
                return function3(noonce);
            case 4:
                return function4(noonce);
            default:
                return noonce;
        }
    }
    
}
