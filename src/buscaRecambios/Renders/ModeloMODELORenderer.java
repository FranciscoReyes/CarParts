package buscaRecambios.Renders;

import javax.swing.table.DefaultTableCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Francisco A. Reyes
 */
public class ModeloMODELORenderer extends DefaultTableCellRenderer{
    
    
    @Override
    protected void setValue (Object value) {
        setText(String.valueOf(value));
        
    }
}
