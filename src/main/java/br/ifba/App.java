package br.ifba;

import java.util.Arrays;

import br.ifba.exception.InvalidParameterException;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private static final String DECRIPT = "DECRIPT";

	private static final String ENCRIPT = "ENCRIPT";

	private static final String[] matrix = {"A", "B", "C", "D", "E", "F", "G","H", "I", "J", "K", "L","M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z","A", "B", "C", "D", "E", "F", "G","H", "I", "J", "K", "L","M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	
	private static String[][] cifra = new String[26][26];
	
	private static void loadCifra(){
		
		for (int i=0; i < 25;i++) {
			for (int j=i; j < i+25;j++) {
				cifra[i][j-i]=matrix[j];
			}
		}
				
	}
	
    public static void main( String[] args )
    {
    	try {
    		
			if (args.length!=3 || (args[0].length()==0 && ENCRIPT.equals(args[0]) || DECRIPT.equals(args[0])) || args[1].length()==0 || args[2].length()==0) {
				throw new InvalidParameterException("Parametros inavalidos....");
			}
    	    	
			String operacao = args[0].toUpperCase();
			String text = args[1].toUpperCase();
			String chave = args[2].toUpperCase();
			
			String textoCifrado="";
			
			loadCifra();
			
			/**
			 * Verifica se a chave é menor que o texto, se for repete a chave ate que fique maior ou igual 
			 */
			if (chave.length() < text.length()) {
				for (int i=0;i<text.length()-chave.length();i++) {
					chave+=chave;
					if (chave.length() >= text.length()) {
						break;
					}
				}
			}
			
			/**
			 * Aqui acontece o encript ou decript
			 */
			for (int i=0; i < text.length();i++) {
				String letraTexto = text.split("|")[i];
				String letraChave = chave.split("|")[i];
				
				int posLetraTexto = Arrays.asList(matrix).indexOf(letraTexto);
				int posLetraChave = Arrays.asList(matrix).indexOf(letraChave);
				
				if (ENCRIPT.equals(operacao)) {
					textoCifrado+=cifra[posLetraTexto][posLetraChave];
				}else {
					int result = posLetraTexto - posLetraChave < 0?(posLetraTexto - posLetraChave)+26:posLetraTexto - posLetraChave;
					
					textoCifrado+=matrix[result];
				}
				
				
			}
			
			System.out.println(textoCifrado);
			
		}catch (InvalidParameterException e) {
			System.out.println("entre com a operacao, o texto e a chave de criptografia ex.: encript|decript texto chave");
		}
		
		catch(Exception e ) {
			e.printStackTrace();
		}

    }
}
