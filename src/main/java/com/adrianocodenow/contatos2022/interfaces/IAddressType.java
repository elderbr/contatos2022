package com.adrianocodenow.contatos2022.interfaces;

import com.adrianocodenow.contatos2022.exceptions.AddressException;

/**
 *
 * @author ElderBR
 */
public interface IAddressType {
    IAddressType setIdAddressType(int id);
    int getIdAddressType();
    
    IAddressType setNameAddressType(String name);
    String getNameAddressType();
    
    default <A extends IAddressType> A toAddressType(Class<A> clazz){
        try {
            A instance = clazz.getDeclaredConstructor().newInstance();
            instance.setIdAddressType(getIdAddressType());
            instance.setNameAddressType(getNameAddressType());
            return instance;
        } catch (Exception e) {
            throw new AddressException("Erro ao converter a classe "+ clazz.getSimpleName());
        }
    }
}
