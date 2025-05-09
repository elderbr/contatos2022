package com.adrianocodenow.contatos2022.model;

import com.adrianocodenow.contatos2022.interfaces.IUser;
import java.time.LocalDate;

/**
 *
 * @author ElderBR
 */
public class User implements IUser {

    private int id;
    private String name;
    private String lastName;
    private boolean status;
    private LocalDate dateCreation;
    private LocalDate dateUpdated;

    @Override
    public User setDateCreation(LocalDate date) {
        this.dateCreation = date;
        return this;
    }

    @Override
    public LocalDate getDateCreation() {
        return dateCreation;
    }

    @Override
    public User setDateUpdated() {
        dateUpdated = LocalDate.now();
        return this;
    }

    @Override
    public LocalDate getDateUpdated() {
        return dateUpdated;
    }

    @Override
    public User setIdUser(int id) {
        this.id = id;
        return this;
    }

    @Override
    public int getIdUser() {
        return id;
    }

    @Override
    public User setNameUser(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getNameUser() {
        return name;
    }

    @Override
    public User setLastNameUser(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public String getLastNameUser() {
        return lastName;
    }

    @Override
    public User setStatus(boolean status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean getStatus() {
        return status;
    }

}
