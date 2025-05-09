package com.adrianocodenow.contatos2022.model;

import com.adrianocodenow.contatos2022.interfaces.IAddressType;

/**
 *
 * @author ElderBR
 */
public class AddressType implements IAddressType {

    private int id;
    private String name;

    public AddressType() {
    }

    @Override
    public AddressType setIdAddressType(int id) {
        this.id = id;
        return this;
    }

    @Override
    public int getIdAddressType() {
        return id;
    }

    @Override
    public AddressType setNameAddressType(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getNameAddressType() {
        return name;
    }
}
