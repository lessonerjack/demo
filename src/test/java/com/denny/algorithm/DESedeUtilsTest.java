package com.denny.algorithm;

import com.denny.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigInteger;

/**
 * Description:
 * User: denny.wang
 * Date: 2018-09-10 14:15
 */
@Slf4j
public class DESedeUtilsTest {

    @Test
    public void  testAlgorithm(){
        try {
            byte[] initKey = DESedeUtils.initKey();
            byte[] data = "13125125136".getBytes("utf-8");
            byte[] encrypt = DESedeUtils.encrypt(data, initKey);
            byte[] decrypt = DESedeUtils.decrypt(encrypt, initKey);
            log.info("initKey:{}",new BigInteger(initKey).toString(16));
            log.info("encrypt:{}",new BigInteger(encrypt).toString(16));
            log.info("decrypt:{}",new BigInteger(decrypt).toString(16));
            log.info("decrypt:{}",new String(decrypt, Constant.CHARSER_SET_UTF8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void  testChange(){
        BigInteger bigInteger = new BigInteger("333531393836363239353232",16);
        log.info(bigInteger.toString());
    }
}
