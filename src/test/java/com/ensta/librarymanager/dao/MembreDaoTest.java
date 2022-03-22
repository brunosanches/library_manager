package com.ensta.librarymanager.dao;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Abonnement;
import com.ensta.librarymanager.model.Membre;
import junit.framework.TestCase;
import org.junit.Test;

public class MembreDaoTest extends TestCase {
    @Test
    public void testGetList() {
        MembreDao membreDao = MembreDao.getInstance();
        try {
            System.out.println(membreDao.getList());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGetById() {
        MembreDao membreDao = MembreDao.getInstance();
        try {
            System.out.println(membreDao.getById(1));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCreate() {
        MembreDao membreDao = MembreDao.getInstance();
        String nom = "Lloyd Wright";
        String prenom = "Frank";
        String adresse = "1491 Mill Run Rd, Mill Run, PA 15464";
        String email = "lloyd.wright@gmail.com";
        String telephone = "+1 (444) 444-4444";
        Membre membre = new Membre(-1, nom, prenom,
                adresse, email,
                telephone, Abonnement.BASIC);

        try {
            membre = membreDao.create(membre);
            System.out.println(membre);
            assertEquals(nom, membre.getNom());
            assertEquals(prenom, membre.getPrenom());
            assertEquals(adresse, membre.getAdresse());
            assertEquals(email, membre.getEmail());
            assertEquals(telephone, membre.getTelephone());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testUpdate() {
        MembreDao membreDao = MembreDao.getInstance();
        String nom = "Lloyd Wright";
        String prenom = "Frank";
        String adresse = "1491 Mill Run Rd, Mill Run, PA 15464";
        String email = "lloyd.wright@gmail.com";
        String telephone = "+1 (444) 444-4444";
        Membre membre = new Membre(-1, nom, prenom,
                adresse, email,
                telephone, Abonnement.BASIC);

        try {
            membre = membreDao.create(membre);
            System.out.println(membre);

            membre.setAbonnement(Abonnement.VIP);
            membreDao.update(membre);
            membre = membreDao.getById(membre.getId());
            System.out.println(membre);

            assertEquals(nom, membre.getNom());
            assertEquals(prenom, membre.getPrenom());
            assertEquals(adresse, membre.getAdresse());
            assertEquals(email, membre.getEmail());
            assertEquals(telephone, membre.getTelephone());
            assertEquals(Abonnement.VIP, membre.getAbonnement());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testDelete() {
        MembreDao membreDao = MembreDao.getInstance();
        Membre membre = new Membre(-1, "Lloyd Wright", "Frank",
                "1491 Mill Run Rd, Mill Run, PA 15464", "lloyd.wright@gmail.com",
                "+1 (444) 444-4444", Abonnement.BASIC);

        try {
            membre = membreDao.create(membre);
            System.out.println(membre);

            membreDao.delete(membre.getId());
            membre = membreDao.getById(membre.getId());
            assertNull(membre);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCount() {
        MembreDao membreDao = MembreDao.getInstance();
        try {
            System.out.println(membreDao.count());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}