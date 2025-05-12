package com.adrianocodenow.contatos2022.model;

import com.adrianocodenow.contatos2022.interfaces.IPhone;
import com.adrianocodenow.contatos2022.interfaces.IPhoneType;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ElderBR
 */
public class Phone implements IPhone {

    private int id;
    private String number;
    private PhoneType phoneType;

    public Phone() {
    }

    public Phone(String number) {
        this.number = number;
    }
    
    public Phone(int id, String number) {
        this.id = id;
        this.number = number;
    }

    @Override
    public Phone setIdPhone(int id) {
        this.id = id;
        return this;
    }

    @Override
    public int getIdPhone() {
        return id;
    }

    @Override
    public Phone setNumberPhone(String number) {
        this.number = number;
        return this;
    }

    @Override
    public String getNumberPhone() {
        return number;
    }

    @Override
    public Phone setPhoneType(IPhoneType type) {
        this.phoneType = type.toPhoneType(PhoneType.class);
        return this;
    }

    @Override
    public PhoneType getPhoneType() {
        return phoneType;
    }
}
