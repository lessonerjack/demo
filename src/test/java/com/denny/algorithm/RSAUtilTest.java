package com.denny.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
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
            String content = "0002FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0030140410133457799113345779911334577991130408";
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
        String test = "5413545";
        String source = "0002FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0002FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0002FFFFFFFFFF";
        System.out.println(source.length());
        byte[] testByte = source.getBytes();
        System.out.println(new String(testByte, "UTF-8"));
        BigInteger testByteInt = new BigInteger(testByte);

        byte[] enTestByte = testByteInt.modPow(pubE, m).toByteArray();//将明文加密为密文
        byte[] deTestByte = new BigInteger(enTestByte).modPow(priE, m).toByteArray();//将密文解密为明文
        System.out.println(new String(deTestByte));
    }


    @Test
    public void testRadix() {
        System.out.println(new BigInteger("1010", 2).toString(16));
        System.out.println(new BigInteger("2", 16).toString(2));
        System.out.println(new BigInteger("0000000000100000000000000000000000000000110000000000000000010010",2)
                .toString(16));
        log.info("hex to binary:{}",new BigInteger("0020000000C00012",16).toString(2));
    }


}
