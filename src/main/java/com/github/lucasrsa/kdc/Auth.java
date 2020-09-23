/*
 * To change this license header, chose License Headers in Project Properties.
 * To change this template file, chose Tols | Templates
 * and open the template in the editor.
 */
package com.github.lucasrsa.kdc;

/**
 *
 * @author Lucas Rosa at https://github.com/lucasrsa
 */
public class Auth {
    
    private static int function0(int nonce){
        return nonce % 25;
    }
    
    private static int function1(int nonce){
        return nonce / 13;
    }
    
    private static int function2(int nonce){
        return nonce * 21;
    }
    
    private static int function3(int nonce){
        return nonce - 37;
    }
    
    private static int function4(int nonce){
        return nonce + 48;
    }
    
    public static int parseNonce(int nonce){
        switch (Math.abs(nonce) % 5) {
            case 0:
                return function0(nonce);
            case 1:
                return function1(nonce);
            case 2:
                return function2(nonce);
            case 3:
                return function3(nonce);
            case 4:
                return function4(nonce);
            default:
                return nonce;
        }
    }
    
}
