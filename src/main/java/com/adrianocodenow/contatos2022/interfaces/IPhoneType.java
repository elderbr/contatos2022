package com.adrianocodenow.contatos2022.interfaces;

/**
 *
 * @author ElderBR
 */
public interface IPhoneType {

    IPhoneType setIdPhoneType(int id);
    int getIdPhoneType();
    
    IPhoneType setNamePhoneType(String name);
    String getNamePhoneType();
    
    default <T extends IPhoneType> T toPhoneType(Class<T> clazz){
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            instance.setIdPhoneType(getIdPhoneType());
            instance.setNamePhoneType(getNamePhoneType());
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter a classe "+ clazz.getSimpleName());
        }
    }    
}
