package com.sirdc.modules.util;

import java.security.MessageDigest;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sirdc.modules.entity.sys.SysLogin;


/**
 * 密码加密
 * @author 彭伟煌(weihuang.peng@imethsoft.com)
 * @version Revision: 1.0.0 Oct 25, 2014
 */
@Service
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Value("${password.hashAlgorithmName}")
    private String hashAlgorithmName = "SHA-256";//默认是md5加密
    
    @Value("${password.hashIterations}")
    private int hashIterations = 2;//默认密码迭代两次

    /**
     * 对用户设置加密钥匙，并对密码进行加密
     * @param user
     */
    public void encryptPassword(SysLogin user) {
        user.setSaltKey(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
	        hashAlgorithmName,
	        user.getPassword(),
	        ByteSource.Util.bytes(user.getSaltKey()),
	        hashIterations
        ).toHex();

        user.setPassword(newPassword);
    }
    
    /**
     * 返回加密后的结果
     * @author: weihuang.peng
     * @param password
     * @param salt
     * @return
     */
    public String encryptPassResult(String password, String salt) {
		String newPassword = new SimpleHash(hashAlgorithmName,
				password, 
				ByteSource.Util.bytes(salt),
				hashIterations).toHex();
		return newPassword;
    }
    
    /**
	 * MD5 加密
	 */
	public final static String getMD5Str(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			 
			return null;
		}
	}
}