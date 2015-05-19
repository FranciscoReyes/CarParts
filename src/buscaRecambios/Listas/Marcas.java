/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaRecambios.Listas;

import buscaRecambios.entity.Marca;
import buscaRecambios.ventanas.CarPartsWindow;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Francisco A. Reyes
 */
public class Marcas {
    EntityManager ent = CarPartsWindow.entityManager;
    Query q = CarPartsWindow.entityManager.createNamedQuery("Marca.findAll");
    
    private List <Marca> listaMarcas;
    
    Marca marca;
    

    public Marcas() {
        
    }
    
    public Marcas(List lista) {
        this.listaMarcas = lista;
    }

    public List<Marca> getListaMarcas() {
        return listaMarcas;
    }

    public void addMarca(Marca marca) {
        this.listaMarcas.add(marca);
    }
    
    public Marca getMarcaByIndex (int index) {
        return listaMarcas.get(index);
    }
    
    public String getMarca (int id)  {
        return listaMarcas.get(id).getMarca();
    }
    
    public Marca getId(int pos) {
        return listaMarcas.get(pos);
    }
    
    public Marca getMarcaById(int id){
        for (Marca marca2 : listaMarcas) {
            if (marca2.getId()== id){
                 return marca;
            }
        }
        return null;
    }
}
