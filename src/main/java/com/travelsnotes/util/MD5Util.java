package com.travelsnotes.util;

import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class MD5Util {
        /**利用MD5进行加密*/
        public String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
            //确定计算方法
            MessageDigest md5= MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
            return newstr+"zzl";
        }

        /**判断用户密码是否正确
         *newpasswd 用户输入的密码
         *oldpasswd 正确密码*/
        public boolean checkPassword(String newpasswd,String oldpasswd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
            if(EncoderByMd5(newpasswd).equals(oldpasswd))
                return true;
            else
                return false;
        }
}
