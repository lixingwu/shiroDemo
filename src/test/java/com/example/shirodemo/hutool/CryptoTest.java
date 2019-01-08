package com.example.shirodemo.hutool;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.junit.Test;

public class CryptoTest {
    @Test
    public void AES() {
        String pwd = "小明@123456@admin@0";

        //随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        Console.log("密钥：" + key);

        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);

        //加密为16进制表示
        String encryptHex = aes.encryptHex(pwd);
        Console.log("密文：" + encryptHex);

        //解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);

        Console.log("明文：" + decryptStr);
    }

    @Test
    public void Sign() {

        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);

        //签名内容
        byte[] data = "{\"name\":\"lixingwu\"}".getBytes();

        // 签名证书
        byte[] signed = sign.sign(data);
        Console.log(signed);

        //根据证书验签
        Console.log(sign.verify(data, signed));
    }

}
