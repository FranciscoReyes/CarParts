/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaRecambios.TableModels;

import buscaRecambios.Listas.Modelos;
import buscaRecambios.entity.Marca;
import buscaRecambios.entity.Modelo;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Usuario
 */
public class TableModelModelos extends AbstractTableModel {
    Modelos listmodelos;
    
    public TableModelModelos(Modelos listamodelos) {
        this.listmodelos = listamodelos;
    }

    @Override
    public int getRowCount() {
        return listmodelos.getListamodelos().size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object modeloInfo = null;
        
        switch (columnIndex) {
            case 0:
                modeloInfo = listmodelos.getListamodelos().get(rowIndex).getIdModelo();
                break;
            case 1:
                modeloInfo = listmodelos.getListamodelos().get(rowIndex).getIdMarca().getMarca();
                break;
            case 2:
                modeloInfo = listmodelos.getListamodelos().get(rowIndex).getModelo();
                break;
            case 3:
                modeloInfo = listmodelos.getListamodelos().get(rowIndex).getCilindrada();
                break;
            case 4:
                modeloInfo = listmodelos.getListamodelos().get(rowIndex).getCv();
                break;
            case 5:
                modeloInfo = listmodelos.getListamodelos().get(rowIndex).getAnno();
                break;
            case 6:
                modeloInfo = listmodelos.getListamodelos().get(rowIndex).getCombustible();
                break;
        }
        
        return modeloInfo;
    }
    
    @Override
    public String getColumnName(int column) {
        String nombre = "";
        switch (column) {
            case 0:
                nombre = "IdModelo";
                break;
            case 1:
                nombre = "Marca";
                break;
            case 2:
                nombre = "Modelo";
                break;
            case 3:
                nombre = "Cilindrada";
                break;
            case 4:
                nombre = "CV";
                break;
            case 5:
                nombre = "Año";
                break;
            case 6:
                nombre = "Combustible";
                break;
        }
        
        return nombre;
    }
    
    // Esto sirve para seleccionar que columnas pueden ser editables o no
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
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
        Modelo modelo = listmodelos.getListamodelos().get(rowIndex);
        
        switch (columnIndex) {
            case 1:
                modelo.setIdMarca((Marca) aValue);
                break;
            case 2:
                modelo.setModelo(String.valueOf(aValue));
                break;
            case 3:
                modelo.setCilindrada(Integer.valueOf(aValue.toString()));
                break;
            case 4:
                modelo.setCv(Integer.valueOf(aValue.toString()));
                break;
            case 5:
                modelo.setAnno(aValue.toString());
                break;
            case 6:
                modelo.setCombustible(aValue.toString());
                break;
        }
        
    }
    
    public void refreshRow(int row) {
        this.fireTableRowsUpdated(row, row);
    }
    
}
