package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SignUtil {
	/**
	 *  这里填写TOKEN 的值，要与微信中填写的TOKEN值一致。
	 * 
	 */
	private static String token = "TsehLoveAmy";
	
	public static boolean checkSignature(String signature,String timestamp,String nonce) {
		String[] arr = new String[]{token,timestamp,nonce};
		
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for(int i = 0;i< arr.length; i++)
		{
			content.append(arr[i]);
		}
		MessageDigest msgDigest = null;
		String tempString = null;
		
		try {
			msgDigest = MessageDigest.getInstance("SHA-1");
			byte[] digest = msgDigest.digest(content.toString().getBytes());
			tempString = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		content = null;
		return tempString != null ? tempString.equals(signature.toUpperCase()):false;
	}
	/*
	 * 将字节数组转换为 十六进制字符串
	 */
	private static String byteToStr(byte[] digest) {
		// TODO Auto-generated method stub		
		String strdig = "";
		for (int i = 0; i < digest.length; i++) {
			strdig += byteToHexStr(digest[i]);
		}
		return strdig;
		
	}
	
	/*
	 * 将字节转换为十六进制字符串
	 */
	private static String byteToHexStr(byte b) {
		// TODO Auto-generated method stub
		char[] digit = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		char[] tempArr = new char[2];
		tempArr[0] = digit[(b >>> 4) & 0X0F];
		tempArr[1] = digit[b & 0X0F];
		String string = new String(tempArr);
		return string;
	}
	
	
	
}
