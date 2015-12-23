package org.cobro.neonsign.utility;

import sun.misc.*;
import java.io.*;

/**
 * Encoding/Decoding을 수행하는 클래스
 * 실제 던져진 해쉬함수에 의한 결과를 System.out으로 찍게 되면 찌그러진 코드형태로 나오게 되는데
 * 이것을 우리 눈으로 비교하여 String문자비교를 통하여 추후 사용할 수 있도록 Base64 인코딩
 * @author JeSeong Lee
 */
public class Base64Util {
	public Base64Util() {
	}

	/**
	 * Base64Encoding을 수행
	 */

	public static String encode(byte[] encodeBytes) {
		BASE64Encoder base64Encoder = new BASE64Encoder();
		ByteArrayInputStream bin = new ByteArrayInputStream(encodeBytes);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		byte[] buf = null;
		try {
			base64Encoder.encodeBuffer(bin, bout);
		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
		buf = bout.toByteArray();
		return new String(buf).trim();
	}

	/**
	 * Base64Decoding 수행
	 */
	public static byte[] decode(String strDecode) {
		BASE64Decoder base64Decoder = new BASE64Decoder();
		ByteArrayInputStream bin = new ByteArrayInputStream(
				strDecode.getBytes());
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		byte[] buf = null;
		try {
			base64Decoder.decodeBuffer(bin, bout);
		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
		buf = bout.toByteArray();
		return buf;
	}
}