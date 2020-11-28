package com.liornegri.unbound.ms.api.exception;

import com.liornegri.unbound.ms.data.model.crypto.exception.CryptoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(final HttpClientErrorException e) {
        return new ResponseEntity<>(e.getStatusText(), e.getStatusCode());
    }

    @ExceptionHandler(value = CryptoException.class)
    public ResponseEntity<String> handleCryptoException(final CryptoException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
