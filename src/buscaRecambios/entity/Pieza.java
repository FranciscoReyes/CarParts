/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaRecambios.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Francisco A. Reyes
 */
@Entity
@Table(name = "pieza")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pieza.findAll", query = "SELECT p FROM Pieza p"),
    @NamedQuery(name = "Pieza.findByIdPieza", query = "SELECT p FROM Pieza p WHERE p.idPieza = :idPieza"),
    @NamedQuery(name = "Pieza.findByNombre", query = "SELECT p FROM Pieza p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Pieza.findByDescrip", query = "SELECT p FROM Pieza p WHERE p.descrip = :descrip")})
public class Pieza implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPieza")
    private Integer idPieza;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descrip")
    private String descrip;
    @JoinColumn(name = "idTipoPieza", referencedColumnName = "idTipoPieza")
    @ManyToOne
    private TipoPieza idTipoPieza;
    @JoinColumn(name = "idModelo", referencedColumnName = "idModelo")
    @ManyToOne
    private Modelo idModelo;

    public Pieza() {
    }

    public Pieza(Integer idPieza) {
        this.idPieza = idPieza;
    }

    public Pieza(Integer idPieza, String nombre) {
        this.idPieza = idPieza;
        this.nombre = nombre;
    }

    public Integer getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(Integer idPieza) {
        this.idPieza = idPieza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public TipoPieza getIdTipoPieza() {
        return idTipoPieza;
    }

    public void setIdTipoPieza(TipoPieza idTipoPieza) {
        this.idTipoPieza = idTipoPieza;
    }

    public Modelo getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Modelo idModelo) {
        this.idModelo = idModelo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPieza != null ? idPieza.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pieza)) {
            return false;
        }
        Pieza other = (Pieza) object;
        if ((this.idPieza == null && other.idPieza != null) || (this.idPieza != null && !this.idPieza.equals(other.idPieza))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "buscaRecambios.entity.Pieza[ idPieza=" + idPieza + " ]";
    }
    
}
