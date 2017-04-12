package Encode;

import java.io.UnsupportedEncodingException;

public class urlEncode {
	public static String urlEncodeUTF8(String source){
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return result;
	}
}
