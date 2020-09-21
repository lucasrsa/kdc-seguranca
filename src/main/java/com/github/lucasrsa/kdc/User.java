/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasrsa.kdc;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Lucas Rosa at https://github.com/lucasrsa
 */
public class User {
    
    private final String id;
    private final String masterKey;
    private final KDC kdc;
    String sessionKey = null;
    int nonce = 0;
    
    private int generateNonce(){
        Random rand = new Random();
        
        return rand.nextInt(Integer.MAX_VALUE);
    }
    
    public byte[] getKey() 
            throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException
    {
        return AES.cifra(this.masterKey, this.masterKey);
    }
    
    public String getId(){
        return this.id;
    }
    
    public byte[] startSession(byte[] key) throws Exception {
        if(this.sessionKey != null){
            throw new Exception("User not available");
        }
        this.sessionKey = AES.decifra(key,this.masterKey);
        this.nonce = this.generateNonce();
        return AES.cifra(Integer.toString(this.nonce), this.sessionKey);
    }
    
    public void validateNonce(int nonce) throws Exception {
        if(Auth.parseNonce(this.nonce) != nonce){
            throw new Exception("Nonce not match!");
        }
    }
    
    public void communicate(User dst)
            throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, Exception
    {
        final byte[][] sessionKeyPair = kdc.startSession(
                AES.cifra(this.id, this.masterKey), 
                AES.cifra(dst.getId(),this.masterKey)
        );
        this.sessionKey = AES.decifra(sessionKeyPair[0], this.masterKey);
        this.nonce = Integer.parseInt(AES.decifra(
                dst.startSession(sessionKeyPair[1]), 
                this.sessionKey
        ));
        dst.validateNonce(Auth.parseNonce(this.nonce));
        System.out.println("Communication successfull!");
        this.sessionKey = null;
        this.nonce = 0;
    }
    
    public User(String id, KDC kdc) throws Exception {
        this.id = id;
        this.masterKey = kdc.newUser(id);
        this.kdc = kdc;
    }
    
}
