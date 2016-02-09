package pe.edu.unu.evaluacion.main;

import java.util.Random;

import pe.edu.unu.evaluacion.util.EncriptarDesencriptar;
import pe.edu.unu.evaluacion.util.UtilMD5;

public class Encriptar {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String key = "qwewss"; //llave 92AE31A79FEEB2A3
		
		String keyMD5 = UtilMD5.md5(key).substring(0,16);
		
		
		String cleartext = "0123456789~0123456789~0123456789~0123456789~0123456789~0123456789~0123456789~0123456789";
		
		String textoEncriptado = EncriptarDesencriptar.encrypt(keyMD5, cleartext);
		System.out.println("Texto encriptado: " +textoEncriptado);
		System.out.println("Texto desencriptado: "+EncriptarDesencriptar.decrypt(keyMD5, textoEncriptado));
		
	}
	
	static String getCadenaAlfanumAleatoria (int longitud){
		String cadenaAleatoria = "";
		long milis = new java.util.GregorianCalendar().getTimeInMillis();
		Random r = new Random(milis);
		int i = 0;
		while ( i < longitud){
		char c = (char)r.nextInt(255);
		if ( (c >= '0' && c <='9') || (c >='a' && c <='z') ){
		cadenaAleatoria += c;
		i ++;
		}
		}
		return cadenaAleatoria;
		}

}
