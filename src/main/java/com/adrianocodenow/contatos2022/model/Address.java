package com.adrianocodenow.contatos2022.model;

import com.adrianocodenow.contatos2022.interfaces.IAddress;

/**
 *
 * @author ElderBR
 */
public class Address implements IAddress {

    private int id;
    private String street;
    private String district;
    private String city;
    private String state;
    private String cep;
    private String country;

    public Address() {
    }

    @Override
    public Address setIdAddress(int id) {
        this.id = id;
        return this;
    }

    @Override
    public int getIdAddress() {
        return id;
    }

    @Override
    public Address setStreetAddress(String street) {
        this.street = street;
        return this;
    }

    @Override
    public String getStreetAddress() {
        return street;
    }

    @Override
    public Address setDistrictAddress(String district) {
        this.district = district;
        return this;
    }

    @Override
    public String getDistrictAddress() {
        return district;
    }

    @Override
    public Address setCityAddress(String city) {
        this.city = city;
        return this;
    }

    @Override
    public String getCityAddress() {
        return city;
    }

    @Override
    public Address setStateAddress(String state) {
        this.state = state;
        return this;
    }

    @Override
    public String getStateAddress() {
        return state;
    }

    @Override
    public Address setCepAddress(String cep) {
        this.cep = cep;
        return this;
    }

    @Override
    public String getCepAddress() {
        return cep;
    }

    @Override
    public Address setCountryAddress(String country) {
        this.country = country;
        return this;
    }

    @Override
    public String getCountryAddress() {
        return country;
    }
}
