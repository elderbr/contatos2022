package com.adrianocodenow.contatos2022.lists.renders;

import com.adrianocodenow.contatos2022.interfaces.IPhoneType;
import com.adrianocodenow.contatos2022.interfaces.IUser;
import com.adrianocodenow.contatos2022.utils.Colors;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author ElderBR
 */
public class UserRender extends JLabel implements ListCellRenderer<IUser> {

    @Override
    public Component getListCellRendererComponent(JList<? extends IUser> list, IUser value, int index,
            boolean isSelected, boolean cellHasFocus) {
        setOpaque(true); // Permite exibir o fundo
        
        setPreferredSize(new Dimension(getWidth(), 30));
        
        setText(value.getNameFullUser());// Texto que deve ser exibido

        // Define cores para seleção
        if(index % 2 == 0){
            setBackground(Color.white);
        }else{
            setBackground(Colors.tbLine());
        }
        if(isSelected){
            setBackground(Colors.select());
        }
        return this;
    }

}
