package com.adrianocodenow.contatos2022.model;

import com.adrianocodenow.contatos2022.interfaces.IPhone;

/**
 *
 * @author ElderBR
 */
public class Phone implements IPhone {

    private int id;
    private String number;

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
}
