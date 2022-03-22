package com.ensta.librarymanager.service;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Abonnement;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.model.Membre;
import junit.framework.TestCase;

import java.time.LocalDate;
import java.util.List;

public class EmpruntServiceTest extends TestCase {

    public void testReturnBook() {
        EmpruntService empruntService = EmpruntService.getInstance();
        try {
            empruntService.create(5, 3, LocalDate.of(2021, 03, 07));
            List<Emprunt> le = empruntService.getListCurrentByLivre(3);
            System.out.println(le);
            assertTrue(le.stream().anyMatch(
                    item -> item.getIdLivre() == 3 &&
                            item.getIdMembre() == 5 &&
                            item.getDateEmprunt().isEqual(LocalDate.of(2021, 03, 07))));

            Emprunt e = le.stream().filter(
                    item -> item.getIdLivre() == 3 &&
                            item.getIdMembre() == 5 &&
                            item.getDateEmprunt().isEqual(LocalDate.of(2021, 03, 07)))
                    .findFirst().orElse(null);

            assertNotNull(e);
            empruntService.returnBook(e.getId());
            e = empruntService.getById(e.getId());
            assertEquals(e.getDateRetour(), LocalDate.now());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void testIsLivreDispo() {
        EmpruntService empruntService = EmpruntService.getInstance();
        try {
            empruntService.create(5, 3, LocalDate.of(2021, 03, 07));
            List<Emprunt> le = empruntService.getListCurrentByLivre(3);
            System.out.println(le);
            assertTrue(le.stream().anyMatch(
                    item -> item.getIdLivre() == 3 &&
                            item.getIdMembre() == 5 &&
                            item.getDateEmprunt().isEqual(LocalDate.of(2021, 03, 07))));

            Emprunt e = le.stream().filter(
                            item -> item.getIdLivre() == 3 &&
                                    item.getIdMembre() == 5 &&
                                    item.getDateEmprunt().isEqual(LocalDate.of(2021, 03, 07)))
                    .findFirst().orElse(null);

            assertFalse(empruntService.isLivreDispo(e.getIdLivre()));
            empruntService.returnBook(e.getId());
            assertTrue(empruntService.isLivreDispo(e.getId()));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void testIsEmpruntPossible() {
        EmpruntService empruntService = EmpruntService.getInstance();
        MembreService membreService = MembreService.getInstance();
        LivreService livreService = LivreService.getInstance();
        try {
            Membre membre = membreService.create(new Membre(-1, "Macedo", "Bruno",
                    "1 xxxxxxx", "xxxx@xxxx.com",
                    "+77 77 77 77 77 77", Abonnement.BASIC));

            Livre livre1 = new Livre(-1, "History", "Jhon", "xxxxxxxxxx");
            Livre livre2 = new Livre(-1, "Geography", "Jhon", "xxxxxxxxxx");
            livre1 = livreService.create(livre1);
            livre2 = livreService.create(livre2);

            empruntService.create(membre.getId(), livre1.getId(), LocalDate.now());
            assertTrue(empruntService.isEmpruntPossible(membreService.getById(membre.getId())));
            empruntService.create(membre.getId(), livre2.getId(), LocalDate.now());
            assertFalse(empruntService.isEmpruntPossible(membreService.getById(membre.getId())));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}