package com.denny.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.util.Map;

/**
 * Description:
 * User: denny.wang
 * Date: 2018-08-23 17:10
 */
@Slf4j
public class RSAUtilTest {
    @Test
    public void init() {
        try {
            String content = "01010101010101010101010101010101";
            Map<String, Object> keyMap = RSAUtil.init();
            byte[] encrypt = RSAUtil.encrypt(content, (PublicKey) keyMap.get(RSAUtil.PUBLIC_KEY));
            String decrypt = RSAUtil.decrypt(encrypt, (PrivateKey) keyMap.get(RSAUtil.PRIVATE_KEY));
            log.info("encryptData:{}", new BigInteger(encrypt).toString(16));
            log.info("decrypt:{}", decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void rsa() throws UnsupportedEncodingException {
        String mod = "B02D8945C76E0E5DDB120C8A2352F340F09FFB64053AE8281924FD5C1EB8838CCEC30D4A16B1D8BD86FAB824420DFB055C81DA8EEDBDA65113F9DAB6044765D8499CF643EE7EB776EC4A936D9695073D4BF6BE93322ED7389C27DCDE9F8BC2954CFC7A9C3E2579B69A9DBD7C363F95EA53EEA204035B1B5F59598D65FD69DB11";

        String publicE = "3";

        String privateE = "7573B0D92F9EB43E920C085C178CA22B4B155242AE27457010C35392BF25AD0889D75E316476907E59FC7AC2D6B3FCAE3DABE709F3D3C4360D513C79582F9939C093929082EEE72B125B51B363D217EEE84656C6E4B3C9636D62B00035A29C944F06E513CDC52DE8E8DF587F5F17ABE2C86AC2ABB877F8FB14EB30E89D34B543";

        // 获取公私钥n
        BigInteger m = new BigInteger(mod, 16);
        // 获取公钥e
        BigInteger pubE = new BigInteger(publicE);
        // 密文
        BigInteger priE = new BigInteger(privateE, 16);
        System.out.println(m);
        System.out.println(pubE);
        System.out.println(priE);
//        String test = "5413545";
//        String source = "0002FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0030140410133457799113345779911334577991130408";
//        System.out.println(source.length());
//        byte[] testByte = source.getBytes();
//        //System.out.println(new String(testByte, "UTF-8"));
//        BigInteger testByteInt = new BigInteger(testByte);
//
//        byte[] enTestByte = testByteInt.modPow(pubE, m).toByteArray();//将明文加密为密文
//        byte[] deTestByte = new BigInteger(enTestByte).modPow(priE, m).toByteArray();//将密文解密为明文
//        System.out.println(toHexString1(enTestByte));
    }
    /**
     * 数组转成十六进制字符串
     * @param b
     * @return HexString
     */
    public static String toHexString1(byte[] b){
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < b.length; ++i){
            buffer.append(toHexString1(b[i]));
        }
        return buffer.toString();
    }
    public static String toHexString1(byte b){
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1){
            return "0" + s;
        }else{
            return s;
        }
    }

    @Test
    public void testRadix() {
        //System.out.println(new BigInteger
        //
        // ("80CBA506BB12183A6A907CC56C241945190B2632C6048427EE6A3D46BF20B6182439F757D6E003C48AE6C1307F151AC6770F9CA048FB2732F4B776B56BCC2C99427A00367DA4E650D295F03EC2FB83F77848F5A1D571365E307AF6FCD2710EAA632A16D8EA389DDF546D14AD5C490902F33153FD59D48FDE24E559BAC98C0EB7", 16).toString(16));
        System.out.println(new BigInteger
                ("C52A", 16).toString(10));
        //System.out.println(new BigInteger("0000000000100000000000000000000000000000110000000000000000010010",2).toString(16));
        //log.info("hex to binary:{}",new BigInteger("0020000000C00012",16).toString(2));
    }

    @Test
    public void testBigInteger(){
        String s = "CA74D577D6A0CD88906608B4989540B405BAFACEC40997A045666A02B5022E0823BE540C5AB325A4EF4CB2675859BBE6E52BBB39F4AFC6F3993ED689D9390667AFE97392D481AB304028CCFDDC499EB212E4E318FEB63B1C03D5254767184A1E5E6C7104167B30A760F1B4FF02F1E47547B50FF9CBA77AC02EC4572D1B32D763";
        BigInteger result =
                new BigInteger
                        ("142169706447985283259105593968873887021350949506858164402734207073758557148502884308385106815238853177946014147076494048125293950016121752365344076049482259754307115205915813991433970921506998591270208136591780524508714647346532148370396067629827790250119050243448931308496252340805501424165483982078619080547")
        ;
        log.info("str:{}",new BigInteger(s,16));
        Assert.assertEquals(new BigInteger(s,16),result);
    }
    @Test
    public void testTrim(){
       String ss = "00 8d 60 00 03 00 00 61 22 00 00 00 00 04 00 70" +
               "24 06 80 02 c0 82 11 16 62 14 65 10 05 85 43 35" +
               "00 00 00 00 00 00 01 00 00 00 73 89 30 12 05 10" +
               "00 10 00 39 38 30 30 30 30 30 34 36 37 39 31 33" +
               "35 30 30 38 39 37 30 33 30 30 30 38 31 35 36 00" +
               "45 95 05 08 80 04 e0 00 9f 1e 08 41 4e 30 30 35" +
               "38 39 32 9f 10 13 07 02 01 03 a0 20 02 01 0a 01" +
               "00 00 00 00 00 23 73 1c 67 9f 36 02 00 2f 00 14" +
               "22 00 00 02 00 05 01 43 8b 27 05 9b 9b f5 d8   ";
       log.info(ss.replace(" ","").toUpperCase());
    }

    @Test
    public void testKey(){
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPrivateKey aPrivate = (RSAPrivateKey)keyPair.getPrivate();
            log.info(new BASE64Encoder().encodeBuffer(aPrivate.getEncoded()) );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }



}
