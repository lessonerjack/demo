package com.denny.algorithm;

import org.junit.Test;

/**
 * Description:
 * User: denny.wang
 * Date: 2018-08-23 17:10
 */
public class RSAUtilTest {
    @Test
    public void init(){
        try {
            RSAUtil.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
