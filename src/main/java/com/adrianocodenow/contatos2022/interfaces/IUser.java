package com.adrianocodenow.contatos2022.interfaces;

import com.adrianocodenow.contatos2022.exceptions.UserException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ElderBR
 */
public interface IUser extends IUserSimples{
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    IUser setDateCreation(LocalDate date);
    LocalDate getDateCreation();
    default String toDateCreation(){
        return getDateCreation().format(formatter);
    }
    
    IUser setDateUpdated();
    LocalDate getDateUpdated();
    default String toDateUpdated(){
        return getDateUpdated().format(formatter);
    }
    
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
