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
public class Main {
    
    public static void main(String[] args)
            throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, Exception
    {
        KDC kdc = new KDC();
        User ana = new User("Ana", kdc);
        User bob = new User("Bob", kdc);
        User alice = new User("Alice", kdc);
        
        // Multiplas execuções foram feitas para testar o dinamismo das funções
        bob.communicate(alice);
        bob.communicate(ana);
        alice.communicate(ana);
        alice.communicate(bob);
        ana.communicate(alice);
        ana.communicate(bob);
    }
    
}
