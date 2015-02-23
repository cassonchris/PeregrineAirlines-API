/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peregrineairlines.model;

import com.peregrineairlines.entities.PlaneModel;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Chris
 */
public class PAModel {
    
    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    public static void open() {
        emf = Persistence.createEntityManagerFactory("PeregrineAirlines-APIPU");
        em = emf.createEntityManager();
    }
    
    public static void close() {
        em.close();
        emf.close();
    }
    
    public static Collection<PlaneModel> getPlaneModels() {
        Query query = em.createNamedQuery("PlaneModel.findAll");
        return query.getResultList();
    }
}
