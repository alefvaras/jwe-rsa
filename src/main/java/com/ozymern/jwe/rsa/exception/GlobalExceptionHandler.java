package com.ozymern.jwe.rsa.exception;

import com.ozymern.jwe.rsa.services.impl.JWEServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({BadPaddingException.class, NoSuchAlgorithmException.class, IllegalBlockSizeException.class, UnsupportedEncodingException.class, NoSuchPaddingException.class, InvalidKeyException.class, InvalidKeySpecException.class})
    public ResponseEntity<ExceptionMessage> handleSecurityException(
            Exception ex, WebRequest request) {

        logger.info("[handleSecurityException] error: {}",ex);

        return new ResponseEntity<>(
                new ExceptionMessage("015", ex.toString()), new HttpHeaders(), HttpStatus.OK);
    }

}
