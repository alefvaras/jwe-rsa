package com.ozymern.jwe.rsa.services.impl;

import com.ozymern.jwe.rsa.controllers.JWEController;
import com.ozymern.jwe.rsa.services.JWEService;
import com.ozymern.jwe.rsa.utils.RSAUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

@Service
public class JWEServiceImpl implements JWEService {

    @Value("${modulus.string}")
    private String modulusString;

    @Autowired
    private RSAUtils rsaUtils;

    Logger logger = LoggerFactory.getLogger(JWEServiceImpl.class);

    @Override
    public String cipher(String message) throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, UnsupportedEncodingException {
        logger.info("[cipher] init");

        PublicKey pubKey= rsaUtils.getKeyJwk(modulusString);

        String cipherText=rsaUtils.cipherJwe(pubKey,message);
        logger.info("[cipher] end");
        return cipherText;
    }
}
