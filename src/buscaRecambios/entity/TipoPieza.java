/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaRecambios.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Francisco A. Reyes
 */
@Entity
@Table(name = "tipo_pieza")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPieza.findAll", query = "SELECT t FROM TipoPieza t"),
    @NamedQuery(name = "TipoPieza.findByIdTipoPieza", query = "SELECT t FROM TipoPieza t WHERE t.idTipoPieza = :idTipoPieza"),
    @NamedQuery(name = "TipoPieza.findByTipo", query = "SELECT t FROM TipoPieza t WHERE t.tipo = :tipo")})
public class TipoPieza implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoPieza")
    private Integer idTipoPieza;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(mappedBy = "idTipoPieza")
    private Collection<Pieza> piezaCollection;

    public TipoPieza() {
    }

    public TipoPieza(Integer idTipoPieza) {
        this.idTipoPieza = idTipoPieza;
    }

    public TipoPieza(Integer idTipoPieza, String tipo) {
        this.idTipoPieza = idTipoPieza;
        this.tipo = tipo;
    }

    public Integer getIdTipoPieza() {
        return idTipoPieza;
    }

    public void setIdTipoPieza(Integer idTipoPieza) {
        this.idTipoPieza = idTipoPieza;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public Collection<Pieza> getPiezaCollection() {
        return piezaCollection;
    }

    public void setPiezaCollection(Collection<Pieza> piezaCollection) {
        this.piezaCollection = piezaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPieza != null ? idTipoPieza.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPieza)) {
            return false;
        }
        TipoPieza other = (TipoPieza) object;
        if ((this.idTipoPieza == null && other.idTipoPieza != null) || (this.idTipoPieza != null && !this.idTipoPieza.equals(other.idTipoPieza))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "buscaRecambios.entity.TipoPieza[ idTipoPieza=" + idTipoPieza + " ]";
    }
    
}
