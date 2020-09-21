package com.github.lucasrsa.kdc;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Lucas Rosa at https://github.com/lucasrsa
 */
public class KDC {
    
    private ArrayList<String[]> userList;
    
    private String generateKey() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 80;
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
    
    public String newUser(String id) throws Exception {
        if(this.getUser(id) != null){
            throw new Exception("User "+id+" already exists!");
        }
        final String key = this.generateKey();
        final String[] user = {id, key};
        userList.add(user);
        return key;
    }
    
}
