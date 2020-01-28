package com.ozymern.jwe.rsa.controllers;


import com.ozymern.jwe.rsa.domain.CipherText;
import com.ozymern.jwe.rsa.services.JWEService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping(JWEController.URI_BASE)
public class JWEController {

    public static final String URI_BASE = "/api/v1";

    Logger logger = LoggerFactory.getLogger(JWEController.class);

    @Autowired
    private JWEService jweService;

    @PostMapping("/cipher")
    public ResponseEntity<CipherText> cipherMessage(@RequestBody  CipherText cipherText) throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        logger.info("[cipherMessage] init, message {}",cipherText.getMessage());

        cipherText.setMessage(jweService.cipher(cipherText.getMessage()));
        return new ResponseEntity<>(cipherText, HttpStatus.OK);
    }


}
