package com.adrianocodenow.contatos2022.controller;

import com.adrianocodenow.contatos2022.dao.PhoneDao;
import com.adrianocodenow.contatos2022.exceptions.PhoneException;
import com.adrianocodenow.contatos2022.interfaces.IPhoneType;
import com.adrianocodenow.contatos2022.model.Phone;
import java.util.Objects;
import javax.swing.JTextField;

/**
 *
 * @author ElderBR
 */
public class PhoneCtrl {

    private static PhoneCtrl instance;
    private PhoneDao dao;
    
    private PhoneCtrl() {
        dao = PhoneDao.getInstance();
    }
    
    public static PhoneCtrl getInstance(){
        if(Objects.isNull(instance)){
            instance = new PhoneCtrl();
        }
        return instance;
    }

    public void save(JTextField tfPhone, IPhoneType phoneType){
        if(Objects.isNull(tfPhone)){
            tfPhone.requestFocus();
            tfPhone.selectAll();
            throw new PhoneException("Número inválido");
        }
        String number = tfPhone.getText().trim().replaceAll("[^0-9]", "");
        if(number.isBlank() || number.length() != 11){
            tfPhone.requestFocus();
            tfPhone.setText(number);
            tfPhone.selectAll();
            throw new PhoneException("Número inválido");
        }
        if(Objects.isNull(phoneType) || phoneType.getIdPhoneType() < 1){
            tfPhone.requestFocus();
            tfPhone.selectAll();
            throw new PhoneException("Escolha o tipo de número");
        }
        Phone phone = new Phone();
        phone.setNumberPhone(number);
        phone.setPhoneType(phoneType);
        dao.insert(phone);
        tfPhone.setText("");
        tfPhone.setEnabled(false);
        phoneType = null;
    }
    
}
