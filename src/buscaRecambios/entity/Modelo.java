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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "modelo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modelo.findAll", query = "SELECT m FROM Modelo m"),
    @NamedQuery(name = "Modelo.findByIdModelo", query = "SELECT m FROM Modelo m WHERE m.idModelo = :idModelo"),
    @NamedQuery(name = "Modelo.findByModelo", query = "SELECT m FROM Modelo m WHERE m.modelo = :modelo"),
    @NamedQuery(name = "Modelo.findByCilindrada", query = "SELECT m FROM Modelo m WHERE m.cilindrada = :cilindrada"),
    @NamedQuery(name = "Modelo.findByCv", query = "SELECT m FROM Modelo m WHERE m.cv = :cv"),
    @NamedQuery(name = "Modelo.findByAnno", query = "SELECT m FROM Modelo m WHERE m.anno = :anno"),
    @NamedQuery(name = "Modelo.findByCombustible", query = "SELECT m FROM Modelo m WHERE m.combustible = :combustible")})
public class Modelo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idModelo")
    private Integer idModelo;
    @Basic(optional = false)
    @Column(name = "modelo")
    private String modelo;
    @Basic(optional = false)
    @Column(name = "cilindrada")
    private int cilindrada;
    @Basic(optional = false)
    @Column(name = "cv")
    private int cv;
    @Basic(optional = false)
    @Column(name = "anno")
    private String anno;
    @Column(name = "combustible")
    private String combustible;
    @OneToMany(mappedBy = "idModelo")
    private Collection<Pieza> piezaCollection;
    @JoinColumn(name = "idMarca", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Marca idMarca;

    public Modelo() {
    }

    public Modelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public Modelo(Integer idModelo, String modelo, int cilindrada, int cv, String anno) {
        this.idModelo = idModelo;
        this.modelo = modelo;
        this.cilindrada = cilindrada;
        this.cv = cv;
        this.anno = anno;
    }

    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    @XmlTransient
    public Collection<Pieza> getPiezaCollection() {
        return piezaCollection;
    }

    public void setPiezaCollection(Collection<Pieza> piezaCollection) {
        this.piezaCollection = piezaCollection;
    }

    public Marca getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Marca idMarca) {
        this.idMarca = idMarca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModelo != null ? idModelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modelo)) {
            return false;
        }
        Modelo other = (Modelo) object;
        if ((this.idModelo == null && other.idModelo != null) || (this.idModelo != null && !this.idModelo.equals(other.idModelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "buscaRecambios.entity.Modelo[ idModelo=" + idModelo + " ]";
    }
    
}
