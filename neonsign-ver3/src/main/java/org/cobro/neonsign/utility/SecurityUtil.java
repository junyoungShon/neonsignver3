package org.cobro.neonsign.utility;

import java.security.*;
/**
 * 자동로그인 위한 시큐리티 유틸
 * 자바에서 java.security패키지에 보면 여러가지 보안에 관련된 클래스를 사용할 수 있도록 구성
 * 거기에 MessageDigest클래스가 해당 알고리즘을 이용하여 digest를 할 수 있도록 구성해준다.
 * @author JeSeong Lee
 */

public class SecurityUtil {
	/**
	 * * byte[] ret = HashUtil.digest("MD5", "abcd".getBytes()); 처럼 호출
	 */
	public static byte[] digest(String alg, byte[] input)
			throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(alg);
		return md.digest(input);
	}

	public static String getCryptoMD5String(String inputValue) throws Exception {
		if (inputValue == null) {
			throw new Exception(
					"Can't conver to Message Digest 5 String value!!");
		}
		byte[] ret = digest("MD5", inputValue.getBytes());
		String result = Base64Util.encode(ret);
		return result;
	}
}