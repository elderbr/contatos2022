/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adrianocodenow.contatos2022.exceptions;

import com.adrianocodenow.contatos2022.interfaces.IUser;
import java.util.Objects;

/**
 *
 * @author ElderBR
 */
public class UserException extends RuntimeException {

    private String msg;

    public UserException(String message) {
        super(message);
        msg = message;
    }

    public UserException(String message, Exception error) {
        super(message, error);
        msg = message + "\nErro: " + error.getMessage();
    }

    public static boolean validation(IUser user) {
        if (Objects.isNull(user)) {
            throw new UserException("Usuário inválido!");
        }
        if (Objects.isNull(user.getNameUser()) || user.getNameUser().isEmpty()) {
            throw new UserException("Nome do usuário obrigatório!");
        }
        if (Objects.isNull(user.getLastNameUser()) || user.getLastNameUser().isEmpty()) {
            throw new UserException("Sobrenome do usuário obrigatório!");
        }
        if (Objects.isNull(user.getStatus())) {
            throw new UserException("Informe o status do usuário!");
        }
        return true;
    }

    @Override
    public String toString() {
        return msg;
    }

}
