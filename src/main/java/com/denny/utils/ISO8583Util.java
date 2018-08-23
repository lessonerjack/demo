package com.denny.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * Description:
 * User: denny.wang
 * Date: 2018-08-22 14:25
 */
@Slf4j
public class ISO8583Util {
    public String length = "";
    public static String TPDU = "6000020000";
    public static String header = "609800170615";
    public static String msgId = "0800";
    public static String send = "";

    public static void main(String[] s) {
        TreeMap filedMap = new TreeMap();//报文域
        filedMap.put("FIELD011", "001306");//受卡方系统跟踪号
        filedMap.put("FIELD041", "00000213");//注意这个域是变长域! ASCII
        filedMap.put("FIELD042", "913500078320001");//注意这个域定长域! ASCII
        filedMap.put("FIELD060", "00000013003");//注意这个域是变长域!
        filedMap.put("FIELD063", "3031203030303130303032");//注意这个域是变长域!

        String origin = getInitBitMap(filedMap);
        String str11 = (String) filedMap.get("FIELD011");
        String str41 = convertStringToHex((String) filedMap.get("FIELD041"));
        String str42 = convertStringToHex((String) filedMap.get("FIELD042"));
        String str60 = filedMap.get("FIELD060").toString();
        String str63 = filedMap.get("FIELD063").toString();
        int length60 =str60 .length();
        int length63 = str63.length()/2;
        log.info("str41:{}",str41);
        log.info("str41:{}",str42);
        log.info("str60:{}",str60);
        log.info("str63:{}",str63);
        log.info("length60:{},length:{}",length60,length63);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(TPDU).append(header).append(msgId).append(origin).append(str11).append(str41).append(str42).append("00"+length60).append(str60+"0").append("00"+length63).append(str63);
        send ="00"+ Integer.toHexString(stringBuffer.length()/2)+stringBuffer.toString();
        log.info("send:{}",send);
        log.info("send length:{}",send.length());
        log.info("send length:{}",hexStringToByte("10"));

//        System.out.println("Integer.toBinaryString(01)="+Integer.toBinaryString(01));
//        System.out.println("Integer.toBinaryString(012)="+Integer.toBinaryString(012));
//        System.out.println("Integer.toBinaryString(10)="+Integer.toBinaryString(10));
//        System.out.println("Integer.toBinaryString(0xa)="+Integer.toBinaryString(0xa));
//
//        System.out.println("Integer.toOctalString(0x12)="+Integer.toOctalString(0x12));
//        System.out.println("Integer.toOctalString(18)="+Integer.toOctalString(18));
//
//        System.out.println("Integer.toHexString(012)="+Integer.toHexString(012));
        System.out.println("Integer.toHexString(10)="+convertHexToString("1000"));


    }
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);

            stringBuilder.append(i + ":");

            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv + ";");
        }
        return stringBuilder.toString();
    }


    // 8583报文初始位图:128位01字符串
    public static String getInitBitMap(TreeMap filedMap) {
        String initBitMap =
                "00000000" + "00000000" + "00000000" + "00000000"
                        + "00000000" + "00000000" + "00000000" + "00000000";
        StringBuffer buffer = new StringBuffer(initBitMap);
        System.out.println(buffer.toString());
        if (filedMap != null) {
            Iterator it = filedMap.keySet().iterator();
            for (; it.hasNext(); ) {
                String fileName = (String) it.next();
                String fileValue = (String) filedMap.get(fileName);

                if (fileName.startsWith("FIELD") && fileValue.length() > 0) {
                    String fileNO = fileName.substring(5, 8);
                    int index = Integer.parseInt(fileNO);
                    buffer.replace(index - 1, index, "1");
                }
            }
        }
        System.out.println(buffer);
        StringBuffer bitmap = new StringBuffer();
        for (int i = 0; i < buffer.length() / 4; i++) {
            int binary = Integer.parseInt(buffer.substring(i * 4, i * 4 + 4), 2);
            switch (binary) {
                case 10:
                    bitmap.append("A");
                    break;
                case 11:
                    bitmap.append("B");
                    break;
                case 12:
                    bitmap.append("C");
                    break;
                case 13:
                    bitmap.append("D");
                    break;
                case 14:
                    bitmap.append("E");
                    break;
                case 15:
                    bitmap.append("F");
                    break;
                default:
                    bitmap.append(binary);
                    break;

            }
        }
        System.out.println(bitmap);
        return bitmap.toString();
    }

    /**
     * ASCII字符转16进制数
     * @param str
     * @return
     */
    public static String convertStringToHex(String str){

        char[] chars = str.toCharArray();

        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < chars.length; i++){
            hex.append(Integer.toHexString((int)chars[i]));
        }

        return hex.toString();
    }

    /**
     * 16进制数转ASCII字符
     * @param hex
     * @return
     */
    public static String convertHexToString(String hex){

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for( int i=0; i<hex.length()-1; i+=2 ){

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char)decimal);

            temp.append(decimal);
        }

        return sb.toString();
    }

    /**
     * 改变128位图中的标志为1
     *
     * @param fieldNo
     * @param res
     * @return
     */
    public static String change16bitMapFlag(String fieldNo, StringBuffer res) {
        int indexNo = Integer.parseInt(fieldNo);
        res = res.replace(indexNo-1,indexNo,"1");
        return res.toString();
    }
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static byte toByte(char c) {
        byte b = (byte) "0123456789abcdef".indexOf(c);
        return b;
    }


}
