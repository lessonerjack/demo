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
        String pubKey = "B02D8945C76E0E5DDB120C8A2352F340F09FFB64053AE8281924FD5C1EB8838CCEC30D4A16B1D8BD86FAB824420DFB055C81DA8EEDBDA65113F9DAB6044765D8499CF643EE7EB776EC4A936D9695073D4BF6BE93322ED7389C27DCDE9F8BC2954CFC7A9C3E2579B69A9DBD7C363F95EA53EEA204035B1B5F59598D65FD69DB11";
        String content = KekUtil.buildContent(true,pubKey, oddCheckResult.toString());
        log.info("content:{}", content);
        String a="0002FFFFFFFFFFFFFFFF17686742719498372975689326124872FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0030140410010101010101010101010101010101010408";
        Assert.assertEquals(content.length(),a.length());
    }

}
