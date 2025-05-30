package com.adrianocodenow.contatos2022.interfaces;

import com.adrianocodenow.contatos2022.exceptions.PhoneException;

/**
 *
 * @author ElderBR
 */
public interface IPhone {
    
    IPhone setIdPhone(int id);
    int getIdPhone();
    
    IPhone setNumberPhone(String number);
    String getNumberPhone();
    
    IPhone setPhoneType(IPhoneType type);
    IPhoneType getPhoneType();
    
    default <P extends IPhone> P toPhone(Class<P> clazz){
        try {
            P instance = clazz.getDeclaredConstructor().newInstance();
            instance.setIdPhone(getIdPhone());
            instance.setNumberPhone(getNumberPhone());
            instance.setPhoneType(getPhoneType());
            return instance;
        } catch (Exception e) {
            throw new PhoneException("Erro ao converter phone "+ clazz.getSimpleName());
        }
    }
}
