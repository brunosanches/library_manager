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
        Membre membre = new Membre(-1, nom, prenom, adresse, email, telephone, Abonnement.BASIC);

        try {
            MembreService membreService = MembreService.getInstance();
            membre = membreService.create(membre);
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
        Membre membre = new Membre(-1, nom, prenom, adresse, email, telephone, Abonnement.BASIC);
        MembreService membreService = MembreService.getInstance();

        try {

            membre = membreService.create(membre);
            System.out.println(membre);

            membre.setAbonnement(Abonnement.VIP);
            membreService.update(membre);
            membre = membreService.getById(membre.getId());
            System.out.println(membre);

            assertEquals(nom.toUpperCase(), membre.getNom());
            assertEquals(prenom, membre.getPrenom());
            assertEquals(adresse, membre.getAdresse());
            assertEquals(email, membre.getEmail());
            assertEquals(telephone, membre.getTelephone());
            assertEquals(Abonnement.VIP, membre.getAbonnement());

            membre.setNom("");
            membreService.update(membre);
            assertTrue(false); // If it comes here it is wrong, need to throw exception

        } catch (ServiceException e) {
            assertEquals(e.getLocalizedMessage(), "Le nom ou prenom du membre est vide");
        }

        try {
            membreService.delete(membre.getId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}