package com.denny.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Description:
 * User: denny.wang
 * Date: 2018-08-23 10:13
 */
@Slf4j
public class KekUtilTest {

    private String hex = "01010101010101010101010101010101";

    @Test
    public void getStringToBinary() {
        StringBuffer atomBuferr = new StringBuffer();
        StringBuffer oddCheckResult = new StringBuffer();
        StringBuffer stringToBinary = KekUtil.getStringToBinary(hex);
        String[] binaryArrs = KekUtil.getArrayByBinaryString(stringToBinary.toString());
        for (int i = 0; i < binaryArrs.length; i++) {
            String s = binaryArrs[i];
            String oddStr = KekUtil.judgeOddEven(s);
            atomBuferr.append(oddStr);
        }
        int hexLength = atomBuferr.length() / 4;
        for (int i = 0; i < hexLength; i++) {
            String tmp = atomBuferr.substring(4 * i, 4 * i + 4);
            String hexByBinary = KekUtil.getHexByBinaryString(tmp);
            oddCheckResult.append(hexByBinary);
        }
        log.info("oddCheckResult:{}", oddCheckResult.toString());
        Assert.assertEquals(oddCheckResult.toString(), "01010101010101010101010101010101");
        String pubKey = "CA74D577D6A0CD88906608B4989540B405BAFACEC40997A045666A02B5022E0823BE540C5AB325A4EF4CB2675859BBE6E52BBB39F4AFC6F3993ED689D9390667AFE97392D481AB304028CCFDDC499EB212E4E318FEB63B1C03D5254767184A1E5E6C7104167B30A760F1B4FF02F1E47547B50FF9CBA77AC02EC4572D1B32D763";
        String content = KekUtil.buildContent(pubKey, oddCheckResult.toString());
        log.info("content:{}", content);
        String a="0002FFFFFFFFFFFFFFFF17686742719498372975689326124872FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0030140410010101010101010101010101010101010408";
        Assert.assertEquals(content.length(),a.length());
    }

}
