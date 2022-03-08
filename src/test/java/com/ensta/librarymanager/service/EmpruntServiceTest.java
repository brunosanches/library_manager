package com.ensta.librarymanager.service;

import com.ensta.librarymanager.dao.EmpruntDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.model.Membre;
import junit.framework.TestCase;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EmpruntServiceTest extends TestCase {

    public void testGetList() {
        try {
            EmpruntService es = EmpruntService.getInstance();
            List<Emprunt> le = es.getList();
            System.out.println(le);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void testGetListCurrent() {
        try {
            EmpruntService es = EmpruntService.getInstance();
            List<Emprunt> le = es.getListCurrent();
            System.out.println(le);
            assertFalse(le.stream().anyMatch(item -> item.getDateRetour() != null));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void testGetListCurrentByMembre() {
        try {
            EmpruntService es = EmpruntService.getInstance();
            List<Emprunt> le = es.getListCurrentByMembre(5);
            System.out.println(le);
            assertTrue(le.stream().allMatch(item -> item.getIdMembre() == 5));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void testGetListCurrentByLivre() {
        try {
            EmpruntService es = EmpruntService.getInstance();
            List<Emprunt> le = es.getListCurrentByLivre(3);
            System.out.println(le);
            assertTrue(le.stream().allMatch(item -> item.getIdLivre() == 3));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void testGetById() {
        try {
            EmpruntService es = EmpruntService.getInstance();
            Emprunt e = es.getById(3);
            System.out.println(e);
            assertTrue(e.getId() == 3);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void testCreate() {
        EmpruntService es = EmpruntService.getInstance();
        try {
            es.create(5, 3, LocalDate.of(2022, 03, 07));
            List<Emprunt> le = es.getListCurrentByLivre(3);
            System.out.println(le);
            assertTrue(le.stream().anyMatch(
                    item -> item.getIdLivre() == 3 &&
                    item.getIdMembre() == 5 &&
                    item.getDateEmprunt().isEqual(LocalDate.of(2022, 03, 07))));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

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

    public void testCount() {
        try {
            EmpruntService es = EmpruntService.getInstance();
            assertEquals(es.getList().size(), es.count());
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
            int idMembre = ms.create("Macedo", "Bruno", "1 xxxxxxx", "xxxx@xxxx.com",
                    "+77 77 77 77 77 77");

            int idLivre1 = ls.create("History", "Jhon", "xxxxxxxxxx");
            int idLivre2 = ls.create("Geography", "Jhon", "xxxxxxxxxx");
            es.create(idMembre, idLivre1, LocalDate.now());
            assertTrue(es.isEmpruntPossible(ms.getById(idMembre)));
            es.create(idMembre, idLivre2, LocalDate.now());
            assertFalse(es.isEmpruntPossible(ms.getById(idMembre)));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}