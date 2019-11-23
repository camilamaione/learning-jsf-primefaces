package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncrypter {

	public static String convertToMd5(String valor) {
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("MD5");

			// Convert to byte array in MD5
			byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));

			// Convert to hexadecimal, so it can be persisted in database
			StringBuffer sb = new StringBuffer();
			for (byte b : valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
