package com.adrianocodenow.contatos2022.controller;

import com.adrianocodenow.contatos2022.dao.UserDao;
import com.adrianocodenow.contatos2022.exceptions.UserException;
import com.adrianocodenow.contatos2022.interfaces.IUser;
import com.adrianocodenow.contatos2022.model.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    private IUser user;
    private List<IUser> list = new ArrayList<>();

    private UserCtrl() {
        userDao = UserDao.getInstance();
        list = userDao.findAll();
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
        
        // Criando novo usuário
        User user = new User();
        user.setNameUser(name);
        user.setLastNameUser(lastName);
        user.setDateCreation(LocalDate.now());

        // Verifica se o usuário existe
        if (!Objects.isNull(userDao.findByNameFull(user))) {
            throw new UserException("Usuário já existe!");
        }
        
        // Atualizando a lista
        userDao.insert(user);
        findAll();                
        fireContentsChanged(this, list.size() - 1, list.size() - 1);
        
        // Limpando os campos
        tfName.setText("");
        tfLastName.setText("");
        tfName.requestFocus();
    }

    public List<IUser> findAll() {
        return list = userDao.findAll();
    }

    public void getSelected(IUser user, JTextField tfName, JTextField tfLastName) {
        if(Objects.isNull(user)){
            return;
        }
        tfName.setEnabled(true);
        tfName.setText(user.getNameUser());
        tfLastName.setEnabled(true);
        tfLastName.setText(user.getLastNameUser());
    }

    @Override
    public IUser getElementAt(int index) {
        user = list.get(index);
        return user;
    }

    @Override
    public int getSize() {
        return list.size();
    }

}
