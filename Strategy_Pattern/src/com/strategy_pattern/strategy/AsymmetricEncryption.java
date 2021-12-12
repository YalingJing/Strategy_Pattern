package com.strategy_pattern.strategy;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.*;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class AsymmetricEncryption implements Encryption{
    String rsatext;
    private static String PUBLIC_KEY_FILE = "PublicKey";
    private static String PRIVATE_KEY_FILE = "PrivateKey";
    private static void createKeyPair() throws Exception {
        //使用RSA算法获得密钥对生成器对象keyPairGenerator
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        //设置密钥长度为1024
        keyPairGenerator.initialize(1024);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //获取公钥
        Key publicKey = keyPair.getPublic();
        //获取私钥
        Key privateKey = keyPair.getPrivate();
        //保存公钥对象和私钥对象为持久化文件
        ObjectOutputStream oos1 = null;
        ObjectOutputStream oos2 = null;
        try {
            oos1 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
            oos2 = new ObjectOutputStream(
                    new FileOutputStream(PRIVATE_KEY_FILE));
            oos1.writeObject(publicKey);
            oos2.writeObject(privateKey);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            oos1.close();
            oos2.close();
        }
    }
    @Override
    public String ciphertext(String str) throws Exception {
        createKeyPair();
        Key publicKey = null;
        //读取持久化的公钥对象
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
            publicKey = (Key) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            ois.close();
        }
        //获取一个加密算法为RSA的加解密器对象cipher。
        Cipher cipher = Cipher.getInstance("RSA");
        //设置为加密模式,并将公钥给cipher。
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        //获得密文
        byte[] secret = cipher.doFinal(str.getBytes());
        //进行Base64编码
        rsatext = new BASE64Encoder().encode(secret);
        return rsatext;
    }
}
