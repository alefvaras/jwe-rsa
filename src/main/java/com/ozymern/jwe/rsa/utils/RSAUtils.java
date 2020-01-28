package com.ozymern.jwe.rsa.utils;


import com.ozymern.jwe.rsa.constants.JWEConstant;
import com.ozymern.jwe.rsa.controllers.JWEController;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;


@Component
public class RSAUtils {

    Logger logger = LoggerFactory.getLogger(RSAUtils.class);

    public PublicKey getKeyJwk(String modulusString) throws NoSuchAlgorithmException, InvalidKeySpecException {

        logger.info("[getKeyJwk] init");
        byte[] modulusBytes = Base64.decodeBase64(modulusString);
        byte[] exponentBytes = Base64.decodeBase64(JWEConstant.EXPONENT);
        BigInteger modulus = new BigInteger(1, modulusBytes);
        BigInteger publicExponent = new BigInteger(1, exponentBytes);

        RSAPublicKeySpec rsaPubKey = new RSAPublicKeySpec(modulus, publicExponent);
        KeyFactory fact = KeyFactory.getInstance(JWEConstant.RSA);
        PublicKey pubKey = fact.generatePublic(rsaPubKey);

        logger.info("[getKeyJwk] end");

        return pubKey;
    }

    public String cipherJwe(PublicKey pubKey, String message) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        logger.info("[cipherJwe] init");

        Cipher cipher = Cipher.getInstance(JWEConstant.RSA_OAEP);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] plainBytes = message.getBytes("UTF-8");
        byte[] cipherData = cipher.doFinal(plainBytes);
        String encryptedStringBase64 = Base64.encodeBase64String(cipherData);

        logger.info("[cipherJwe] end");
        return encryptedStringBase64;

    }
}
