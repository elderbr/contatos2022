package com.adrianocodenow.contatos2022.controller;

import com.adrianocodenow.contatos2022.dao.UserDao;
import com.adrianocodenow.contatos2022.exceptions.UserException;
import com.adrianocodenow.contatos2022.interfaces.IUser;
import com.adrianocodenow.contatos2022.model.User;
import java.time.LocalDate;
import java.util.Objects;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;

/**
 *
 * @author ElderBR
 */
public class UserCtrl extends DefaultListModel<IUser> {

    private static UserCtrl instance;
    private UserDao userDao;

    private UserCtrl() {
        userDao = UserDao.getInstance();
    }

    public static UserCtrl getInstance() {
        if (Objects.isNull(instance)) {
            instance = new UserCtrl();
        }
        return instance;
    }

    public void save(JTextField tfName, JTextField tfLastName) {
        if (Objects.isNull(tfName)) {
            throw new UserException("Campo nome obrigatório!");
        }
        String name = tfName.getText().trim();
        if (name.isBlank() || name.length() < 3) {
            throw new UserException("Campo nome obrigatório e não pode conter menos de 2 letras!");
        }
        if (Objects.isNull(tfLastName)) {
            throw new UserException("Campo sobrenome obrigatório!");
        }
        String lastName = tfLastName.getText().trim();
        if (lastName.isBlank() || lastName.length() < 3) {
            throw new UserException("Campo sobrenome obrigatório e não pode conter menos de 2 letras!");
        }
        User user = new User();
        user.setNameUser(name);
        user.setLastNameUser(lastName);
        user.setDateCreation(LocalDate.now());

        if (!Objects.isNull(userDao.findByNameFull(user))) {
            throw new UserException("Usuário já existe!");
        }
        userDao.insert(user);
    }
}
