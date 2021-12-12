package com.strategy_pattern.strategy;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestAlgorithmEncryption implements Encryption{
    String md5text="";
    @Override
    public String ciphertext(String psd) {
        if (null == psd) {
            psd = "";
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(psd.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuilder builder = new StringBuilder(32);
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    builder.append("0");
                }
                builder.append(Integer.toHexString(i));
            }
            md5text = builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5text;
    }
}
