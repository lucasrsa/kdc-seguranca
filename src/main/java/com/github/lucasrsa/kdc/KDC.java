package com.github.lucasrsa.kdc;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Lucas Rosa at https://github.com/lucasrsa
 */
public class KDC {
    
    private final ArrayList<String[]> userList = new ArrayList<>();
    
    // Gera chave alfanumérica de 32 caractéres
    private String generateKey() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 32;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
          .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
    }
    
    private String[] getUser(String id){
        for(String[] user:this.userList){
            if(user[0].equals(id)){
                return user;
            }
        }
        return null;
    }
    
    private String getUserKey(byte[] usrId)
            throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, Exception
    {
        for(String[] user:this.userList){
            try{
                if(user[0].equals(AES.decifra(usrId, user[1]))){
                    return user[1];
                }
            }catch(BadPaddingException e){ 
                // BadPaddingException is thrown when a decoding is attempted with the wrong key, which is expected for all the other users from the list
            }
        }
        return null;
    }
    
    // Gera uma nova chave para ser masterKey de novo User, adiciona à lista de usuários
    public String newUser(String id) throws Exception {
        if(this.getUser(id) != null){
            throw new Exception("User "+id+" already exists!");
        }
        final String key = this.generateKey();
        final String[] user = {id, key};
        userList.add(user);
        return key;
    }
    
    // Gera uma nova chave de sessão, retorna um par (cifrado na masterKey de cada User)
    public byte[][] startSession(byte[] src, byte[] dst)
            throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, Exception
    {
        String srcKey = this.getUserKey(src);
        String dstKey = this.getUserKey(dst);
        if((srcKey == null) || (dstKey == null)){
            throw new Exception("User not found!");
        }
        final String key = this.generateKey();
        final byte[][] keyPair = {
            AES.cifra(key, srcKey),
            AES.cifra(key, dstKey)
        };
        return keyPair;
    }
    
}
