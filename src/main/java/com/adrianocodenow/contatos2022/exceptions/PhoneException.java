package com.adrianocodenow.contatos2022.exceptions;

import com.adrianocodenow.contatos2022.interfaces.IPhone;
import java.util.Objects;

/**
 *
 * @author ElderBR
 */
public class PhoneException extends RuntimeException {

    private String msg;

    public PhoneException(String message) {
        super(message);
        msg = message;
    }

    public PhoneException(String message, Exception error) {
        super(message, error);
        msg = message + "\nErro: " + error.getMessage();
    }

    public static boolean validation(IPhone phone) {
        if (Objects.isNull(phone)) {
            throw new PhoneException("Telefone inválido!");
        }
        if (phone.getNumberPhone().isBlank()) {
            throw new PhoneException("Número do telefone obrigatório!");
        }
        return true;
    }

    @Override
    public String toString() {
        return msg;
    }

}
