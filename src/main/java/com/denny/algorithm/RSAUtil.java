package com.denny.algorithm;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

/**
 * Description:RSA加密解密算法
 * User: denny.wang
 * Date: 2018-08-23 16:43
 */
@Slf4j
public class RSAUtil {
        private static String mod="CA74D577D6A0CD88906608B4989540B405BAFACEC40997A045666A02B5022E0823BE540C5AB325A4EF4CB2675859BBE6E52BBB39F4AFC6F3993ED689D9390667AFE97392D481AB304028CCFDDC499EB212E4E318FEB63B1C03D5254767184A1E5E6C7104167B30A760F1B4FF02F1E47547B50FF9CBA77AC02EC4572D1B32D763";
        private static String publicE="65537";
        private static String privateE="86F88E4FE46B33B0604405CDBB0E2B22AE7CA73482B10FC02E4446AC78AC1EB017D438083C776E6DF4DDCC44E59127EF43727CD14DCA84A26629E45BE6260443EF573B2B71C73861E5BEEC5960720FF75C90B190FF5B9A01946CEE0C383D0E0128BDADD09F263010B869CA9B619A09A9215A3B42F568DD2AF738C1944A31E99B";
    public static void init() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey aPublic = (RSAPublicKey)keyPair.getPublic();
        RSAPrivateKey aPrivate = (RSAPrivateKey)keyPair.getPrivate();
        log.info("public Encoded:{}", Base64.encode(aPublic.getEncoded()));
        log.info("private Encoded:{}",Base64.encode(aPrivate.getEncoded()));
        log.info("public mod:{}",aPublic.getModulus());
        log.info("public key:{}",aPublic.getPublicExponent());
        log.info("private mod:{}",aPrivate.getModulus());
        log.info("private key:{}",aPrivate.getPrivateExponent());

        // 获取公私钥n
        BigInteger m = new BigInteger(mod,16);
        // 获取公钥e
        BigInteger pubE = new BigInteger(publicE,16);
        // 密文
        BigInteger priE = new BigInteger(privateE,16);
        log.info("mod:{}",m);
        log.info("pube:{}",pubE);
        log.info("prie:{}",priE);
        RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(m,pubE);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(rsaPublicKeySpec);
        log.info("prie:{}",Base64.encode(publicKey.getEncoded()));

        RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(m,priE);
        PrivateKey privateKey = keyFactory.generatePrivate(rsaPrivateKeySpec);
        String encrypt = encrypt("12344", publicKey);
        decrypt(encrypt,privateKey);


    }
    public static String encrypt(String data,PublicKey publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        byte[] bytes = cipher.doFinal("123".getBytes());
        return Base64.encode(bytes);
    }

    public static String decrypt(String data,PrivateKey privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        byte[] bytes1 = cipher.doFinal(data.getBytes());
        log.info("bytes:{}",Base64.encode(bytes1));
        return Base64.encode(bytes1);
    }

}
