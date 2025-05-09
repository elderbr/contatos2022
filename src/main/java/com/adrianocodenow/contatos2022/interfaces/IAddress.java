package com.adrianocodenow.contatos2022.interfaces;

import com.adrianocodenow.contatos2022.exceptions.AddressException;

/**
 *
 * @author ElderBR
 */
public interface IAddress {
    
    IAddress setIdAddress(int id);
    int getIdAddress();
    
    IAddress setStreetAddress(String street);
    String getStreetAddress();
    
    IAddress setDistrictAddress(String district);
    String getDistrictAddress();
    
    IAddress setCityAddress(String city);
    String getCityAddress();
    
    IAddress setStateAddress(String state);
    String getStateAddress();
    
    IAddress setCepAddress(String cep);
    String getCepAddress();
    
    IAddress setCountryAddress(String country);
    String getCountryAddress();
    
    default <A extends IAddress> A toAddress(Class<A> clazz){
        try {
            A instance = clazz.getDeclaredConstructor().newInstance();
            instance.setIdAddress(getIdAddress());
            instance.setStreetAddress(getStreetAddress());
            instance.setDistrictAddress(getDistrictAddress());
            instance.setCityAddress(getCityAddress());
            instance.setStateAddress(getStateAddress());
            instance.setCepAddress(getCepAddress());
            instance.setCountryAddress(getCountryAddress());
            return instance;
        } catch (Exception e) {
            throw new AddressException("Erro ao converter a classe "+ clazz.getSimpleName());
        }
    }
}
