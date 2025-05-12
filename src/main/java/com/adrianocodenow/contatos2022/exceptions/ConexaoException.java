package com.adrianocodenow.contatos2022.exceptions;

import com.adrianocodenow.contatos2022.utils.Msg;

/**
 *
 * @author ElderBR
 */
public class ConexaoException extends RuntimeException {    

    public ConexaoException(String message) {
        super(message);
    }

    public ConexaoException(String message, Exception error) {
        super(message, error);
        Msg.ServerErro(message, error);
    }

    public ConexaoException(String message, String method, Exception error) {
        super(message, error);
        Msg.ServerErro(message, method, error);
    }

}
