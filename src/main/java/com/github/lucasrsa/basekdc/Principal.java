import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Principal {

	public static void main(String[] args) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {
		
		Pessoa bob = new Pessoa("bob", "bolabolabolabola");
		Pessoa ana = new Pessoa("ana", "patopatopatopato");
		KDC kdc = new KDC(bob, ana);
		
		String parametro1 = bob.getID();
		byte[] parametro2 = AES.cifra(bob.getID(), bob.getChaveMestre());
		byte[] parametro3 = AES.cifra(ana.getID(), bob.getChaveMestre());
		
		kdc.GerarChaveSessao(parametro1, parametro2, parametro3);
		
		
	}
}
