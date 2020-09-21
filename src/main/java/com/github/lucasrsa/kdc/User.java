/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasrsa.kdc;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
    
    public User(String id, KDC kdc) throws Exception {
        this.id = id;
        this.masterKey = kdc.newUser(id);
        this.kdc = kdc;
    }
    
    public byte[] getKey() 
            throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException
    {
        return AES.cifra(this.masterKey, this.masterKey);
    }
    
    public String getId(){
        return this.id;
    }
    
}
