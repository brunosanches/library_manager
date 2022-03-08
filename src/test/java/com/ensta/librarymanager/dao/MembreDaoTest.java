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

        try {
            int id = membreDao.create(nom, prenom, adresse, email, telephone);
            Membre m = membreDao.getById(id);
            System.out.println(m);
            assertEquals(nom, m.getNom());
            assertEquals(prenom, m.getPrenom());
            assertEquals(adresse, m.getAdresse());
            assertEquals(email, m.getEmail());
            assertEquals(telephone, m.getTelephone());
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

        try {
            int id = membreDao.create(nom, prenom, adresse, email, telephone);
            Membre m = membreDao.getById(id);
            System.out.println(m);

            m.setAbonnement(Abonnement.VIP);
            membreDao.update(m);
            m = membreDao.getById(id);
            System.out.println(m);

            assertEquals(nom, m.getNom());
            assertEquals(prenom, m.getPrenom());
            assertEquals(adresse, m.getAdresse());
            assertEquals(email, m.getEmail());
            assertEquals(telephone, m.getTelephone());
            assertEquals(Abonnement.VIP, m.getAbonnement());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testDelete() {
        MembreDao membreDao = MembreDao.getInstance();
        String nom = "Lloyd Wright";
        String prenom = "Frank";
        String adresse = "1491 Mill Run Rd, Mill Run, PA 15464";
        String email = "lloyd.wright@gmail.com";
        String telephone = "+1 (444) 444-4444";

        try {
            int id = membreDao.create(nom, prenom, adresse, email, telephone);
            Membre m = membreDao.getById(id);
            System.out.println(m);

            membreDao.delete(id);
            m = membreDao.getById(id);
            assertNull(m);
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