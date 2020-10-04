package br.com.dilas.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ContaBloqueadaException extends RuntimeException {
    public ContaBloqueadaException() {
    }

    public ContaBloqueadaException(String message) {
        super(message);
    }

    public ContaBloqueadaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContaBloqueadaException(Throwable cause) {
        super(cause);
    }

    public ContaBloqueadaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
