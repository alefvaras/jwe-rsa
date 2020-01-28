package com.ozymern.jwe.rsa.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionMessage {

    private String code;
    private String message;
}
