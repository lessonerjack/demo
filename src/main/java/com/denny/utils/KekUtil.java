package com.denny.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * Description:
 * User: denny.wang
 * Date: 2018-08-23 9:58
 */
@Slf4j
public class KekUtil {
    public static String plaintext = "123456789012345f";
    public static String ciphertext = "";


    public static StringBuffer getStringToBinary(String hex) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < hex.length(); i++) {
            String s = getHexByChar(hex.toUpperCase().charAt(i));
            log.info("s:{}", s);
            buffer.append(s);
        }
        log.info("buffer:{}", buffer);
        return buffer;
    }

    /**
     * 不够位数的在前面补0，保留num的长度位数字
     *
     * @param code
     * @return
     */
    private static String autoGenericCode(String code, int num) {
        String result = "";
        // 保留num的位数
        // 0 代表前面补充0
        // num 代表长度为4
        // d 代表参数为正数型
        result = String.format("%0" + num + "d", Integer.parseInt(code));

        return result;
    }

    /**
     * 单字符转化成2进制
     *
     * @param charAt
     * @return
     */
    private static String getHexByChar(char charAt) {
        switch (charAt) {
            case '0':
                return "0000";
            case '1':
                return "0001";
            case '2':
                return "0010";
            case '3':
                return "0011";
            case '4':
                return "0100";
            case '5':
                return "0101";
            case '6':
                return "0110";
            case '7':
                return "0111";
            case '8':
                return "1000";
            case '9':
                return "1001";
            case 'A':
                return "1010";
            case 'B':
                return "1011";
            case 'C':
                return "1100";
            case 'D':
                return "1101";
            case 'E':
                return "1110";
            case 'F':
                return "1111";

        }
        return null;
    }

    /**
     * 2位16进制数分组
     *
     * @param origin
     * @return
     */
    public static String[] getArrayByBinaryString(String origin) {
        int length = origin.length() / 8;
        String[] str = new String[length];
        for (int i = 0; i < length; i++) {
            str[i] = origin.substring(i * 8, i * 8 + 8);
        }
        return str;
    }

    /**
     * 奇偶校验
     *
     * @param data
     * @return
     */
    public static String judgeOddEven(String data) {
        if (StringUtils.isBlank(data)) {
            return null;
        }
        int count = 0;
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '1') {
                count++;
            }
        }
        if (count % 2 == 0) {
            if (data.charAt(7) == '1') {
                data = data.substring(0, 7) + "0";
            } else {
                data = data.substring(0, 7) + "1";
            }
            log.info("偶数1:{}", data);
            return data;
        } else {
            log.info("奇数1:{}", data);
            return data;

        }
    }

    /**
     * 二进制转16进制数
     *
     * @param s
     * @return
     */
    public static String getHexByBinaryString(String s) {
        if (StringUtils.isBlank(s) || s.length() > 4) {
            return null;
        }
        int hex = Integer.parseInt(s, 2);
        switch (hex) {
            case 10:
                return "A";
            case 11:
                return "B";
            case 12:
                return "C";
            case 13:
                return "D";
            case 14:
                return "E";
            case 15:
                return "F";
            default:
                return String.valueOf(hex);
        }
    }

    /**
     * 组建缺少的填充F
     * @param isPublicKey
     * @param key
     * @param plaintext
     * @return
     */
    public static String fillPkcs(boolean isPublicKey, String key, String plaintext) {
        StringBuffer pksc = new StringBuffer();
        int keyLength = key.length();//密钥长度
        int plaintextLength = plaintext.length();//明文长度
        int otherLength = 18;//其他项内容长度
        int paddingLength = keyLength - otherLength - plaintextLength;
        if (isPublicKey) {
            //公钥填充非0
            pksc.append("02");
            for (int i = 0; i < 16; i++) {
                pksc.append("F");
            }
            for (int i = 0; i < 32; i++) {

                int random = new Random().nextInt(8) + 1;
                pksc.append(random);
            }
            for (int i = 0; i < paddingLength - 16 - 32; i++) {
                pksc.append("F");
            }
        } else {
            //填充FF
            pksc.append("01");
            for (int i = 0; i < paddingLength; i++) {
                pksc.append("F");
            }
        }
        return pksc.toString();
    }

    /**
     * 构建pkcs11填充的明文
     * @param key
     * @param oddPlaintText
     * @return
     */
    public static String buildContent(boolean isPublicKey,String key,String oddPlaintText){
        //填充
        int plaintextLength = oddPlaintText.length();
        Integer finalDataLength;
        StringBuffer padding = new StringBuffer();
        finalDataLength = 8 + plaintextLength;
        String pkcs11 = KekUtil.fillPkcs(isPublicKey, key, oddPlaintText);
        padding.append("00");
        //padding.append("02");
        padding.append(pkcs11);
        padding.append("00").append("30");
        padding.append(Integer.toHexString(finalDataLength/2))
                .append("04")
                .append(Integer.toHexString(oddPlaintText.length()/2))
                .append(oddPlaintText)
                .append("0408");
        return padding.toString();
    }

}
