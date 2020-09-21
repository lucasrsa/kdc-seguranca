import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AES 
{
	private static String ALGORITMO = "AES";
	
	public static byte[] cifra(String texto, String chave) 
			throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException
	{
		Key key = 
				new SecretKeySpec(chave.getBytes(StandardCharsets.UTF_8), ALGORITMO);			
		Cipher cifrador = Cipher.getInstance(ALGORITMO);
		cifrador.init(Cipher.ENCRYPT_MODE, key);
		byte[] textoCifrado = cifrador.doFinal(texto.getBytes());
		return textoCifrado;
	}	
	
	public static String decifra(byte[] texto, String chave) 
			throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException
	{
	  	 Key key = 
	  			 new SecretKeySpec(chave.getBytes(StandardCharsets.UTF_8), ALGORITMO);
		 Cipher decifrador = Cipher.getInstance(ALGORITMO);
		 decifrador.init(Cipher.DECRYPT_MODE, key);
    	 byte[] textoDecifrado = decifrador.doFinal(texto);
    	 return new String(textoDecifrado);
	}	
	
	public static void Imprimir(String texto)
	{
		System.out.println(texto);
	}
	
	public static void Imprimir(byte[] texto)
	{
		System.out.println(new String(texto));
	}
	
	public static void main(String[] args) throws Exception {
		try
		{						
			String chave = "0123456789ABCDEF"; //tamanho: 16
			String texto = "Quando eu digo que deixei de te amar\r\n" + 
					"x porque eu te amo\r\n" + 
					"Quando eu digo que nxo quero mais vocx\r\n" + 
					"x porque eu te quero\r\n" + 
					"Eu tenho medo de te dar meu coraxo\r\n" + 
					"E confessar que eu estou em tuas mxos\r\n" + 
					"Mas nxo posso imaginar o que vai ser de mim\r\n" + 
					"Se eu te perder um dia\r\n" + 
					"Eu me afasto e me defendo de vocx\r\n" + 
					"Mas depois me entrego\r\n" + 
					"Faxo tipo, falo coisas que eu nxo sou\r\n" + 
					"Mas depois eu nego\r\n" + 
					"Mas a verdade x que eu sou louco por vocxs\r\n" + 
					"E tenho medo de pensar em te perder\r\n" + 
					"Eu preciso aceitar que nxo dx mais\r\n" + 
					"Pra separar as nossas vidas\r\n" + 
					"E nessa loucura de dizer que nxo te quero\r\n" + 
					"Vou negando as aparxncias\r\n" + 
					"Disfarxando as evidxncias\r\n" + 
					"Mas pra que viver fingindo\r\n" + 
					"Se eu nxo posso enganar meu coraxo\r\n" + 
					"Eu sei que te amo\r\n" + 
					"Chega de mentiras\r\n" + 
					"De negar o meu desejo\r\n" + 
					"Eu te quero mais que tudo\r\n" + 
					"Eu preciso do seu beijo\r\n" + 
					"Eu entrego a minha vida\r\n" + 
					"Pra vocx fazer o que quiser de mim\r\n" + 
					"Sx quero ouvir vocx dizer que sim\r\n" + 
					"Diz que x verdade, que tem saudade\r\n" + 
					"Que ainda vocx pensa muito em mim\r\n" + 
					"Diz que x verdade, que tem saudade\r\n" + 
					"Que ainda vocx quer viver pra mim\r\n" + 
					"Eu me afasto e me defendo de vocx\r\n" + 
					"Mas depois me entrego\r\n" + 
					"Faxo tipo, falo coisas que eu nxo sou\r\n" + 
					"Mas depois eu nego\r\n" + 
					"Mas a verdade x que eu sou louco por vocx\r\n" + 
					"E tenho medo de pensar em te perder\r\n" + 
					"Eu preciso aceitar que nxo dx mais\r\n" + 
					"Pra separar as nossas vidas\r\n" + 
					"E nessa loucura de dizer que nxo te quero\r\n" + 
					"Vou negando as aparxncias\r\n" + 
					"Disfarxando as evidxncias\r\n" + 
					"Mas pra que viver fingindo\r\n" + 
					"Se eu nxo posso enganar meu coraxo\r\n" + 
					"Eu sei que te amo\r\n" + 
					"Chega de mentiras\r\n" + 
					"De negar o meu desejo\r\n" + 
					"Eu te quero mais que tudo\r\n" + 
					"Eu preciso do seu beijo\r\n" + 
					"Eu entrego a minha vida\r\n" + 
					"Pra vocx fazer o que quiser de mim\r\n" + 
					"Sx quero ouvir vocx dizer que sim\r\n" + 
					"Diz que x verdade, que tem saudade\r\n" + 
					"Que ainda vocx pensa muito em mim\r\n" + 
					"Diz que x verdade, que tem saudade\r\n" + 
					"Que ainda vocx quer viver pra mim\r\n" + 
					"Diz que x verdade, que tem saudade\r\n" + 
					"Que ainda vocx quer viver pra mim";
			
			byte[] textoCifrado = AES.cifra(texto, chave);
			String textoDecifrado = AES.decifra(textoCifrado, chave);
			Imprimir(textoCifrado);
			Imprimir(textoDecifrado);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}