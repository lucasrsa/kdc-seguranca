
public class FuncaoAutenticacao {
	
	static public int funcaoBobAna(int nonce)
	{
		return (nonce) * 3 / 2 + 2157;
	}

	static public int funcaoBobAlice(int nonce)
	{
		return (nonce) + 1;
	}
}
