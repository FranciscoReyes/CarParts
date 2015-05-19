/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaRecambios.TableModels;

import buscaRecambios.Listas.Piezas;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Usuario
 */
public class TableModelPiezas extends AbstractTableModel {
    Piezas listpiezas;

    public TableModelPiezas(Piezas listapiezas) {
        this.listpiezas = listapiezas;
    }

    @Override
    public int getRowCount() {
        return listpiezas.getListaPieza().size();
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
                piezaInfo = listpiezas.getListaPieza().get(rowIndex);
                break;
            case 1:
                piezaInfo = listpiezas.getListaPieza().get(rowIndex);
                break;
            case 2:
                piezaInfo = listpiezas.getListaPieza().get(rowIndex);
                break;
            case 3:
                piezaInfo = listpiezas.getListaPieza().get(rowIndex);
                break;
            case 4:
                piezaInfo = listpiezas.getListaPieza().get(rowIndex);
                break;
        }
        return piezaInfo;
    }

    @Override
    public String getColumnName(int column) {
        String nombre = "";
        switch (column) {
            case 0:
                nombre = "IdPieza";
                break;
            case 1:
                nombre = "Modelo";
                break;
            case 2:
                nombre = "Nombre";
                break;
            case 3:
                nombre = "TipoPieza";
                break;
            case 4:
                nombre = "Descripción";
                break;
        }

        return nombre;
    }

}
