package com.ensta.librarymanager.dao;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Emprunt;
import junit.framework.TestCase;

import java.time.LocalDate;
import java.util.List;

public class EmpruntDaoTest extends TestCase {

    public void testGetList() {
        EmpruntDao ed = EmpruntDao.getInstance();
        try {
            List<Emprunt> le = ed.getList();
            System.out.println(le);
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    public void testGetListCurrent() {
        EmpruntDao ed = EmpruntDao.getInstance();
        try {
            List<Emprunt> le = ed.getListCurrent();
            System.out.println(le);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public void testGetListCurrentByMembre() {
        EmpruntDao ed = EmpruntDao.getInstance();
        int idMembre = 5;
        try {
            List<Emprunt> le = ed.getListCurrentByMembre(idMembre);
            System.out.println(le);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public void testGetListCurrentByLivre() {
        EmpruntDao ed = EmpruntDao.getInstance();
        int idLivre = 3;
        try {
            List<Emprunt> le = ed.getListCurrentByLivre(idLivre);
            System.out.println(le);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public void testGetById() {
        EmpruntDao ed = EmpruntDao.getInstance();
        int id = 3;
        try {
            Emprunt e = ed.getById(id);
            System.out.println(e);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public void testCreate() {
        EmpruntDao ed = EmpruntDao.getInstance();
        try {
            ed.create(5, 3, LocalDate.of(2022, 03, 07));
            List<Emprunt> le = ed.getListCurrentByLivre(3);
            System.out.println(le);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public void testUpdate() {
        EmpruntDao ed = EmpruntDao.getInstance();
        try {
            ed.create(5, 3, LocalDate.of(2022, 03, 8));
            List<Emprunt> le = ed.getListCurrentByMembre(5);
            Emprunt e = le.stream().filter(
                    item -> item.getIdLivre() == 3 &&
                            item.getDateEmprunt().isEqual(LocalDate.of(2022, 3, 8))
                    ).findFirst().orElse(null);

            int id = e.getId();
            e.setDateRetour(LocalDate.of(2022, 4, 8));
            ed.update(e);

            Emprunt e2 = ed.getById(id);

            assertEquals(e.getDateRetour(), e2.getDateRetour());
            System.out.println(e);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public void testCount() {
        try {
            EmpruntDao ed = EmpruntDao.getInstance();
            int count = ed.count();
            ed.create(1, 1, LocalDate.of(2021, 9, 3));
            int count2 = ed.count();
            assertEquals(count + 1, count2);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}