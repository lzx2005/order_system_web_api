package com.lzx2005.tools;
/**
 * 账号密码加密工具
 * @author thomas.su
 *
 */
public class SecretTools {
	
	public static String secrect(String password,int userId){
		StringBuffer text = new StringBuffer("wisdom_");
		String initValue = text.append(password).append(userId).toString();
		String temp = MD5.encrypt(initValue);
		
		return temp;
	}
}
