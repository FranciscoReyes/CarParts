/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaRecambios.Renders;

import buscaRecambios.entity.Marca;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Usuario
 */
public class IdMARCA_Renderer extends DefaultTableCellRenderer {
    
    @Override
    protected void setValue(Object value) {
        setText(String.valueOf(((Marca) value).getId()));
    }
    
}
