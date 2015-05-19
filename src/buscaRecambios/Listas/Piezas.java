/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaRecambios.Listas;

import buscaRecambios.entity.Pieza;
import buscaRecambios.ventanas.CarPartsWindow;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Francisco A. Reyes
 */
public class Piezas {
    EntityManager ent = CarPartsWindow.entityManager;
    Query q = CarPartsWindow.entityManager.createNamedQuery("Pieza.findAll");
    private List <Pieza> listaPieza = q.getResultList();

    public Piezas() {
    }

    public Piezas(List resultList) {
        this.listaPieza = resultList;
    }

    public List<Pieza> getListaPieza() {
        return listaPieza;
    }
    
    public void addPieza(Pieza pieza) {
        this.listaPieza.add(pieza);
    }
    
    public Pieza getPiezaByModelo (int modeloId) {
        for (Pieza pieza : listaPieza) {
            if (pieza.getIdPieza() == modeloId) {
                return pieza;
            }
        }
        return null;
    }
}
