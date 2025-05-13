package com.adrianocodenow.contatos2022.model;

import com.adrianocodenow.contatos2022.interfaces.IPhoneType;

/**
 *
 * @author ElderBR
 */
public class PhoneType implements IPhoneType {

    private int id;
    private String name;

    public PhoneType() {
    }

    public PhoneType(String name) {
        this.name = name;
    }   

    @Override
    public PhoneType setIdPhoneType(int id) {
        this.id = id;
        return this;
    }

    @Override
    public int getIdPhoneType() {
        return id;
    }

    @Override
    public PhoneType setNamePhoneType(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getNamePhoneType() {
        return name;
    }
}
