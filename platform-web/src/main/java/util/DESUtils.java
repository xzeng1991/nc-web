package util;

import java.nio.charset.Charset;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.apache.commons.codec.binary.Base64;

public class DESUtils {
	private static Key key;
	private static String KEY_STR = "myKey";
	
	// 生成DES加解密使用的密钥
	static{
		try{
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			generator.init(new SecureRandom(KEY_STR.getBytes()));
			key = generator.generateKey();
			generator = null;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	//对字符串进行DES加密，返回BASE64编码的加密字符串
	public static String getEncryptString(String str){
		Base64 base64en = new Base64();
		try{
			byte[] strBytes = str.getBytes(Charset.forName("utf-8"));
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptStrBytes = cipher.doFinal(strBytes);
			return base64en.encodeAsString(encryptStrBytes);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	// 对BASE64编码加密字符串进行解密，返回解密后的字符串
	public static String getDecryptString(String str){
		try{
			byte[] strBytes = Base64.decodeBase64(str);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decryptStrBytes = cipher.doFinal(strBytes);
			return new String(decryptStrBytes,"utf-8");
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
