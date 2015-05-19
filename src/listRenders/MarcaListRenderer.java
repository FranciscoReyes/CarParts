/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listRenders;

import buscaRecambios.entity.Marca;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author Francisco A. Reyes
 */
public class MarcaListRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        setText(((Marca) value).getMarca());
        
        if (isSelected) {
            this.setBackground(Color.cyan);
        } else {
            this.setBackground(Color.white);
        }
        return this;
    }
    
    
}
