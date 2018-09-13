package com.denny.algorithm;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * Description:
 * User: denny.wang
 * Date: 2018-09-10 13:54
 */
public class DESedeUtils {
    public static final String  KEY_ALGORITHM="DESede";
    public static final String  CITHER_ALGORITHM="DESede/ECB/PKCS5Padding";

    private static Key toKey(byte[] key) throws Exception {
        DESedeKeySpec sedeKeySpec = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generateSecret(sedeKeySpec);
    }

    /**
     * 解密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data,byte[] key) throws Exception {
        Key k = toKey(key);
        Cipher instance = Cipher.getInstance(CITHER_ALGORITHM);
        instance.init(Cipher.DECRYPT_MODE,k);
        return instance.doFinal(data);
    }

    /**
     * 加密
     * @param date
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] date,byte[] key) throws Exception {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CITHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE,k);
        return cipher.doFinal(date);
    }

    public static byte[] initKey() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        kg.init(112);
        SecretKey secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }
}
