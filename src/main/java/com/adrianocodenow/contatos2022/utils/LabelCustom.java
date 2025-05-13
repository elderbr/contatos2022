/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adrianocodenow.contatos2022.utils;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author ElderBR
 */
public class LabelCustom {

    public static void isAddButton(JLabel label, boolean has) {
        if (has) {
            label.setBackground(Color.GREEN);
            label.setBorder(BorderFactory.createLineBorder(new Color(0, 85, 0), 2));
        } else {
            label.setBackground(new Color(213, 216, 222));
            label.setBorder(null);
        }
    }
    
    public static void isSubButton(JLabel label, boolean has) {
        if (has) {
            label.setOpaque(true);
            label.setBackground(new Color(220, 53, 69));
            label.setBorder(BorderFactory.createLineBorder(new Color(146, 35, 46), 2));
        } else {
            label.setBackground(new Color(213, 216, 222));
            label.setBorder(null);
        }
    }
}
