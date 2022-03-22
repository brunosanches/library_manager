package com.ensta.librarymanager.service;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Abonnement;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.model.Membre;
import junit.framework.TestCase;

import java.time.LocalDate;
import java.util.List;

public class EmpruntServiceTest extends TestCase {

    public void testReturnBook() {
        EmpruntService es = EmpruntService.getInstance();
        try {
            es.create(5, 3, LocalDate.of(2021, 03, 07));
            List<Emprunt> le = es.getListCurrentByLivre(3);
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
            es.returnBook(e.getId());
            e = es.getById(e.getId());
            assertEquals(e.getDateRetour(), LocalDate.now());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void testIsLivreDispo() {
        EmpruntService es = EmpruntService.getInstance();
        try {
            es.create(5, 3, LocalDate.of(2021, 03, 07));
            List<Emprunt> le = es.getListCurrentByLivre(3);
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

            assertFalse(es.isLivreDispo(e.getIdLivre()));
            es.returnBook(e.getId());
            assertTrue(es.isLivreDispo(e.getId()));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void testIsEmpruntPossible() {
        EmpruntService es = EmpruntService.getInstance();
        MembreService ms = MembreService.getInstance();
        LivreService ls = LivreService.getInstance();
        try {
            Membre membre = ms.create(new Membre(-1, "Macedo", "Bruno",
                    "1 xxxxxxx", "xxxx@xxxx.com",
                    "+77 77 77 77 77 77", Abonnement.BASIC));

            int idLivre1 = ls.create("History", "Jhon", "xxxxxxxxxx");
            int idLivre2 = ls.create("Geography", "Jhon", "xxxxxxxxxx");
            es.create(membre.getId(), idLivre1, LocalDate.now());
            assertTrue(es.isEmpruntPossible(ms.getById(membre.getId())));
            es.create(membre.getId(), idLivre2, LocalDate.now());
            assertFalse(es.isEmpruntPossible(ms.getById(membre.getId())));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}