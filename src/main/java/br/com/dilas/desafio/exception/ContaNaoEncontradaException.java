package br.com.dilas.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ContaNaoEncontradaException extends RuntimeException {
    public ContaNaoEncontradaException() {
    }

    public ContaNaoEncontradaException(String message) {
        super(message);
    }

    public ContaNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContaNaoEncontradaException(Throwable cause) {
        super(cause);
    }

    public ContaNaoEncontradaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
