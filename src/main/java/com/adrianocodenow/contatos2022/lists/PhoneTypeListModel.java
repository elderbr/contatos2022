package com.adrianocodenow.contatos2022.lists;

import com.adrianocodenow.contatos2022.dao.PhoneTypeDao;
import com.adrianocodenow.contatos2022.interfaces.IPhoneType;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author ElderBR
 */
public class PhoneTypeListModel extends DefaultListModel<IPhoneType> {

    private PhoneTypeDao phoneTypeDao;
    private IPhoneType type;
    private List<IPhoneType> list = new ArrayList<>();

    public PhoneTypeListModel() {
        phoneTypeDao = PhoneTypeDao.getInstance();
        list = phoneTypeDao.findAll();
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

}
