package com.adrianocodenow.contatos2022.cbox;

import com.adrianocodenow.contatos2022.dao.PhoneTypeDao;
import com.adrianocodenow.contatos2022.interfaces.IPhoneType;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author ElderBR
 */
public class PhoneTypeCbox extends DefaultComboBoxModel<IPhoneType>{
    
    private PhoneTypeDao phoneTDao;
    private IPhoneType type;
    private List<IPhoneType> list = new ArrayList<>();

    public PhoneTypeCbox() {
        phoneTDao = PhoneTypeDao.getInstance();
        list = phoneTDao.findAll();
    }   
    

    @Override
    public IPhoneType getElementAt(int index) {
        type = list.get(index);
        return type;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public IPhoneType getSelectedItem() {
        return type;
    }
    
    
}
