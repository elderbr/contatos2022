package com.adrianocodenow.contatos2022.interfaces;

import com.adrianocodenow.contatos2022.exceptions.UserException;
import java.time.LocalDate;

/**
 *
 * @author ElderBR
 */
public interface IUser extends IUserSimples{
    
    IUser setDateCreation(LocalDate date);
    LocalDate getDateCreation();
    
    IUser setDateUpdated();
    LocalDate getDateUpdated();
    
    default <U extends IUser> U toUser(Class<U> clazz){
        try {
            U instance = toUserSimples(clazz);
            instance.setDateCreation(getDateCreation());            
            return instance;
        } catch (Exception e) {
            throw new UserException("Erro ao converter User "+ clazz.getSimpleName());
        }
    }
}
