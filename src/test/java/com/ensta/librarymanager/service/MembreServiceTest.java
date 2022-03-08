package com.ensta.librarymanager.service;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Abonnement;
import com.ensta.librarymanager.model.Membre;
import junit.framework.TestCase;

public class MembreServiceTest extends TestCase {

    public void testGetList() {
        MembreService ms = MembreService.getInstance();
        try {
            System.out.println(ms.getList());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void testGetById() {
        MembreService ms = MembreService.getInstance();
        try {
            System.out.println(ms.getById(1));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void testCreate() {
        String nom = "Lloyd Wright";
        String prenom = "Frank";
        String adresse = "1491 Mill Run Rd, Mill Run, PA 15464";
        String email = "lloyd.wright@gmail.com";
        String telephone = "+1 (444) 444-4444";

        try {
            MembreService ms = MembreService.getInstance();
            int id = ms.create(nom, prenom, adresse, email, telephone);
            Membre m = ms.getById(id);
            System.out.println(m);
            assertEquals(nom.toUpperCase(), m.getNom());
            assertEquals(prenom, m.getPrenom());
            assertEquals(adresse, m.getAdresse());
            assertEquals(email, m.getEmail());
            assertEquals(telephone, m.getTelephone());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void testUpdate() {
        String nom = "Lloyd Wright";
        String prenom = "Frank";
        String adresse = "1491 Mill Run Rd, Mill Run, PA 15464";
        String email = "lloyd.wright@gmail.com";
        String telephone = "+1 (444) 444-4444";

        try {
            MembreService ms = MembreService.getInstance();
            int id = ms.create(nom, prenom, adresse, email, telephone);
            Membre m = ms.getById(id);
            System.out.println(m);

            m.setAbonnement(Abonnement.VIP);
            ms.update(m);
            m = ms.getById(id);
            System.out.println(m);

            assertEquals(nom.toUpperCase(), m.getNom());
            assertEquals(prenom, m.getPrenom());
            assertEquals(adresse, m.getAdresse());
            assertEquals(email, m.getEmail());
            assertEquals(telephone, m.getTelephone());
            assertEquals(Abonnement.VIP, m.getAbonnement());

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void testDelete() {
        String nom = "Lloyd Wright";
        String prenom = "Frank";
        String adresse = "1491 Mill Run Rd, Mill Run, PA 15464";
        String email = "lloyd.wright@gmail.com";
        String telephone = "+1 (444) 444-4444";

        try {
            MembreService ms = MembreService.getInstance();
            int id = ms.create(nom, prenom, adresse, email, telephone);
            Membre m = ms.getById(id);
            System.out.println(m);

            ms.delete(id);
            m = ms.getById(id);
            assertNull(m);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void testCount() {
        LivreService ls = LivreService.getInstance();
        try {
            System.out.println(ls.count());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}