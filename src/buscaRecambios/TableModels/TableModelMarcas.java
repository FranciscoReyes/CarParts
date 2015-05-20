/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaRecambios.TableModels;

import buscaRecambios.Listas.Marcas;
import buscaRecambios.entity.Marca;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Francisco A. Reyes
 */
public class TableModelMarcas extends AbstractTableModel {
    Marcas listMarcas;
    
    public TableModelMarcas(Marcas listamarca) {
        this.listMarcas = listamarca;
    }
    
    @Override
    public int getRowCount() {
        return listMarcas.getListaMarcas().size(); //Obtener el numero de filas de la tabla modelos como sea
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object marcainfo = null;
        
        switch (columnIndex) {
            case 0:
                marcainfo = listMarcas.getId(rowIndex);
                break;
            case 1:
                marcainfo = listMarcas.getId(rowIndex).getMarca();
                break;
            
        }
        
        return marcainfo;
    }
    
    @Override
    public String getColumnName (int column) {
        String nombre = "";
        switch (column) {
            case 0:
                nombre = "IdMarca";
                break;
            case 1:
                nombre = "Marca";
                break;
        }
        
        return nombre;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return false;
            case 1:
                return true;
            default:
                return true;
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Marca marca = listMarcas.getListaMarcas().get(rowIndex);
        
        switch (columnIndex) {
            case 1:
                marca.setMarca(String.valueOf(aValue));
                break;
           
        }
        
    }
    
    public void refreshRow(int row){
        this.fireTableRowsUpdated(row, row);
    }
    
}
