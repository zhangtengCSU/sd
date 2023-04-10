package com.reach.common.utils;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.util.text.BasicTextEncryptor;

public class JasyptUtil {
    public static void main(String[] args) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //1.salt：jasypt.encryptor.password=zrxJuly
        textEncryptor.setPassword("reachtothemoon");
        //2.data
        String user = textEncryptor.encrypt("reach");
        String password = textEncryptor.encrypt("CoiLxJi3-9");
        //replace by ENC(jdbcUrl)   ENC(password)
        System.out.println("user:"+user);
        System.out.println("password:"+password);
    }
}