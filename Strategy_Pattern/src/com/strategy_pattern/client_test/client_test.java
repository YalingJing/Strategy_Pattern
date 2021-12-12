package com.strategy_pattern.client_test;

import com.strategy_pattern.context.Password;
import com.strategy_pattern.strategy.Encryption;

import java.util.Scanner;

public class client_test {
    public static void main(String args[]) throws Exception {
        Password p = new Password();
        System.out.println("请输入原始密码：");
        Scanner input = new Scanner(System.in);
        String psd = input.nextLine();
        p.setPsd(psd);
        Encryption encryption;
        encryption = (Encryption) XMLUtil.getBean();
        p.setEncryption(encryption);
        String cipherpsd = p.getEncryption();
        System.out.println("加密后的密码为：");
        System.out.println(cipherpsd);
    }
}
