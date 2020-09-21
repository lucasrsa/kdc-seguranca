import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class KDC {
	
	private Pessoa bob;
	private Pessoa ana;
		
	public KDC(Pessoa bob, Pessoa ana)
	{
		this.bob = bob;
		this.ana = ana;
	}
	
	public String getChaveSessao()
	{
		//random
		return "1111222233334444";
	}
	
	public void GerarChaveSessao(String id, byte[] idCifrado, byte[] destinatarioCifrado) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException
	{
		String novoID = AES.decifra(idCifrado, bob.getChaveMestre());
		
		if( id.equals(novoID) )
		{
			System.out.println("Autenticado");
			String destinatario = AES.decifra(destinatarioCifrado, bob.getChaveMestre());
			
			if( destinatario.equals(ana.getID()) )
			{
				String chaveSessao = getChaveSessao();
				byte[] k_s_bob = AES.cifra(chaveSessao, bob.getChaveMestre());
				byte[] k_s_ana = AES.cifra(chaveSessao, ana.getChaveMestre());
				
				//...
			}			
		}
		else
		{
			System.out.println("Autenticacao invalida");
		}
	}
	
}
