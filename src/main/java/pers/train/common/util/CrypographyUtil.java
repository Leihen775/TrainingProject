package pers.train.common.util;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class CrypographyUtil {

	
	public static void main(String[] args){
		/*System.out.println(MD5("java123","123456"));
		System.out.println(MD5("jack","123456"));
		System.out.println(MD5("marry","123456"));*/
	}
	
	/**
	 * base64加密
	 */
	public static String encodeBase64(String s){
		return Base64.encodeToString(s.getBytes());
	}
	
	public static String  decodeBase64(String s){
		return Base64.decodeToString(s);
	}
	
	
	/**
	 * Md5加密
	 */
	
	public static  Object MD5(String username,String password,String saltz){
	   // return new Md5Hash(s,salt).toString();
	    
	    //hashIterations加密的次数1024
		String hashAlgorithmName = "MD5";
		Object credentials = (Object)password;
		//加点盐
		Object salt = ByteSource.Util.bytes(saltz);
		int hashIterations = 1024;
		
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		return result;
	}
	
	
}
