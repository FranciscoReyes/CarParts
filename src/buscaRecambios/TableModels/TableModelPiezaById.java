/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaRecambios.TableModels;

import buscaRecambios.Listas.Piezas;
import buscaRecambios.entity.Pieza;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Usuario
 */
public class TableModelPiezaById extends AbstractTableModel {
    Piezas lista;
    Pieza pieza;
    
    public TableModelPiezaById(Piezas lista) {
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return lista.getListaPieza().size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object piezaInfo = null;
        
        switch (columnIndex) {
            case 0:
                piezaInfo = lista.getListaPieza().get(rowIndex).getIdPieza();
                break;
            case 1:
                piezaInfo = lista.getListaPieza().get(rowIndex).getIdModelo().getIdModelo();
                break;
            case 2:
                piezaInfo = lista.getListaPieza().get(rowIndex).getNombre();
                break;
            case 3:
                piezaInfo = lista.getListaPieza().get(rowIndex).getIdTipoPieza().getTipo();
                break;
            case 4:
                piezaInfo =lista.getListaPieza().get(rowIndex).getDescrip();
                break;
        }
        
        return piezaInfo;
    }
    
}
