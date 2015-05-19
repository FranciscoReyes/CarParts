/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaRecambios.Listas;

import buscaRecambios.entity.TipoPieza;
import java.util.List;

/**
 *
 * @author Francisco A. Reyes
 */
public class TiposPieza {
    
    private List <TipoPieza> listaTipoPiezas;

    public TiposPieza() {
    }

    public TiposPieza(List resultList) {
        this.listaTipoPiezas = resultList;
    }

    public List<TipoPieza> getListaTipoPiezas() {
        return listaTipoPiezas;
    }
    
    public void addTipoPieza(TipoPieza tipopieza) {
        this.listaTipoPiezas.add(tipopieza);
    }
    
    public TipoPieza getTPiezaByIndex (int id) {
        return listaTipoPiezas.get(id);
    }
    
}
