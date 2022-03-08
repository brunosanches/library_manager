package com.ensta.librarymanager.service;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Abonnement;
import com.ensta.librarymanager.model.Membre;
import junit.framework.TestCase;
import org.junit.Test;

public class MembreServiceTest extends TestCase {
    @Test
    public void testCreate() {
        String nom = "";
        String prenom = "Frank";
        String adresse = "1491 Mill Run Rd, Mill Run, PA 15464";
        String email = "lloyd.wright@gmail.com";
        String telephone = "+1 (444) 444-4444";

        try {
            MembreService ms = MembreService.getInstance();
            int id = ms.create(nom, prenom, adresse, email, telephone);
            assertTrue(false); // If it comes here it is wrong, need to throw exception
        } catch (ServiceException e) {
            assertEquals(e.getLocalizedMessage(), "Le nom ou prenom du membre est vide");
        }
    }
    @Test
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

            m.setNom("");
            ms.update(m);
            assertTrue(false); // If it comes here it is wrong, need to throw exception

        } catch (ServiceException e) {
            assertEquals(e.getLocalizedMessage(), "Le nom ou prenom du membre est vide");
        }
    }
}