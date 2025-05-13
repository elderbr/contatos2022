package com.adrianocodenow.contatos2022.controller;

import com.adrianocodenow.contatos2022.dao.PhoneTypeDao;
import com.adrianocodenow.contatos2022.exceptions.PhoneException;
import com.adrianocodenow.contatos2022.interfaces.IPhoneType;
import com.adrianocodenow.contatos2022.model.PhoneType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author ElderBR
 */
public class PhoneTypeCtrl extends DefaultListModel<IPhoneType> {

    private static PhoneTypeCtrl instance;
    private PhoneTypeDao dao;
    private IPhoneType phoneType;
    private List<IPhoneType> listType = new ArrayList<>();

    private PhoneTypeCtrl() {
        dao = PhoneTypeDao.getInstance();
        listType = dao.findAll();
    }

    public static PhoneTypeCtrl getInstance() {
        if (Objects.isNull(instance)) {
            instance = new PhoneTypeCtrl();
        }
        return instance;
    }

    public void save(@NotNull JTextField tfPhoneType) {
        String type = tfPhoneType.getText().trim();
        if (Objects.isNull(type) || type.isBlank()) {
            throw new PhoneException("O nome do tipo de telefone inválido!");
        }
        if (type.length() < 3) {
            throw new PhoneException("O nome do tipo de telefone precisa ser maior 2 letras!");
        }
        PhoneType phoneType = new PhoneType(type);
        dao.insert(phoneType);
        tfPhoneType.setText("");
        tfPhoneType.requestFocus();
        
        // Busca a lista
        listType = dao.findAll();        
        fireContentsChanged(this, listType.size()-1, listType.size()-1);
    }

    
    public void delete(@NotNull IPhoneType phoneType) {
        if(Objects.isNull(phoneType) || phoneType.getIdPhoneType() < 1){
            throw new PhoneException("Tipo de telefone inválido!");
        }
        dao.findById(phoneType.getIdPhoneType());        
        dao.delete(phoneType);
        listType.remove(phoneType);        
        fireContentsChanged(this, listType.size()-1, listType.size()-1);
    }
    
    

    @Override
    public IPhoneType getElementAt(int index) {
        phoneType = listType.get(index);
        return phoneType;
    }

    @Override
    public int getSize() {
        return listType.size();
    }
}
