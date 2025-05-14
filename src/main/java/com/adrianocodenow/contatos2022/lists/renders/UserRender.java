package com.adrianocodenow.contatos2022.lists.renders;

import com.adrianocodenow.contatos2022.interfaces.IPhoneType;
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
public class PhoneTypeRender extends JLabel implements ListCellRenderer<IPhoneType> {

    @Override
    public Component getListCellRendererComponent(JList<? extends IPhoneType> list, IPhoneType value, int index,
            boolean isSelected, boolean cellHasFocus) {
        setOpaque(true); // Permite exibir o fundo
        
        setPreferredSize(new Dimension(getWidth(), 30));
        
        setText(value.getNamePhoneType());// Texto que deve ser exibido

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
