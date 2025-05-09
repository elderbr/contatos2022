package com.adrianocodenow.contatos2022.interfaces;

import com.adrianocodenow.contatos2022.exceptions.UserException;

/**
 *
 * @author ElderBR
 */
public interface IUserSimples {
    
    IUserSimples setIdUser(int id);
    int getIdUser();
    
    IUserSimples setNameUser(String name);
    String getNameUser();
    
    IUserSimples setLastNameUser(String lastName);
    String getLastNameUser();
    default String getNameFullUser(){
        return getNameUser().concat(" ").concat(getLastNameUser());
    }
    
    IUserSimples setStatus(boolean status);
    boolean getStatus();
    
    default <U extends IUserSimples> U toUserSimples(Class<U> clazz){
        try {
            U instance = clazz.getDeclaredConstructor().newInstance();
            instance.setIdUser(getIdUser());
            instance.setNameUser(getNameUser());
            instance.setStatus(getStatus());
            return instance;
        } catch (Exception e) {
            throw new UserException("Erro ao converter UserSimples "+ clazz.getSimpleName()+"\nErro: "+ e.getMessage());
        }
    }
    
}
