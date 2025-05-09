package com.adrianocodenow.contatos2022.interfaces;

/**
 *
 * @author ElderBR
 */
public interface IPhone {
    
    IPhone setIdPhone(int id);
    int getIdPhone();
    
    IPhone setNumberPhone(String number);
    String getNumberPhone();
    
    default <P extends IPhone> P toPhone(Class<P> clazz){
        try {
            P instance = clazz.getDeclaredConstructor().newInstance();
            instance.setIdPhone(getIdPhone());
            instance.setNumberPhone(getNumberPhone());
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter phone "+ clazz.getSimpleName());
        }
    }
}
