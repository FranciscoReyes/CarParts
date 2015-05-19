/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaRecambios.Listas;

import buscaRecambios.entity.Modelo;
import java.util.List;

/**
 *
 * @author Francisco A. Reyes
 */
public class Modelos {
    
    
    private List <Modelo> listamodelos;

    public Modelos() {
        
    }
    
    public Modelos(List lista) {
        this.listamodelos = lista;
    } 
    
    public List<Modelo> getListamodelos() {
        return listamodelos;
    }
    
    public void addModelo (Modelo modelo) {
        this.listamodelos.add(modelo);
    }
    
    public Modelo getModeloByIndex (int id) {
        return listamodelos.get(id);
    }
    
    public int getSize() {
        return listamodelos.size();
    }
                   
}
