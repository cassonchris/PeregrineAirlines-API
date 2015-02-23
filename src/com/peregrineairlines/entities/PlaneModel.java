/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peregrineairlines.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Chris
 */
@Entity
@Table(name = "plane_model")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlaneModel.findAll", query = "SELECT p FROM PlaneModel p"),
    @NamedQuery(name = "PlaneModel.findByName", query = "SELECT p FROM PlaneModel p WHERE p.name = :name")})
public class PlaneModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    public PlaneModel() {
    }

    public PlaneModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlaneModel)) {
            return false;
        }
        PlaneModel other = (PlaneModel) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.peregrineairlines.entities.PlaneModel[ name=" + name + " ]";
    }
    
}
