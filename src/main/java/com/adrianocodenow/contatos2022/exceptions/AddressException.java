package com.adrianocodenow.contatos2022.exceptions;

import com.adrianocodenow.contatos2022.interfaces.IAddress;
import java.util.Objects;

/**
 *
 * @author ElderBR
 */
public class AddressException extends RuntimeException {

    private String msg;

    public AddressException(String message) {
        super(message);
        msg = message;
    }

    public AddressException(String message, Exception error) {
        super(message, error);
        msg = message + "\nErro: " + error.getMessage();
    }

    public static boolean validation(IAddress address) {
        if (Objects.isNull(address)) {
            throw new AddressException("Endereço inválido!");
        }
        if(Objects.isNull(address.getStreetAddress()) || address.getStreetAddress().isEmpty()){
            throw new AddressException("O campo endereço é obrigatório!");
        }
        if(Objects.isNull(address.getDistrictAddress()) || address.getDistrictAddress().isEmpty()){
            throw new AddressException("O campo bairro é obrigatório!");
        }
        if(Objects.isNull(address.getCityAddress()) || address.getCityAddress().isEmpty()){
            throw new AddressException("O campo cidade é obrigatório!");
        }
        if(Objects.isNull(address.getStateAddress()) || address.getStateAddress().isEmpty()){
            throw new AddressException("O campo estado é obrigatório!");
        }
        if(Objects.isNull(address.getCepAddress()) || address.getCepAddress().isEmpty()){
            throw new AddressException("O campo CEP é obrigatório!");
        }
        if(Objects.isNull(address.getCountryAddress()) || address.getCountryAddress().isEmpty()){
            throw new AddressException("O campo país é obrigatório!");
        }
        return true;
    }

    @Override
    public String toString() {
        return msg;
    }

}
