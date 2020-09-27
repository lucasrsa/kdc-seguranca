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
    
    public byte[] getId() 
            throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException
    {
        return AES.cifra(this.id,this.masterKey);
    }
    
    // Verifica se User está em outra sessão, decifra chave de sessão e gera nounce
    public byte[] startSession(byte[] key) throws Exception {
        if(this.sessionKey != null){
            throw new Exception("User not available");
        }
        this.sessionKey = AES.decifra(key,this.masterKey);
        this.nonce = this.generateNonce();
        return AES.cifra(Integer.toString(this.nonce), this.sessionKey);
    }
    
    public void validateNonce(byte[] nonce) throws Exception {
        if(Auth.parseNonce(this.nonce) != Integer.parseInt(AES.decifra(nonce, this.sessionKey))){
            throw new Exception("Nonce not match!");
        }
    }
    
    public String toString(){
        return this.id;
    }

    // Libera valores de sessão
    public void endSession(){
        this.sessionKey = null;
        this.nonce = 0;
    }
    
    // Função responsável por toda a comunicação (handshake apenas) entre User A (this) e User B (dst)
    public void communicate(User dst)
            throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, Exception
    {
        // User solicita chave de sessão para KDC
        final byte[][] sessionKeyPair = kdc.startSession(
                AES.cifra(this.id, this.masterKey), 
                dst.getId()
        );
        
        // Primeira chave do par está cifrada na masterKey de this
        this.sessionKey = AES.decifra(sessionKeyPair[0], this.masterKey);

        // startSession retorna nonce gerado por dst cifrado na chave de sessão
        this.nonce = Integer.parseInt(
                AES.decifra(
                    // Segunda chave do par está cifrada na masterKey de dst
                    dst.startSession(sessionKeyPair[1]), 
                    this.sessionKey
                )
        );
        
        // Solicita validação de nonce modificado para dst
        dst.validateNonce(
                AES.cifra(
                        Integer.toString(Auth.parseNonce(this.nonce)),
                        this.sessionKey
                )
        );
        
        System.out.println("Communication between "+this+" and "+dst+" successfull!");
        this.endSession();
        dst.endSession();
    }
    
    // Construtor de User necessita de KDC para ser adicionado á lista de usuários
    public User(String id, KDC kdc) throws Exception {
        this.id = id;
        this.masterKey = kdc.newUser(id);
        this.kdc = kdc;
    }
    
}
