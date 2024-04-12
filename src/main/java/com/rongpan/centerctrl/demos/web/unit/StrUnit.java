package com.rongpan.centerctrl.demos.web.unit;

import java.io.UnsupportedEncodingException;

public class StrUnit {
    public static final String US_ASCII = "US-ASCII";
    /** *//** 将字符编码转换成US-ASCII码     */
    public static String toASCII(String str) {
        String res = "error";
        try {
            res = StrUnit.changeCharset(str,US_ASCII);
        } catch (UnsupportedEncodingException e) {
        }
        return res;
    }
    private static String changeCharset(String str, String newCharset) throws UnsupportedEncodingException{
        if(str != null) {
            //用默认字符编码解码字符串。与系统相关，中文windows默认为GB2312
            byte[] bs = str.getBytes();
            return new String(bs, newCharset);    //用新的字符编码生成字符串
        }
        return null;
    }

}
