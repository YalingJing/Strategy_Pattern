package com.strategy_pattern.strategy;

public class CaesarEncryption implements Encryption{
    String casartext;
    @Override
    public String ciphertext(String psd) {
        int n = 3;
        char c;
        for(int i=0;i<psd.length();i++)
        {
            c=psd.charAt(i);//取出字符串的每个字符
            if(c>='A'&&c<='Z')//当字符在"A"到"Z"之间的时候
            {
                if(c+n%26<='Z')//当提取的字符在Z之前的n位时
                {
                    casartext+=(char)(c+n%26);
                }
                else
                {
                    casartext+=(char)('A'+((n-('Z'-c)-1)%26));//孤立出来的n个字符
                }
            }
            else if(c>='a'&&c<='z')
            {
                if(c+n%26<='z')//当提取的字符在z之前的n位时
                {
                    casartext+=(char)(c+n%26);
                }
                else
                {
                    casartext+=(char)('a'+((n-('z'-c)-1)%26));//孤立出来的n个字符
                }
            }
            else if(c>='0'&&c<='9')
            {
                if(c+n%10<='9')
                {
                    casartext+=(char)(c+n%10);//当提取的字符在9之前的n位时
                }
                else
                {
                    casartext+=(char)('0'+((n-('9'-c)-1)%10));//孤立出来的n个字符
                }
            }
            else
            {
                casartext+=c;
            }
        }
        return casartext;
    }
}
